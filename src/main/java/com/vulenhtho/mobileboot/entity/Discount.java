package com.vulenhtho.mobileboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
    private String content;

    @ManyToMany(mappedBy = "discounts")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

   }
