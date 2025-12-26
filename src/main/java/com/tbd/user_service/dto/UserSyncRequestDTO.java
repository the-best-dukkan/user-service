package com.tbd.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSyncRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "sub cannot be blank")
    private String sub;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100)
    private String fullName;
    private String picture;
    private Boolean isEmailVerified;

}
