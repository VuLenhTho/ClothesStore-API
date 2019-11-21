package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCreatedDateAndPhone(Date createdDate, String phone);
}
