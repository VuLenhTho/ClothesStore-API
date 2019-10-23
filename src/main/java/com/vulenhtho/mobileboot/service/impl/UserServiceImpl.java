package com.vulenhtho.mobileboot.service.impl;

import com.vulenhtho.mobileboot.entity.Role;
import com.vulenhtho.mobileboot.entity.User;
import com.vulenhtho.mobileboot.mapper.RoleMapper;
import com.vulenhtho.mobileboot.mapper.UserMapper;
import com.vulenhtho.mobileboot.model.request.UserFilterRequest;
import com.vulenhtho.mobileboot.model.request.UserRequest;
import com.vulenhtho.mobileboot.model.respone.*;
import com.vulenhtho.mobileboot.repository.RoleRepository;
import com.vulenhtho.mobileboot.repository.UserRepository;
import com.vulenhtho.mobileboot.service.UserService;
import com.vulenhtho.mobileboot.specification.UserSpecification;
import com.vulenhtho.mobileboot.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void delete(UserIdResponse ids) {
        for (Long id : ids.getIds()) {
            User user = userRepository.getOne(id);

            for (Role role : user.getRoles()) {
                role.getUsers().remove(user);
            }
            userRepository.delete(user);
        }
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
    public UserFilterResponse findAllWithFilter (UserFilterRequest filterRequest) {
        UserFilterResponse userFilterResponse = new UserFilterResponse();

        List<User> users = userRepository.findAll(UserSpecification.filterUser(filterRequest)
                ,PageRequest.of(
                        filterRequest.getPage()
                        ,filterRequest.getSize()
                        ,sort(filterRequest.getSort()))).getContent();

        List<User> allUser = userRepository.findAll(UserSpecification.filterUser(filterRequest));

        List<UserResponse> userResponses = users.stream()
                .map(userMapper::transferToUserResponse)
                .collect(Collectors.toList());

        int total = (int) Math.ceil((double)allUser.size() / filterRequest.getSize());

        userFilterResponse.setCurrentPage(filterRequest.getPage());
        userFilterResponse.setTotalPages(total);
        userFilterResponse.setUsers(userResponses);
        return userFilterResponse;
    }

    private Sort sort(String typeDateSort){
        if (typeDateSort != null){
            if (typeDateSort.equals("date-des")){
                return Sort.by("createdDate").descending();
            }else if (typeDateSort.equals("date-asc")){
                return Sort.by("createdDate").ascending();
            }
        }

        return Sort.by("createdDate").descending();
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

    private boolean isNull(Objects objects){
        return objects == null;
    }
}
