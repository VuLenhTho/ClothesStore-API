package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
    ProductColor findByProductIdAndColorId(Long productId, Long colorId);
}
