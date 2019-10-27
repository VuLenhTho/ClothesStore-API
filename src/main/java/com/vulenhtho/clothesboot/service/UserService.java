package com.vulenhtho.clothesboot.service;

import com.vulenhtho.clothesboot.model.request.UserFilterRequest;
import com.vulenhtho.clothesboot.model.request.UserRequest;
import com.vulenhtho.clothesboot.model.respone.RegisterResponse;
import com.vulenhtho.clothesboot.model.respone.UserFilterResponse;
import com.vulenhtho.clothesboot.model.respone.UserIdResponse;
import com.vulenhtho.clothesboot.model.respone.UserResponse;

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
