package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.Discount;
import com.vulenhtho.clothesboot.model.respone.DiscountResponse;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {
    public DiscountResponse transferToDiscountResponse(Discount discount){
        DiscountResponse discountResponse = new DiscountResponse();
        BeanUtils.refine(discount,discountResponse,BeanUtils::copyNonNull);
        return discountResponse;
    }
}
