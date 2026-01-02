package com.tbd.user_service.controller;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserResponseDTO;
import com.tbd.user_service.service.UserService;
import com.tbd.user_service.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/detail")
    public ResponseEntity<UserResponseDTO> getCurrentUserDetails(HttpServletRequest request) {

        String userSub = Util.extractUserSubFromRequest(request);

        return ResponseEntity.ok(userService.getUserByUserSub(userSub));
    }

    @PostMapping("/add-address")
    public ResponseEntity<TbdAddressDTO> addAddress(HttpServletRequest request, @RequestBody @Valid TbdAddressDTO tbdAddressDTO) {
        String userSub = Util.extractUserSubFromRequest(request);
        return new ResponseEntity<>(userService.addAddress(tbdAddressDTO, userSub), HttpStatus.CREATED);
    }

}
