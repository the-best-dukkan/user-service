package com.tbd.user_service.entity;

import com.tbd.user_service.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.util.ProxyUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbd_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbdUser extends Auditable {

    @Id
    @Column(name = "sub", nullable = false, updatable = false)
    private String sub;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "profile_complete")
    private boolean profileComplete = false;

    @Column(name = "picture")
    private String picture;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbd_user_roles",
            joinColumns = @JoinColumn(name = "user_sub"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<TbdRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TbdAddress> addresses = new ArrayList<>(20);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Check if the other object is a Hibernate proxy or the actual class
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        TbdUser that = (TbdUser) o;
        // If ID is null (unsaved), they are only equal if they are the same instance (this == o)
        return sub != null && sub.equals(that.sub);
    }

    @Override
    public int hashCode() {
        // We return a constant because the ID changes from null to a value after saving.
        // This ensures the entity stays in the same "bucket" in a Set/Map.
        return getClass().hashCode();
    }
}
