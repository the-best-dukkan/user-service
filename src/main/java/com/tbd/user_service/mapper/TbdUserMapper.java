package com.tbd.user_service.mapper;

import com.tbd.common.utils.ProtoMapperUtils;
import com.tbd.proto.user_service.TbdRoleProto;
import com.tbd.proto.user_service.TbdUserProto;
import com.tbd.user_service.dto.UserSyncRequestDTO;
import com.tbd.user_service.dto.UserSyncResponseDTO;
import com.tbd.user_service.entity.TbdRole;
import com.tbd.user_service.entity.TbdUser;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {ProtoMapperUtils.class}
)
public interface TbdUserMapper {

    UserSyncResponseDTO tbdUserToUserSyncResponse(TbdUser tbdUser);

    TbdUser userSyncRequestDTOToTbdUser(UserSyncRequestDTO userSyncRequestDTO);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbdUserProto toProto(TbdUser tbdUser);

    @AfterMapping
    default void mapRoles(
            TbdUser source,
            @MappingTarget TbdUserProto.Builder target
    ) {
        if (source.getRoles() != null && !source.getRoles().isEmpty()) {
            target.addAllRoles(
                    source.getRoles()
                            .stream()
                            .map(this::mapRole)
                            .toList()
            );
        }
    }

    TbdRoleProto mapRole(TbdRole role);
}
