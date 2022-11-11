package com.example.ecommerce.controller.exception;

public class BrandNotFoundException extends Exception  {
    private final long id;

    public static BrandNotFoundException createWith(long id){
        return new BrandNotFoundException(id);
    }

    private BrandNotFoundException(long id){
        this.id = id;
    }

    @Override
    public String getMessage(){
        return "Brand " + id + " not found";
    }
}
