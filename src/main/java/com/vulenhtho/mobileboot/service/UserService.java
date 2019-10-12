package com.vulenhtho.mobileboot.service;

import com.vulenhtho.mobileboot.model.request.UserRequest;
import com.vulenhtho.mobileboot.model.respone.RegisterResponse;
import com.vulenhtho.mobileboot.model.respone.UserResponse;

import java.util.List;

public interface UserService {
    void insert(UserRequest userRequest);

    void update(Long id, UserRequest userRequest);

    void delete(Long id);

    RegisterResponse findUserByUserName(String userName);

    List<UserResponse> findAll();

    UserResponse findUserById(Long id);
}
