package com.vulenhtho.clothesboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "item")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends Base implements Serializable {
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Long price;
    @Column
    private Long amount;


}
