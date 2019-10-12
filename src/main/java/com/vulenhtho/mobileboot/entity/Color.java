package com.vulenhtho.mobileboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "color")
@Getter
@Setter
public class Color extends Base {

    private String name;

    @ManyToMany(mappedBy = "colors")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

   }
