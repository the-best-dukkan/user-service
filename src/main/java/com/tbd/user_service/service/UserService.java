package com.tbd.user_service.service;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserResponseDTO;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserSyncResponseDTO syncUser(UserSyncRequestDTO userSyncRequestDTO);

    UserResponseDTO getCurrentUser();
    Page<TbdAddressDTO> getAddresses(Pageable pageable);
    TbdAddressDTO addAddress(TbdAddressDTO tbdAddressDTO);
}
