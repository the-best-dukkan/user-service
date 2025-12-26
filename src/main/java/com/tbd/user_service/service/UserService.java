package com.tbd.user_service.service;

import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;

public interface UserService {

    UserSyncResponseDTO syncUser(UserSyncRequestDTO userSyncRequestDTO);
}
