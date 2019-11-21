package com.vulenhtho.clothesboot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAdminRequest {
    private String sort;
    private String status;
    private String sex;
    private String search;
    private Long subCategory;
    private Boolean trend;
    private Long discount;
    private Integer page;
    private Integer size;
}
