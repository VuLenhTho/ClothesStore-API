package com.vulenhtho.clothesboot.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private Long importPrice;
    private Long price;
    private Long amount;
    private String status;
    private String thumbnail;
    private String photoList;
    private String sex;
    private Long hot;
    private boolean trend;

    private Long subCategoryId;
    private String creator;
    private Long[] colorIds;
    private Long[] sizeIds;
    private Long[] discountIds;

}
