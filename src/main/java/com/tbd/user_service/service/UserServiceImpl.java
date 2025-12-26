package com.tbd.user_service.service;

import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.entity.TbdRole;
import com.tbd.user_service.entity.TbdUser;
import com.tbd.user_service.enums.TbdRoles;
import com.tbd.user_service.mapper.TbdUserMapper;
import com.tbd.user_service.repository.UserRepository;
import com.tbd.user_service.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final TbdUserMapper tbdUserMapper;

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

    private UserSyncResponseDTO mapToUserSyncResponse(TbdUser tbdUser) {
        return tbdUserMapper.tbdUserToUserSyncResponse(tbdUser);
    }
}
