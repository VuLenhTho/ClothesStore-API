package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductResponse {
    private String name;
    private Long importPrice;
    private Long price;
    private Long amount;
    private String status;
    private String thumbnail;
    private String photoList;
    private String sex;
    private String createdBy;
    private Timestamp createdDate;
    private Long hot;
    private boolean trend;

    private CategoryResponse subCategory;
    private Set<ColorResponse> colors = new HashSet<>();
    private Set<DiscountResponse> discounts = new HashSet<>();
    private Set<SizeResponse> sizes = new HashSet<>();
}
