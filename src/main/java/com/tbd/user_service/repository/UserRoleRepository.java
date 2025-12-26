package com.tbd.user_service.repository;

import com.tbd.user_service.entity.TbdRole;
import com.tbd.user_service.enums.TbdRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<TbdRole, Long> {

    TbdRole findByName(TbdRoles name);
}
