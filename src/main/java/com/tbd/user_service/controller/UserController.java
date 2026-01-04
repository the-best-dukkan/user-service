package com.tbd.user_service.controller;

import com.tbd.proto.user_service.TbdUserProto;
import com.tbd.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/detail")
    public ResponseEntity<TbdUserProto> getCurrentUserDetails() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
