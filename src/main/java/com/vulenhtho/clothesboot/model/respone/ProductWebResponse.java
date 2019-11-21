package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductWebResponse {
    private Long id;
    private String name;
    private Long price;
    private String status;
    private String shortDescription;
    private String thumbnail;
    private String photoList;
    private CategoryResponse category;

    private Set<ColorResponse> colors = new HashSet<>();
    private Set<DiscountResponse> discounts = new HashSet<>();
    private Set<SizeResponse> sizes = new HashSet<>();
    private Set<ProductColorSizeResponse> productColorSizes = new HashSet<>();
}
