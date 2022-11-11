package com.example.ecommerce.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "base_price")
    private double basePrice;
    @Column(name = "curr")
    private String curr;

    @ManyToOne
    @JoinColumn(name="brand_id", nullable = false)
    private Brand brand;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Set<Price> prices;

    public Product(){

    }

    public Product(String name, int basePrice, String curr){
        this.name = name;
        this.basePrice = basePrice;
        this.curr = curr;
    }

    private void setPrices(Set<Price> prices){
        this.prices = prices;
        prices.forEach(price -> price.setProduct(this));
    }
}
