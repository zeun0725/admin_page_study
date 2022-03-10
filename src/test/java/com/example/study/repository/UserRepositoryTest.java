package com.example.study.repository;

import com.example.study.AdminPageStudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminPageStudyApplicationTests {

    //Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = insert into user (%s, %s, %d) value (account, email, age);
        // 객체를 통해 DB 접근 가능, sql문이 아닌
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("new User: "+newUser);
    }

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(2L); // long type => 2L

        user.ifPresent(selectUser -> {
            System.out.println("user :"+selectUser);
            System.out.println("email :"+selectUser.getEmail());
        });
    }

    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional // 마지막에 roll back transaction 해서 실 데이터 영향 없음
    public void delete() {
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent()); // true

        user.ifPresent(selectUser -> userRepository.delete(selectUser));

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); // false

    }
}
