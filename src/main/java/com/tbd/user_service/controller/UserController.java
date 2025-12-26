package com.tbd.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @GetMapping("/all")
    public List<String> getUsers() {

        return Arrays.asList("user1", "user2", "user3");
    }
}
