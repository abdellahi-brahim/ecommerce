package com.example.ecommerce.controller.exception;

public class ProductNotAvailableForSaleException extends Exception  {
    private final long id;

    public static ProductNotAvailableForSaleException createWith(long id){
        return new ProductNotAvailableForSaleException(id);
    }

    private ProductNotAvailableForSaleException(long id){
        this.id = id;
    }

    @Override
    public String getMessage(){
        return "Product " + id + " is not available for sale";
    }
}
