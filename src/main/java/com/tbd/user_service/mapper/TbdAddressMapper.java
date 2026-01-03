package com.tbd.user_service.mapper;

import com.tbd.common.utils.ProtoMapperUtils;
import com.tbd.proto.user_service.TbdAddressPageProto;
import com.tbd.proto.user_service.TbdAddressProto;
import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.entity.TbdAddress;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, uses = {ProtoMapperUtils.class})
public interface TbdAddressMapper {

    TbdAddress tbdAddressDTOToTbdAddress(TbdAddressDTO tbdAddressDTO);

    TbdAddressDTO tbdAddressToTbdAddressDTO(TbdAddress tbdAddress);

    @BeanMapping(nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbdAddressProto tbdAddressToTbdAddressProto(TbdAddress tbdAddress);
    TbdAddressDTO tbdAddressProtoToTbdAddressProtoDTO(TbdAddressProto tbdAddressProto);

    default TbdAddressPageProto toAddressPageProto(Page<TbdAddress> page) {
        if (page == null) return null;

        return TbdAddressPageProto.newBuilder()
                .addAllContent(page.getContent().stream()
                        .map(this::tbdAddressToTbdAddressProto)
                        .toList())
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setNumber(page.getNumber())
                .setSize(page.getSize())
                .build();
    }

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
