package com.vulenhtho.clothesboot.repository;

import com.vulenhtho.clothesboot.entity.Cart;
import com.vulenhtho.clothesboot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByCart(Cart cart);
}
