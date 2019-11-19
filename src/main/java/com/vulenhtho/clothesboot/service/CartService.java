package com.vulenhtho.clothesboot.service;

import com.vulenhtho.clothesboot.model.request.CartRequest;

public interface CartService {
    void insert(CartRequest cartRequest);

    void update(Long id, CartRequest cartRequest);

    void delete(Long id);


}
