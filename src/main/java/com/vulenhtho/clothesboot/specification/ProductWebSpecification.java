package com.vulenhtho.clothesboot.specification;

import com.vulenhtho.clothesboot.entity.Product;
import com.vulenhtho.clothesboot.model.request.ProductWebFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public class ProductWebSpecification {
    public static Specification<Product> filterProduct(ProductWebFilterRequest filterRequest) {
        return Specification
                .where(withCategory(filterRequest.getCategoryId()))
                .and(withTrend(filterRequest.getTrend()))
                .and(withSearch(filterRequest.getSearch()))
                .and(withSex(filterRequest.getSex()));


    }

    public static Specification<Product> withCategory(Long categoryId) {
        if (categoryId == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> withTrend(Boolean trend) {
        if (trend == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("trend"), trend);
    }

    public static Specification<Product> withSearch(String search) {
        if (search == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("name"),"%"+ search+"%");
    }

    public static Specification<Product> withSex(String sex) {
        if (sex == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("sex"), sex);
    }
}
