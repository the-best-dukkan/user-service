package com.tbd.user_service.service;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserResponseDTO;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.proto.TbdAddressPageProto;
import com.tbd.user_service.proto.TbdAddressProto;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserSyncResponseDTO syncUser(UserSyncRequestDTO userSyncRequestDTO);

    UserResponseDTO getCurrentUser();

    TbdAddressPageProto getAddresses(Pageable pageable);

    TbdAddressDTO addAddress(TbdAddressDTO tbdAddressDTO);

    TbdAddressDTO updateAddress(Long id, TbdAddressDTO tbdAddressDTO);

    TbdAddressDTO partialUpdateAddress(Long id, TbdAddressDTO tbdAddressDTO);

    void deleteAddress(Long id);

    TbdAddressProto getAddressById(Long id);
}
