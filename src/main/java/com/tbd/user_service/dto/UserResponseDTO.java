package com.tbd.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String sub;
    private String email;
    private String fullName;
    private boolean profileComplete;
    private String picture;
    private Instant createdDate;
    private Instant modifiedDate;
    private Instant lastLogin;
    private Boolean isEmailVerified;
    private Set<TbdRoleDTO> roles;
}
