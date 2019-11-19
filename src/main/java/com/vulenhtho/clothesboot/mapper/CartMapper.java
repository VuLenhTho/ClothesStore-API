package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.Cart;
import com.vulenhtho.clothesboot.model.request.CartRequest;
import com.vulenhtho.clothesboot.repository.ProductRepository;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private ProductRepository productRepository;

    @Autowired
    public CartMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Cart transferToCart(Cart cart, CartRequest cartRequest) {
        BeanUtils.refine(cartRequest, cart, BeanUtils::copyNonNull);

        return cart;
    }
}
