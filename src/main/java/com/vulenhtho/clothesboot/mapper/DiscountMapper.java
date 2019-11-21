package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.Discount;
import com.vulenhtho.clothesboot.model.respone.DiscountResponse;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DiscountMapper {
    public DiscountResponse transferToDiscountResponse(Discount discount) {
        Date date = new Date();
        if (date.after(discount.getStartDate()) && date.before(discount.getEndDate())) {
            DiscountResponse discountResponse = new DiscountResponse();
            BeanUtils.refine(discount, discountResponse, BeanUtils::copyNonNull);
            return discountResponse;
        }
        return null;
    }

    public Set<DiscountResponse> transferToDiscountsResponse(Set<Discount> discounts) {
        Set<DiscountResponse> discountResponses = new HashSet<>();
        for (Discount d : discounts) {
            if (transferToDiscountResponse(d) != null)
                discountResponses.add(transferToDiscountResponse(d));
        }
        return discountResponses;
    }
}
