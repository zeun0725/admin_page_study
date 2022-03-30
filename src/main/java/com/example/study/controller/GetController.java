package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbbb";
        System.out.println("id: "+id);
        System.out.println("pwd: "+pwd);
        return id+pwd;
    }

    // localhost:8080/api/getMultiParameter?account=abc&email=study@gmail.com&page=10
    // 검색변수가 늘어나는 상황일 때
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // json 형식 통신 {"account": "", "email": "", "page": 0}
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {
        // {"resultCode": "OK", "description": "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
