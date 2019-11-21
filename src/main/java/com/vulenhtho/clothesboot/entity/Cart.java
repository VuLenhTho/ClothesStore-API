package com.vulenhtho.clothesboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart extends Base {
    @Column
    private String customerName;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private Date createdDate;
    @Column
    private String status;

    @OneToMany(mappedBy = "cart")
    private Set<Item> items = new HashSet<>();

}
