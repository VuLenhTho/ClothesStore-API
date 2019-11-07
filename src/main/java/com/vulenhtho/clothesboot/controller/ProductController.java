package com.vulenhtho.clothesboot.controller;

import com.vulenhtho.clothesboot.model.request.IdsRequest;
import com.vulenhtho.clothesboot.model.request.ProductAdminRequest;
import com.vulenhtho.clothesboot.model.request.ProductRequest;
import com.vulenhtho.clothesboot.model.respone.ProductFilterResponse;
import com.vulenhtho.clothesboot.model.respone.ProductResponse;
import com.vulenhtho.clothesboot.model.respone.ProductWebResponse;
import com.vulenhtho.clothesboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<ProductFilterResponse> getProducts(@RequestParam(required = false,defaultValue = "0") Integer page
            , @RequestParam(required = false, defaultValue = "5") Integer size
            , @RequestParam(required = false) String search
            , @RequestParam(required = false) String status, @RequestParam(required = false) String sort
            , @RequestParam(required = false) String sex, @RequestParam(required = false) Long subCategory
            , @RequestParam(required = false) Boolean trend, @RequestParam(required = false) Long discount) {

        ProductAdminRequest productAdminRequest = new ProductAdminRequest
                (sort, status, sex, search,subCategory, trend, discount, page, size);
        return ResponseEntity.ok(productService.findAllWithFilter(productAdminRequest));
    }

    @GetMapping("/web/product/{id}")
    public ResponseEntity<ProductWebResponse> getProduct(@PathVariable Long id) {

        return ResponseEntity.ok(productService.findByIdWeb(id));
    }

    @PostMapping("/product")
    public ResponseEntity<Void> insert(@RequestBody ProductRequest productRequest) {
        productService.insert(productRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public ResponseEntity<Void> delete(@RequestBody IdsRequest idsRequest) {
        productService.delete(idsRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products-all")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

}
