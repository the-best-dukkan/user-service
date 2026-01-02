package com.tbd.user_service.entity;

import com.tbd.user_service.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tbd_user_roles")
@AllArgsConstructor
@NoArgsConstructor
public class TbdUserRole extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_sub", nullable = false)
    private TbdUser userSub;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private TbdRole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        TbdUserRole that = (TbdUserRole) o;
        return Objects.equals(userSub, that.userSub) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}