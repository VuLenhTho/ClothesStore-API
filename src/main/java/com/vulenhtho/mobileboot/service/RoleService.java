package com.vulenhtho.mobileboot.service;

import com.vulenhtho.mobileboot.entity.Role;
import com.vulenhtho.mobileboot.model.respone.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> findAll();
}
