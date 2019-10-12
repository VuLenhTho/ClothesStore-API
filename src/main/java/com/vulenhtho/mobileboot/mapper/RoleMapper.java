package com.vulenhtho.mobileboot.mapper;

import com.vulenhtho.mobileboot.entity.Role;
import com.vulenhtho.mobileboot.model.respone.RoleResponse;
import com.vulenhtho.mobileboot.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse transferToRoleResponse(Role role){
        RoleResponse roleResponse = new RoleResponse();
        BeanUtils.refine(role,roleResponse,BeanUtils::copyNonNull);
        return roleResponse;
    }
}
