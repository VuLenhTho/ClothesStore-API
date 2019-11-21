package com.vulenhtho.clothesboot.controller;

import com.vulenhtho.clothesboot.model.request.CartRequest;
import com.vulenhtho.clothesboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/web/cart")
    public ResponseEntity<Void> insert(@RequestBody CartRequest cartRequest) {
        cartService.insert(cartRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/web/cart/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CartRequest cartRequest) {
        cartService.update(id, cartRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
