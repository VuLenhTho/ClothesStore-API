package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.Category;
import com.vulenhtho.clothesboot.model.respone.CategoryResponse;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse transferToCategoryResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.refine(category, categoryResponse,BeanUtils::copyNonNull);

        return categoryResponse;
    }
}
