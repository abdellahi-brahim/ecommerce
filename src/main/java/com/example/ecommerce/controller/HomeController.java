package com.example.ecommerce.controller;

import com.example.ecommerce.controller.exception.BrandNotFoundException;
import com.example.ecommerce.controller.exception.ProductNotAvailableForSaleException;
import com.example.ecommerce.controller.exception.ProductNotFoundException;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class HomeController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/brand", method = RequestMethod.POST)
    String addBrand(@RequestBody Brand brand) {
        brandService.save(brand);
        return "Success";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    ResponseEntity<?> checkPrice(@RequestBody Request request) throws ProductNotAvailableForSaleException, BrandNotFoundException, ProductNotFoundException {
        Brand brand = brandService.findBrandById(request.getBrandId());

        if(brand == null) throw BrandNotFoundException.createWith(request.getBrandId());

        Product product = brand.getProducts()
                .stream()
                .filter(p -> request.getProductId() == p.getId()).findFirst().orElse(null);

        if(product == null) throw ProductNotFoundException.createWith(request.getProductId());

        List<Price> prices = new java.util.ArrayList<>(product.getPrices().stream().toList());
        prices.sort((o1, o2) -> o1.getPriority() > o2.getPriority() ? -1 : (o1 == o2) ? 0 : 1);

        Price price = prices.
                stream().
                filter(p -> request.getDate().isAfter(p.getStartDate()) && request.getDate().isBefore(p.getEndDate())).
                findFirst().
                orElse(null);

        if(price == null) throw ProductNotAvailableForSaleException.createWith(request.getProductId());

        Response response = new Response(product.getId(),
                brand.getId(),
                price.getTax(),
                price.getStartDate(),
                price.getEndDate(),
                price.getTax() + product.getBasePrice());

        return ResponseEntity.ok(response);
    }
}
