package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.Role;
import com.vulenhtho.clothesboot.model.respone.RoleResponse;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse transferToRoleResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        BeanUtils.refine(role, roleResponse, BeanUtils::copyNonNull);
        return roleResponse;
    }
}
