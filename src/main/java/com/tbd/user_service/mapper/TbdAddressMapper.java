package com.tbd.user_service.mapper;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.entity.TbdAddress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TbdAddressMapper {

    TbdAddress tbdUserDTOToUserAddress(TbdAddressDTO tbdAddressDTO);
    TbdAddressDTO tbdUserToUserAddressDTO(TbdAddress tbdAddress);
}
