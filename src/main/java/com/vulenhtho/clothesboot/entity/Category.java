package com.vulenhtho.clothesboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter@Setter
public class Category extends Base {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<SubCategory> subCategories = new HashSet<>();
}
