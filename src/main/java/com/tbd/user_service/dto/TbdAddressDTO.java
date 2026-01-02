package com.tbd.user_service.dto;

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

    @NotBlank(message = "validation.address.required.address")
    @Size(min = 5, max = 100, message = "validation.address.size.address_line_1")
    private String addressLine1;
    @Size(max = 100, message = "validation.address.size.address_line_2")
    private String addressLine2;
    @NotBlank(message = "validation.address.required.city")
    @Size(min = 2, max = 50, message = "validation.address.size.city")
    private String city;
    @NotBlank(message = "validation.address.required.state")
    @Size(min = 2, max = 50, message = "validation.address.size.state")
    private String state;
    @NotBlank(message = "validation.address.required.country")
    @Size(min = 2, max = 50, message = "validation.address.size.country")
    private String country;
    @NotBlank(message = "validation.address.required.zipcode")
    @Size(min = 2, max = 10, message = "validation.address.size.zipcode")
    private String zipCode;
    @NotBlank(message = "validation.address.required.countrycode")
    @Size(min = 2, max = 10, message = "validation.address.size.countrycode")
    private String countryCode;
    @NotBlank(message = "validation.address.required.phonenumber")
    @Size(min = 5, max = 15, message = "validation.address.size.phonenumber")
    private String phoneNumber;
    @Size(min = 5, max = 15, message = "validation.address.size.alternate_phonenumber")
    private String alternatePhoneNumber;
    @NotBlank(message = "validation.address.required.fullname")
    @Size(min = 2, max = 50, message = "validation.address.size.fullname")
    private String fullName;
    @Size(min = 2, max = 100, message = "validation.address.size.landmark")
    private String landmark;

    private Instant createdDate;
    private Instant modifiedDate;
}
