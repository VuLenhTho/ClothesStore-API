package com.vulenhtho.clothesboot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterRequest {
    private String sort;
    private Boolean status;
    private Boolean sex;
    private String search;
    private Long role;
    private Integer page;
    private Integer size;
}
