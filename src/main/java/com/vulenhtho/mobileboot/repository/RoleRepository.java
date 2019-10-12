package com.vulenhtho.mobileboot.repository;

import com.vulenhtho.mobileboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAll();
}
