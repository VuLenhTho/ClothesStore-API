package com.vulenhtho.mobileboot.service.impl;

import com.vulenhtho.mobileboot.entity.Role;
import com.vulenhtho.mobileboot.entity.User;
import com.vulenhtho.mobileboot.mapper.RoleMapper;
import com.vulenhtho.mobileboot.mapper.UserMapper;
import com.vulenhtho.mobileboot.model.request.UserRequest;
import com.vulenhtho.mobileboot.model.respone.RegisterResponse;
import com.vulenhtho.mobileboot.model.respone.RoleResponse;
import com.vulenhtho.mobileboot.model.respone.UserResponse;
import com.vulenhtho.mobileboot.repository.RoleRepository;
import com.vulenhtho.mobileboot.repository.UserRepository;
import com.vulenhtho.mobileboot.service.UserService;
import com.vulenhtho.mobileboot.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;
    private RoleMapper roleMapper;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, BCryptPasswordEncoder encoder, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.roleMapper = roleMapper;
    }

    @Override
    public void insert(UserRequest userRequest) {
        User user = userMapper.transferToUser(userRequest);

        for (Long id : userRequest.getIds()) {
            Role role = roleRepository.getOne(id);
            user.getRoles().add(role);
        }
        Date date = new Date();
        user.setCreatedDate(new Timestamp(date.getTime()));
        user.setStatus(true);
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void update(Long id, UserRequest userRequest) {
        Optional<User> userExist = userRepository.findById(id);

        BeanUtils.refine(userRequest, userExist.get(),BeanUtils::copyNonNull);
        userExist.get().setRoles(new HashSet<>());

        for (Long i : userRequest.getIds()) {
            Role role = roleRepository.getOne(i);
            userExist.get().getRoles().add(role);
        }

        userRepository.save(userExist.get());
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.getOne(id);

        for (Role role : user.getRoles()) {
            role.getUsers().remove(user);
        }
        userRepository.delete(user);
    }

    @Override
    public RegisterResponse findUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        return userMapper.transferToRegister(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            userResponses.add(userMapper.transferToUserResponse(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.getOne(id);
        UserResponse userResponse = new UserResponse();
        Set<RoleResponse> roleResponses = user.getRoles().stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toSet());
        userResponse.setRoles(roleResponses);
        BeanUtils.refine(user,userResponse,BeanUtils::copyNonNull);
        return userResponse;
    }
}
