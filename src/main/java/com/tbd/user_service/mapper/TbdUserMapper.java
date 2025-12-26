package com.tbd.user_service.mapper;

import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.entity.TbdUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TbdUserMapper {

    UserSyncResponseDTO tbdUserToUserSyncResponse(TbdUser tbdUser);

    TbdUser userSyncRequestDTOToTbdUser(UserSyncRequestDTO userSyncRequestDTO);
}
