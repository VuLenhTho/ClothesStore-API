package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DiscountResponse {
    private Long id;
    private Long amount;
    private String content;
    private String name;
    private Long percent;
    private Date endDate;
    private Date startDate;
}
