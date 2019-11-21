package com.vulenhtho.clothesboot.service;

import com.vulenhtho.clothesboot.model.request.IdsRequest;
import com.vulenhtho.clothesboot.model.request.ProductAdminRequest;
import com.vulenhtho.clothesboot.model.request.ProductRequest;
import com.vulenhtho.clothesboot.model.request.ProductWebFilterRequest;
import com.vulenhtho.clothesboot.model.respone.BriefProductFilterResponse;
import com.vulenhtho.clothesboot.model.respone.ProductFilterResponse;
import com.vulenhtho.clothesboot.model.respone.ProductResponse;
import com.vulenhtho.clothesboot.model.respone.ProductWebResponse;

import java.util.List;

public interface ProductService {
    void insert(ProductRequest productRequest);

    void update(Long id, ProductRequest productRequest);

    void delete(Long id);

    void delete(IdsRequest ids);

    List<ProductResponse> findAll();

    ProductFilterResponse findAllWithFilter(ProductAdminRequest filterRequest);

    ProductResponse findById(Long id);

    ProductWebResponse findByIdWeb(Long id);

    BriefProductFilterResponse findBriefProducts(ProductWebFilterRequest filterRequest);
}
