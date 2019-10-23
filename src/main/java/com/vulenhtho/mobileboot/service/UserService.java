package com.vulenhtho.mobileboot.service;

import com.vulenhtho.mobileboot.model.request.UserFilterRequest;
import com.vulenhtho.mobileboot.model.request.UserRequest;
import com.vulenhtho.mobileboot.model.respone.RegisterResponse;
import com.vulenhtho.mobileboot.model.respone.UserFilterResponse;
import com.vulenhtho.mobileboot.model.respone.UserIdResponse;
import com.vulenhtho.mobileboot.model.respone.UserResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    void insert(UserRequest userRequest);

    void update(Long id, UserRequest userRequest);

    void delete(Long id);

    void delete(UserIdResponse ids);

    RegisterResponse findUserByUserName(String userName);

    List<UserResponse> findAll();

    UserFilterResponse findAllWithFilter(UserFilterRequest filterRequest);

    UserResponse findUserById(Long id);
}
