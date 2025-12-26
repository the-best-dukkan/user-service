package com.tbd.user_service.controller;

import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/internal/users")
@RestController
@RequiredArgsConstructor
public class UserInternalController {

    private final UserService userService;

    @PostMapping("/sync")
    public ResponseEntity<UserSyncResponseDTO> syncUser(@Valid @RequestBody UserSyncRequestDTO userSyncRequestDTO) {
        UserSyncResponseDTO userSyncResponseDTO = userService.syncUser(userSyncRequestDTO);
        return ResponseEntity.ok(userSyncResponseDTO);
    }

}
