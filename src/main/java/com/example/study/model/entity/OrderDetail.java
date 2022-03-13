package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;


    // N : 1
    // 객체 이름을 적어줘야 함
    @ManyToOne
    private User user; // user_id

    // N : 1
    @ManyToOne
    private Item item;
}
