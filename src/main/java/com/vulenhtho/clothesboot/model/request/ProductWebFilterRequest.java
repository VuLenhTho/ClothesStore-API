package com.vulenhtho.clothesboot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWebFilterRequest {
    private Long categoryId;
    private Boolean trend;
    private String sex;
    private String search;
    private String sort;
    private Integer page;
    private Integer size;
}
