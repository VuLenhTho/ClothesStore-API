package com.vulenhtho.clothesboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Discount extends Base {
    @Column(nullable = false,unique = true)
    private String name;
    @Column
    private Long percent;
    @Column
    private Long amount;
    @Column
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private Set<ProductDiscount> productDiscounts = new HashSet<>();

    @ManyToMany(mappedBy = "discounts")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

   }
