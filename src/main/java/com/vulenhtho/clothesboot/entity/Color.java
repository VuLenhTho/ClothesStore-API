package com.vulenhtho.clothesboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "color")
@Getter
@Setter
public class Color extends Base {
    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "color")
    private Set<ProductColor> productColors = new HashSet<>();

    @ManyToMany(mappedBy = "colors")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

}
