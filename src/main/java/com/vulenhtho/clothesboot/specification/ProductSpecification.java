package com.vulenhtho.clothesboot.specification;

import com.vulenhtho.clothesboot.entity.Product;
import com.vulenhtho.clothesboot.model.request.ProductAdminRequest;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> filterProduct(ProductAdminRequest filter) {
        if (filter.getSearch() != null) {
            return Specification
                    .where(withSex(filter.getSex()))
                    .and(withStatus(filter.getStatus()))
                    .and(withTrend(filter.getTrend()))
                    .and(withCreatedBy(filter.getSearch())
                            .or(withName(filter.getSearch())))
                    ;
        }
        return Specification
                .where(withSex(filter.getSex()))
                .and(withStatus(filter.getStatus()))
                .and(withTrend(filter.getTrend()));

    }

    public static Specification<Product> withStatus(String status) {
        if (status == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Product> withSex(String sex) {
        if (sex == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("sex"), sex);
    }

    public static Specification<Product> withName(String name) {
        if (name == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Product> withTrend(Boolean trend) {
        if (trend == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("trend"), trend);
    }

    public static Specification<Product> withCreatedBy(String createdBy) {
        if (createdBy == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("createdBy"), createdBy);
    }

    public static Specification<Product> withPrice(Long maxPrice, Long minPrice) {
        if (maxPrice == null || minPrice == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice),
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }


}
