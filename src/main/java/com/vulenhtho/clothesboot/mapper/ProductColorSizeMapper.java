package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.ProductColorSize;
import com.vulenhtho.clothesboot.model.respone.ProductColorSizeResponse;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProductColorSizeMapper {
    public ProductColorSizeResponse toProductColorSizeResponse(ProductColorSize productColorSize) {
        ProductColorSizeResponse response = new ProductColorSizeResponse();
        BeanUtils.refine(productColorSize, response, BeanUtils::copyNonNull);
        response.setColorId(productColorSize.getColor().getId());
        response.setSizeId(productColorSize.getSize().getId());
        return response;
    }

    public Set<ProductColorSizeResponse> toSetProductColorSizeResponses(Set<ProductColorSize> productColorSizes) {
        Set<ProductColorSizeResponse> productColorSizeResponses = new HashSet<>();
        for (ProductColorSize productColorSize : productColorSizes) {
            productColorSizeResponses.add(toProductColorSizeResponse(productColorSize));
        }
        return productColorSizeResponses;
    }
}
