package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter@Setter
public class BriefProductWebResponse {
    private Long id;
    private String name;
    private Long price;
    private String thumbnail;
    private Set<DiscountResponse> discount;
}
