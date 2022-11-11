package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Response {
    private long productId;
    private long brandId;
    private double tax;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private double price;
    public Response(long productId, long brandId, double tax, LocalDateTime startDate, LocalDateTime endDate, double price) {
        this.productId = productId;
        this.brandId = brandId;
        this.tax = tax;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
