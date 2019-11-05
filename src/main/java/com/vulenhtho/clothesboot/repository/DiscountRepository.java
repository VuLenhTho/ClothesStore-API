package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    List<Discount> findAll();
}
