package com.dream.demo.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/testUser")
public class TestUserController {


    @GetMapping("list")
    public List<User> getUserList(){
        return new ArrayList<>();
    }
}
