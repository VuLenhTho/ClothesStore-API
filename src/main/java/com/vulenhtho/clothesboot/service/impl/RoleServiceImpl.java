package com.vulenhtho.clothesboot.service.impl;

import com.vulenhtho.clothesboot.mapper.RoleMapper;
import com.vulenhtho.clothesboot.model.respone.RoleResponse;
import com.vulenhtho.clothesboot.repository.RoleRepository;
import com.vulenhtho.clothesboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
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
