package com.vulenhtho.mobileboot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends Base{
    @Column(nullable = false)
    private String name;
    @Column()
    private String technicalData;
    @Column()
    private String description;
    @Column
    private Long importPrice;
    @Column
    private Long price;
    @Column
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

    @Column
    private String promotion;

    @Column(nullable = false)
    private String status;
    @Column
    private String thumbnail;
    @Column
    private String photoList;
    @Column(nullable = false)
    private String state;
    @Column
    private String createdBy;
    @Column
    private Timestamp createdDate;
    @Column
    private String modifiedBy;
    @Column
    private Timestamp modifiedDate;

    @ManyToMany
    @JoinTable(name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private Set<Color> colors = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "product_discount",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id"))
    private Set<Discount> discounts = new HashSet<>();
}
