package com.tbd.user_service.service;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserResponseDTO;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;

import java.util.List;

public interface UserService {

    UserSyncResponseDTO syncUser(UserSyncRequestDTO userSyncRequestDTO);

    UserResponseDTO getUserByUserSub(String userSub);
    List<TbdAddressDTO> getAddressesByUserSub(String userSub);
    TbdAddressDTO addAddress(TbdAddressDTO tbdAddressDTO, String userSub);
}
