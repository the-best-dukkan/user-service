package com.tbd.user_service.repository;

import com.tbd.user_service.entity.TbdAddress;
import com.tbd.user_service.entity.TbdUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbdAddressRepository extends JpaRepository<TbdAddress, Long> {

    @Query("select address from TbdAddress address where address.user.sub = :userSub")
    Page<TbdAddress> findAllByUserSub(String userSub, Pageable pageable);

    @Query("select count(*) from TbdAddress address where address.user.sub = :userSub")
    int findCountByUserSub(String userSub);

    Optional<TbdAddress> findByIdAndUserSub(Long id, String sub);
}
