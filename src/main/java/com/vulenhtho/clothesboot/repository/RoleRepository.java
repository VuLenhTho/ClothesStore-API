package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAll();
}
