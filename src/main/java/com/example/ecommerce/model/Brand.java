package com.example.ecommerce.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "BRAND")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private Set<Product> products;

    public Brand(){

    }

    public Brand(String name){
        this.name = name;
    }

    public void setProducts(Set<Product> products){
        this.products = products;
        products.forEach(product -> product.setBrand(this));
    }
}
