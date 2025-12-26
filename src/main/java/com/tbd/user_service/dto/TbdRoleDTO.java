package com.tbd.user_service.dto;

import com.tbd.user_service.enums.TbdRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbdRoleDTO {

    TbdRoles name;
}
