package com.vulenhtho.mobileboot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SubCategory")
public class SubCategory extends Base {
    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

   }
