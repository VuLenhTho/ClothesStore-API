package com.vulenhtho.clothesboot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWebRequest {
    private String sort;
    private String search;
    private Long subCategory;
    private Integer page;
    private Integer size;

}
