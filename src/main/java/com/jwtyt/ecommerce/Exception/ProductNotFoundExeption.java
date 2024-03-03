package com.jwtyt.ecommerce.Exception;

import com.jwtyt.ecommerce.Repository.ProductRepo;

public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption( String message){
        super(message);
    }
}
