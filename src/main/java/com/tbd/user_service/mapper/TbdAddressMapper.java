package com.tbd.user_service.mapper;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.entity.TbdAddress;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TbdAddressMapper {

    TbdAddress tbdUserDTOToUserAddress(TbdAddressDTO tbdAddressDTO);

    TbdAddressDTO tbdUserToUserAddressDTO(TbdAddress tbdAddress);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void updateEntityFromDTO(TbdAddressDTO tbdAddressDTO, @MappingTarget TbdAddress tbdAddress);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void partialUpdateEntityFromDto(TbdAddressDTO tbdAddressDTO, @MappingTarget TbdAddress tbdAddress);
}
