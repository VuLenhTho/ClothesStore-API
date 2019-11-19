package com.vulenhtho.clothesboot.model.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter@Setter
public class CartRequest {
    private String customerName;
    private String phone;
    private String address;
    private List<ItemRequest> items;
}
