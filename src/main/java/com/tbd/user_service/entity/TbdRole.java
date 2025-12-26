package com.tbd.user_service.entity;

import com.tbd.user_service.enums.TbdRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbd_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbdRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TbdRoles name;
}
