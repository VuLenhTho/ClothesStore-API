package com.vulenhtho.clothesboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends Base {

    @Column(unique = true, nullable = false)
    private String name;
    @Column()
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<Permission> permissions = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();


}
