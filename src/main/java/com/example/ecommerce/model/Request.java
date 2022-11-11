package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Request {
    private long productId;
    private long brandId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public Request(long productId, long brandId, LocalDateTime date){
        this.productId = productId;
        this.brandId = brandId;
        this.date = date;
    }

    public Request() {}
}
