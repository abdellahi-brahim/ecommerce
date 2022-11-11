package com.example.ecommerce.repository;

import com.example.ecommerce.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandService extends JpaRepository<Brand, Long> {
    Brand findBrandById(long id);
}
