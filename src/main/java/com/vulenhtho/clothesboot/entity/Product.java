package com.vulenhtho.clothesboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends Base {
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private Long importPrice;
    @Column
    private Long price;

    @Column
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column()
    private String status;
    @Column
    private String thumbnail;
    @Column
    private String photoList;
    @Column
    private String sex;
    @Column
    private String createdBy;
    @Column
    private Timestamp createdDate;
    @Column
    private String modifiedBy;
    @Column
    private Timestamp modifiedDate;
    @Column
    private Long hot;
    @Column
    private boolean trend;

    @OneToMany(mappedBy = "product")
    private Set<ProductColorSize> productColorSizes = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Item> items = new HashSet<>();


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

    @ManyToMany
    @JoinTable(name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private Set<Size> sizes = new HashSet<>();
}
