package com.jwtyt.ecommerce.Service;

import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    public Product addNewProduct(Product product){
        return  productRepo.save(product);
    }
}
