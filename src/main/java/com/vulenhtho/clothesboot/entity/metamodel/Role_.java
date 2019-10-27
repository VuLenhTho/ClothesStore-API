package com.vulenhtho.clothesboot.entity.metamodel;

import com.vulenhtho.clothesboot.entity.Permission;
import com.vulenhtho.clothesboot.entity.Role;
import com.vulenhtho.clothesboot.entity.User;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
    public static volatile SingularAttribute<Role, Long> id;
    public static volatile SetAttribute<Role, User> users;
    public static volatile SetAttribute<Role, Permission> permissions;
}
