package com.vulenhtho.mobileboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends Base {
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column
    private String fullName;
    @Column
    private boolean sex;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column
    private boolean status;
    @Column
    private String createdBy;
    @Column(nullable = false)
    private Timestamp createdDate;
    @Column
    private String modifiedBy;
    @Column
    private Timestamp modifiedDate;

    @ManyToMany
    @JoinTable(name = "permission" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}
