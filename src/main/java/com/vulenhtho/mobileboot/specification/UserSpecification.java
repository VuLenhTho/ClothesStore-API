package com.vulenhtho.mobileboot.specification;

import com.vulenhtho.mobileboot.entity.User;
import com.vulenhtho.mobileboot.model.request.UserFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> filterUser(UserFilterRequest filter) {
        if (filter.getSearch() != null) {
            return Specification
                    .where(withSex(filter.getSex()))
                    .and(withStatus(filter.getStatus()))
                    .and(withCreatedBy(filter.getSearch())
                            .or(withEmail(filter.getSearch()))
                            .or(withFullName(filter.getSearch()))
                            .or(withPhone(filter.getSearch()))
                            .or(withUserName(filter.getSearch()))
                    );
        }
        return Specification
                .where(withSex(filter.getSex()))
                .and(withStatus(filter.getStatus()));
    }

//    public static Specification<User> witRole(String role) {
//        if (role == null)
//            return null;
//
//        return (root, criteriaQuery, criteriaBuilder)
//                -> criteriaBuilder.equal(root.get("role")., role);
//    }


    public static Specification<User> withStatus(Boolean status) {
        if (status == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<User> withSex(Boolean sex) {
        if (sex == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("sex"), sex);
    }

    public static Specification<User> withCreatedBy(String search) {
        if (search == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("createdBy"), "%" + search + "%");
    }

    public static Specification<User> withUserName(String search) {
        if (search == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("userName"), "%" + search + "%");
    }

    public static Specification<User> withFullName(String search) {
        if (search == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("fullName"), "%" + search + "%");
    }

    public static Specification<User> withEmail(String search) {
        if (search == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + search + "%");
    }

    public static Specification<User> withPhone(String search) {
        if (search == null)
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("phone"), "%" + search + "%");
    }


}
