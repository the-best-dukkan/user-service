package com.tbd.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbd_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbdUser {

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

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", updatable = false)
    private Instant modifiedAt;

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
}
