package com.tbd.user_service.service;

import com.tbd.proto.user_service.TbdAddressPageProto;
import com.tbd.proto.user_service.TbdAddressProto;
import com.tbd.proto.user_service.TbdUserProto;
import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserSyncResponseDTO syncUser(UserSyncRequestDTO userSyncRequestDTO);

    TbdUserProto getCurrentUser();

    TbdAddressPageProto getAddresses(Pageable pageable);

    TbdAddressDTO addAddress(TbdAddressDTO tbdAddressDTO);

    TbdAddressDTO updateAddress(Long id, TbdAddressDTO tbdAddressDTO);

    TbdAddressDTO partialUpdateAddress(Long id, TbdAddressDTO tbdAddressDTO);

    void deleteAddress(Long id);

    TbdAddressProto getAddressById(Long id);
}
