package com.vulenhtho.clothesboot.specification;

import com.vulenhtho.clothesboot.entity.Permission;
import com.vulenhtho.clothesboot.entity.User;
import com.vulenhtho.clothesboot.entity.metamodel.Permission_;
import com.vulenhtho.clothesboot.entity.metamodel.User_;
import com.vulenhtho.clothesboot.model.request.UserFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;

public class UserSpecification {
    public static Specification<User> filterUser(UserFilterRequest filter) {
        if (filter.getSearch() != null) {
            return Specification
                    .where(withSex(filter.getSex()))
                    .and(withStatus(filter.getStatus()))
                    .and(witRole(filter.getRole()))
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

    public static Specification<User> witRole(Long roleId) {
        if (roleId == null)
            return null;

        return (root, cq, cb)
                -> {
            SetJoin<User, Permission> join = root.join(User_.permissions);
            Predicate equalPredicate = cb.equal(join.get(Permission_.roleId), roleId);
            cq.distinct(true);
            return equalPredicate;

        };
    }


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
