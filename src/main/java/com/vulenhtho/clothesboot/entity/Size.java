package com.vulenhtho.clothesboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "size")
@Getter@Setter
public class Size extends Base{
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "size")
    private Set<ProductSize> productSizes = new HashSet<>();

    @ManyToMany(mappedBy = "sizes")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

}
