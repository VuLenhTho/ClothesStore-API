package com.vulenhtho.clothesboot.service.impl;

import com.vulenhtho.clothesboot.entity.Role;
import com.vulenhtho.clothesboot.entity.User;
import com.vulenhtho.clothesboot.mapper.RoleMapper;
import com.vulenhtho.clothesboot.mapper.UserMapper;
import com.vulenhtho.clothesboot.model.request.UserFilterRequest;
import com.vulenhtho.clothesboot.model.request.UserRequest;
import com.vulenhtho.clothesboot.model.respone.*;
import com.vulenhtho.clothesboot.repository.RoleRepository;
import com.vulenhtho.clothesboot.repository.UserRepository;
import com.vulenhtho.clothesboot.service.UserService;
import com.vulenhtho.clothesboot.specification.UserSpecification;
import com.vulenhtho.clothesboot.util.BeanUtils;
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
        Date date = new Date();
        User user = userMapper.transferToUser(userRequest);

        for (Long id : userRequest.getIds()) {
            Role role = roleRepository.getOne(id);
            user.getRoles().add(role);
        }

        user.setCreatedDate(new Timestamp(date.getTime()));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedBy(userRequest.getCreator());

        userRepository.save(user);
    }

    @Override
    public void update(Long id, UserRequest userRequest) {
        Date date = new Date();
        Optional<User> userExist = userRepository.findById(id);

        BeanUtils.refine(userRequest, userExist.get(),BeanUtils::copyNonNull);
//        set role of user exist = empty
        userExist.get().setRoles(new HashSet<>());
//         set new role
        for (Long i : userRequest.getIds()) {
            Role role = roleRepository.getOne(i);
            userExist.get().getRoles().add(role);
        }
        userExist.get().setPassword(encoder.encode(userExist.get().getPassword()));
        userExist.get().setModifiedDate(new Timestamp(date.getTime()));
        userExist.get().setModifiedBy(userRequest.getCreator());

        userRepository.save(userExist.get());
    }

    @Override
    public void delete(Long id) {
        deleteOneUser(id);
    }

    @Override
    public void delete(UserIdResponse ids) {
        for (Long id : ids.getIds()) {
            deleteOneUser(id);
        }
    }

    private void deleteOneUser(Long id){
        Optional<User> user = userRepository.findById(id);

        for (Role role : user.get().getRoles()) {
            role.getUsers().remove(user);
        }
        userRepository.delete(user.get());
    }

    @Override
    public RegisterResponse findUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        return userMapper.transferToRegister(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return toUserResponse(userRepository.findAll());
    }

    @Override
    public UserFilterResponse findAllWithFilter (UserFilterRequest filterRequest) {
        UserFilterResponse userFilterResponse = new UserFilterResponse();

        List<User> users = userRepository.findAll(UserSpecification.filterUser(filterRequest)
                ,PageRequest.of(
                        filterRequest.getPage()
                        ,filterRequest.getSize()
                        ,sort(filterRequest.getSort())
                )).getContent();

        Long countAllUser = userRepository.count(UserSpecification.filterUser(filterRequest));
        int total = (int) Math.ceil((double)countAllUser / filterRequest.getSize());
        userFilterResponse.setCurrentPage(filterRequest.getPage());
        userFilterResponse.setTotalPages(total);
        userFilterResponse.setUsers(toUserResponse(users));
        return userFilterResponse;
    }

    private List<UserResponse> toUserResponse(List<User> users){
        return users.stream()
                .map(userMapper::transferToUserResponse)
                .collect(Collectors.toList());
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
        Set<RoleResponse> roleResponses = user.getRoles()
                .stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toSet());
        userResponse.setRoles(roleResponses);
        BeanUtils.refine(user,userResponse,BeanUtils::copyNonNull);
        return userResponse;
    }

}
