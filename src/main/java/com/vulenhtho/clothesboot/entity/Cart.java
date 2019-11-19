package com.vulenhtho.clothesboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart extends Base{
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
