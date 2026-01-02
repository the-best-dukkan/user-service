package com.tbd.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

@Entity
@Table(name = "tbd_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbdAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line_1", nullable = false, length = 100)
    private String addressLine1;
    @Column(name = "address_line_2", nullable = false, length = 100)
    private String addressLine2;
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Column(name = "state", nullable = false, length = 50)
    private String state;
    @Column(name = "country", nullable = false, length = 50)
    private String country;
    @Column(name = "zip_code", nullable = false, length = 50)
    private String zipCode;
    @Column(name = "country_code", nullable = false, length = 10)
    private String countryCode;
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;
    @Column(name = "alternate_phone_number", length = 20)
    private String alternatePhoneNumber;
    @Column(name = "full_name", length = 50)
    private String fullName;
    @Column(name = "landmark", length = 100)
    private String landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sub") // Matches your Liquibase column name
    private TbdUser user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Check if the other object is a Hibernate proxy or the actual class
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        TbdAddress that = (TbdAddress) o;
        // If ID is null (unsaved), they are only equal if they are the same instance (this == o)
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        // We return a constant because the ID changes from null to a value after saving.
        // This ensures the entity stays in the same "bucket" in a Set/Map.
        return getClass().hashCode();
    }
}
