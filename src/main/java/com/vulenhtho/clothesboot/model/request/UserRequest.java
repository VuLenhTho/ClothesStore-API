package com.vulenhtho.clothesboot.model.request;

import com.vulenhtho.clothesboot.validation.NOTBLANK;
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
    private String creator;
    private Long[] ids;


}
