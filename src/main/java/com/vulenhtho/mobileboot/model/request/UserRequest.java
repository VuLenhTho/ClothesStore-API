package com.vulenhtho.mobileboot.model.request;

import com.vulenhtho.mobileboot.validation.NOTBLANK;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NOTBLANK
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private boolean sex;
    private boolean status;
    private Long[] ids;

}
