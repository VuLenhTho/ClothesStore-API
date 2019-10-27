package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RegisterResponse {
    private String userName;
    private String password;
    private Set<RoleResponse> roles = new HashSet<>();
}
