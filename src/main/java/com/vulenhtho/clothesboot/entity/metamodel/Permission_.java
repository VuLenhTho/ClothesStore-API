package com.vulenhtho.clothesboot.entity.metamodel;

import com.vulenhtho.clothesboot.entity.Permission;
import com.vulenhtho.clothesboot.entity.Role;
import com.vulenhtho.clothesboot.entity.User;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permission.class)
public class Permission_ {
    public static volatile SingularAttribute<Permission, User> userId;
    public static volatile SingularAttribute<Permission, Role> roleId;
}
