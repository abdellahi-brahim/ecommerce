package com.example.ecommerce.repository;

import com.example.ecommerce.model.Price;
import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceService extends JpaRepository<Price, Long> {
    List<Price> findPricesByProduct(Product product);
}