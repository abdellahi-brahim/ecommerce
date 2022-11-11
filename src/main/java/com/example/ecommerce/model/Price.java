package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PRICE")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    @Column(name = "tax")
    private double tax;
    @Column(name = "priority")
    private int priority;

    @ManyToOne
    @JoinColumn(name="products_id", nullable = false)
    private Product product;

    public Price(){}

    public Price(LocalDateTime startDate, LocalDateTime endDate, int tax){
        this.startDate = startDate;
        this.endDate = endDate;
        this.tax = tax;
    }
}
