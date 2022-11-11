package com.example.ecommerce.controller.exception;

public class ProductNotFoundException extends Exception  {
    private final long id;

    public static ProductNotFoundException createWith(long id){
        return new ProductNotFoundException(id);
    }

    private ProductNotFoundException(long id){
        this.id = id;
    }

    @Override
    public String getMessage(){
        return "Product " + id + " not found";
    }
}
