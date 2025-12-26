package com.tbd.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSyncResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String sub;
    private String fullName;
    private boolean profileComplete;
    private Set<TbdRoleDTO> roles;
}
