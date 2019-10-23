package com.vulenhtho.mobileboot.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilterRequest {
    private String sort;
    private Boolean status;
    private Boolean sex;
    private String search;
    private Integer page;
    private Integer size;
}
