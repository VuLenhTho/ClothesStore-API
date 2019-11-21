package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findUserByUserName(String userName);

    List<User> findAll();


}
