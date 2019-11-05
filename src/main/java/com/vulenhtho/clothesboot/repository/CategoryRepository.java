package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();
}
