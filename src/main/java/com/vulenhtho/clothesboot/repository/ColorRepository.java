package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Long> {
    List<Color> findAll();
}
