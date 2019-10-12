package com.vulenhtho.mobileboot.mapper;

import com.vulenhtho.mobileboot.entity.User;
import com.vulenhtho.mobileboot.model.request.UserRequest;
import com.vulenhtho.mobileboot.model.respone.RegisterResponse;
import com.vulenhtho.mobileboot.model.respone.RoleResponse;
import com.vulenhtho.mobileboot.model.respone.UserResponse;
import com.vulenhtho.mobileboot.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private RoleMapper roleMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User transferToUser(UserRequest userRequest){
        User user = new User();
        BeanUtils.refine(userRequest,user, BeanUtils::copyNonNull);

        return user;
    }

    public UserResponse transferToUserResponse(User user){
        UserResponse userResponse = new UserResponse();

        BeanUtils.refine(user, userResponse,BeanUtils::copyNonNull);
        return userResponse;
    }

    public RegisterResponse transferToRegister(User user){
        RegisterResponse registerResponse =new RegisterResponse();

        Set<RoleResponse> roleResponses = user.getRoles().stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toSet());
        BeanUtils.refine(user, registerResponse,BeanUtils::copyNonNull);
        registerResponse.setRoles(roleResponses);
        return registerResponse;
    }
}
