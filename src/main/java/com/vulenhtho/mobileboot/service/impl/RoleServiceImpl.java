package com.vulenhtho.mobileboot.service.impl;

import com.vulenhtho.mobileboot.mapper.RoleMapper;
import com.vulenhtho.mobileboot.model.respone.RoleResponse;
import com.vulenhtho.mobileboot.repository.RoleRepository;
import com.vulenhtho.mobileboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl  implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toList());
    }
}
