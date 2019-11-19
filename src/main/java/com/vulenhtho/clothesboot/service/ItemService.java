package com.vulenhtho.clothesboot.service;

import com.vulenhtho.clothesboot.entity.Cart;
import com.vulenhtho.clothesboot.model.request.CartRequest;

public interface ItemService {
    void insert(CartRequest cartRequest, Cart cart);

    void update(CartRequest cartRequest, Cart cart);
}
