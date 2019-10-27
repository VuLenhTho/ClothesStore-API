package com.vulenhtho.clothesboot.entity.metamodel;

import com.vulenhtho.clothesboot.entity.Permission;
import com.vulenhtho.clothesboot.entity.Role;
import com.vulenhtho.clothesboot.entity.User;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SetAttribute<User, Role> roles;
    public static volatile SetAttribute<User, Permission> permissions;
}
