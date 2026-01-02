package com.tbd.user_service.repository;

import com.tbd.user_service.entity.TbdAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbdAddressRepository extends JpaRepository<TbdAddress,Long> {
}
