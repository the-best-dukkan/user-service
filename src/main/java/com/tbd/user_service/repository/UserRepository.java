package com.tbd.user_service.repository;

import com.tbd.user_service.entity.TbdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TbdUser, String> {

    Optional<TbdUser> findBySub(String sub);
    Optional<TbdUser> findByEmail(String email);
}
