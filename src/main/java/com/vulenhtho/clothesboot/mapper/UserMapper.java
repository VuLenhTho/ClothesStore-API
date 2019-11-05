package com.vulenhtho.clothesboot.mapper;

import com.vulenhtho.clothesboot.entity.Role;
import com.vulenhtho.clothesboot.entity.User;
import com.vulenhtho.clothesboot.model.request.UserRequest;
import com.vulenhtho.clothesboot.model.respone.RegisterResponse;
import com.vulenhtho.clothesboot.model.respone.RoleResponse;
import com.vulenhtho.clothesboot.model.respone.UserResponse;
import com.vulenhtho.clothesboot.repository.RoleRepository;
import com.vulenhtho.clothesboot.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private RoleMapper roleMapper;
    private RoleRepository roleRepository;

    @Autowired
    public UserMapper(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public User transferToUser(UserRequest userRequest, User user){
        BeanUtils.refine(userRequest,user, BeanUtils::copyNonNull);

        if (userRequest.getIds() != null){
            user.setRoles(new HashSet<>());
            for (Long id : userRequest.getIds()) {
                Role role = roleRepository.getOne(id);
                user.getRoles().add(role);
            }
        }

        return user;
    }

    public UserResponse transferToUserResponse(User user){
        UserResponse userResponse = new UserResponse();

        BeanUtils.refine(user, userResponse,BeanUtils::copyNonNull);
        return userResponse;
    }

    public List<UserResponse> toUsersResponse(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(transferToUserResponse(user));
        }
        return userResponses;
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
