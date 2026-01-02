package com.tbd.user_service.service;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserResponseDTO;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.entity.TbdAddress;
import com.tbd.user_service.entity.TbdRole;
import com.tbd.user_service.entity.TbdUser;
import com.tbd.user_service.enums.TbdRoles;
import com.tbd.user_service.exception.ResourceNotFoundInDbException;
import com.tbd.user_service.mapper.TbdAddressMapper;
import com.tbd.user_service.mapper.TbdUserMapper;
import com.tbd.user_service.repository.TbdAddressRepository;
import com.tbd.user_service.repository.UserRepository;
import com.tbd.user_service.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final TbdAddressRepository tbdAddressRepository;
    private final TbdUserMapper tbdUserMapper;
    private final TbdAddressMapper tbdAddressMapper;

    @Override
    @Transactional
    public UserSyncResponseDTO syncUser(UserSyncRequestDTO userSyncRequestDTO) {

        // check in user db if user already exists
        Optional<TbdUser> userBySub = userRepository.findBySub(userSyncRequestDTO.getSub().trim());

        // if user exists update the last_login and return the user response
        if (userBySub.isPresent()) {

            TbdUser user = userBySub.get();
            user.setLastLogin(Instant.now());

            return mapToUserSyncResponse(userRepository.saveAndFlush(user));
        } else {

            // if user does not exists, insert in db and assign the default the role
            TbdUser tbdUser = tbdUserMapper.userSyncRequestDTOToTbdUser(userSyncRequestDTO);

            TbdRole role = userRoleRepository.findByName(TbdRoles.ROLE_CUSTOMER);
            tbdUser.setRoles(Set.of(role));
            tbdUser.setLastLogin(Instant.now());

            TbdUser savedUser = userRepository.saveAndFlush(tbdUser);
            return mapToUserSyncResponse(savedUser);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByUserSub(String userSub) {

        getUserFromDb(userSub);

        TbdUser tbdUser = getUserFromDb(userSub);

        return tbdUserMapper.tbdUserToUserResponseDTO(tbdUser);
    }


    @Override
    public List<TbdAddressDTO> getAddressesByUserSub(String userSub) {

        return List.of();
    }

    @Override
    public TbdAddressDTO addAddress(TbdAddressDTO tbdAddressDTO, String userSub) {

        TbdUser tbdUser = getUserFromDb(userSub);
        TbdAddress tbdAddress = tbdAddressMapper.tbdUserDTOToUserAddress(tbdAddressDTO);

        tbdAddress.setUser(tbdUser);
        TbdAddress savedAddress = tbdAddressRepository.save(tbdAddress);
        return tbdAddressMapper.tbdUserToUserAddressDTO(savedAddress);
    }

    private TbdUser getUserFromDb(String userSub) {
        return userRepository.findBySub(userSub)
                .orElseThrow(() -> new ResourceNotFoundInDbException("error.user.notfound"));
    }

    private UserSyncResponseDTO mapToUserSyncResponse(TbdUser tbdUser) {
        return tbdUserMapper.tbdUserToUserSyncResponse(tbdUser);
    }
}
