package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String userName;
    private String fullName;
    private String createdBy;
    private Date createdDate;
    private String phone;
    private String email;
    private boolean status;
    private boolean sex;
    private Set<RoleResponse> roles = new HashSet<>();


}
