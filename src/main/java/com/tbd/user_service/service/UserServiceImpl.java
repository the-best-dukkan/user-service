package com.tbd.user_service.service;

import com.tbd.common.exceptions.PageSizeLimitExceedException;
import com.tbd.common.exceptions.ResourceNotFoundInDbException;
import com.tbd.common.exceptions.ValidationException;
import com.tbd.common.utils.CommonUtil;
import com.tbd.common.utils.Translator;
import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.dto.UserResponseDTO;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.entity.TbdAddress;
import com.tbd.user_service.entity.TbdRole;
import com.tbd.user_service.entity.TbdUser;
import com.tbd.user_service.enums.TbdRoles;
import com.tbd.user_service.exception.MaxAddressLimitExceedException;
import com.tbd.user_service.mapper.TbdAddressMapper;
import com.tbd.user_service.mapper.TbdUserMapper;
import com.tbd.user_service.repository.TbdAddressRepository;
import com.tbd.user_service.repository.UserRepository;
import com.tbd.user_service.repository.UserRoleRepository;
import com.tbd.user_service.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final TbdAddressRepository tbdAddressRepository;
    private final TbdUserMapper tbdUserMapper;
    private final TbdAddressMapper tbdAddressMapper;
    private final HttpServletRequest httpServletRequest;
    private final Translator translator;
    private final MessageSource messageSource;

    @Value("${app.api.config.address-limit}")
    private int maxAddressLimit;

    @Value("${app.api.config.page-size-limit}")
    private int pageSizeLimit;

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
    public UserResponseDTO getCurrentUser() {

        TbdUser tbdUser = getUserFromDb();
        return tbdUserMapper.tbdUserToUserResponseDTO(tbdUser);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<TbdAddressDTO> getAddresses(Pageable pageable) {

        validatePageSize(pageable);

        TbdUser userFromDb = getUserFromDb();

        Page<TbdAddress> allByUserSub = tbdAddressRepository.findAllByUserSub(userFromDb.getSub(), pageable);
        return allByUserSub.map(tbdAddressMapper::tbdUserToUserAddressDTO);
    }

    @Override
    @Transactional
    public TbdAddressDTO addAddress(TbdAddressDTO tbdAddressDTO) {

        TbdUser tbdUser = getUserFromDb();

        validateMaxAddressLimit(tbdUser);

        TbdAddress tbdAddress = tbdAddressMapper.tbdUserDTOToUserAddress(tbdAddressDTO);

        tbdAddress.setUser(tbdUser);
        TbdAddress savedAddress = tbdAddressRepository.save(tbdAddress);
        return tbdAddressMapper.tbdUserToUserAddressDTO(savedAddress);
    }

    @Override
    @Transactional
    public TbdAddressDTO updateAddress(Long id, TbdAddressDTO tbdAddressDTO) {

        TbdAddress address = validateAndGetAddressById(id);
        tbdAddressMapper.updateEntityFromDTO(tbdAddressDTO, address);

        TbdAddress updatedAddress = tbdAddressRepository.save(address);

        return tbdAddressMapper.tbdUserToUserAddressDTO(updatedAddress);
    }

    @Override
    @Transactional
    public TbdAddressDTO partialUpdateAddress(Long id, TbdAddressDTO tbdAddressDTO) {

        TbdAddress address = validateAndGetAddressById(id);
        tbdAddressMapper.partialUpdateEntityFromDto(tbdAddressDTO, address);

        TbdAddress updatedAddress = tbdAddressRepository.save(address);

        return tbdAddressMapper.tbdUserToUserAddressDTO(updatedAddress);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {

        TbdAddress address = validateAndGetAddressById(id);
        tbdAddressRepository.delete(address);
    }

    @Override
    @Transactional(readOnly = true)
    public TbdAddressDTO getAddressById(Long id) {
        return tbdAddressMapper.tbdUserToUserAddressDTO(validateAndGetAddressById(id));
    }

    private void validateMaxAddressLimit(TbdUser tbdUser) {

        int totalAddressAdded = tbdAddressRepository.findCountByUserSub(tbdUser.getSub());

        if (totalAddressAdded >= maxAddressLimit) {
            throw new MaxAddressLimitExceedException(translator.translate("error.request.address_limit_exceed", maxAddressLimit));
        }
    }

    private TbdUser getUserFromDb() {
        return userRepository.findBySub(Util.extractUserSubFromRequest(httpServletRequest, messageSource))
                .orElseThrow(() -> new ResourceNotFoundInDbException(translator.translate("error.user.notfound")));
    }

    private UserSyncResponseDTO mapToUserSyncResponse(TbdUser tbdUser) {
        return tbdUserMapper.tbdUserToUserSyncResponse(tbdUser);
    }

    private void validatePageSize(Pageable pageable) {

        if (pageable.getPageSize() > pageSizeLimit) {
            throw new PageSizeLimitExceedException(translator.translate("error.request.page_size_limit_exceed", pageSizeLimit));
        }
    }

    private TbdAddress validateAndGetAddressById(Long id) {

        if (!CommonUtil.validateId(id)) {
            throw new ValidationException(translator.translate("error.request.invalid.id"));
        }

        Optional<TbdAddress> address = tbdAddressRepository.findById(id);

        return address.orElseThrow(() -> new ResourceNotFoundInDbException(translator.translate("error.user.address.notfound")));
    }
}
