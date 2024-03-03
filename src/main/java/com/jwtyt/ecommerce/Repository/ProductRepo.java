package com.jwtyt.ecommerce.Repository;

import com.jwtyt.ecommerce.Models.Product;

import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepo extends CrudRepository<Product,Integer> {

    public  List<Product> findAll(Pageable pageable);
    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String Key1 , String Key2 , Pageable pageable
    );
}
