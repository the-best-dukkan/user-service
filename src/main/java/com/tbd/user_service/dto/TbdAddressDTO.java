package com.tbd.user_service.dto;

import com.tbd.common.validation.groups.OnCreate;
import com.tbd.common.validation.groups.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbdAddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "validation.address.required.address", groups = {OnCreate.class})
    @Size(min = 5, max = 100, message = "validation.address.size.address_line_1", groups = {OnUpdate.class})
    private String addressLine1;
    @Size(max = 100, message = "validation.address.size.address_line_2", groups = {OnUpdate.class})
    private String addressLine2;
    @NotBlank(message = "validation.address.required.city", groups = {OnCreate.class})
    @Size(min = 2, max = 50, message = "validation.address.size.city", groups = {OnUpdate.class})
    private String city;
    @NotBlank(message = "validation.address.required.state", groups = {OnCreate.class})
    @Size(min = 2, max = 50, message = "validation.address.size.state", groups = {OnUpdate.class})
    private String state;
    @NotBlank(message = "validation.address.required.country", groups = {OnCreate.class})
    @Size(min = 2, max = 50, message = "validation.address.size.country", groups = {OnUpdate.class})
    private String country;
    @NotBlank(message = "validation.address.required.zipcode", groups = {OnCreate.class})
    @Size(min = 2, max = 10, message = "validation.address.size.zipcode", groups = {OnUpdate.class})
    private String zipCode;
    @NotBlank(message = "validation.address.required.countrycode", groups = {OnCreate.class})
    @Size(min = 2, max = 10, message = "validation.address.size.countrycode", groups = {OnUpdate.class})
    private String countryCode;
    @NotBlank(message = "validation.address.required.phonenumber", groups = {OnCreate.class})
    @Size(min = 5, max = 15, message = "validation.address.size.phonenumber", groups = {OnUpdate.class})
    private String phoneNumber;
    @Size(min = 5, max = 15, message = "validation.address.size.alternate_phonenumber", groups = {OnUpdate.class})
    private String alternatePhoneNumber;
    @NotBlank(message = "validation.address.required.fullname", groups = {OnCreate.class})
    @Size(min = 2, max = 50, message = "validation.address.size.fullname", groups = {OnUpdate.class})
    private String fullName;
    @Size(min = 2, max = 100, message = "validation.address.size.landmark", groups = {OnUpdate.class})
    private String landmark;

    private Instant createdDate;
    private Instant modifiedDate;
}
