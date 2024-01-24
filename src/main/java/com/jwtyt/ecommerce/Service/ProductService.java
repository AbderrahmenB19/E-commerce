package com.jwtyt.ecommerce.Service;

import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    public Product addNewProduct(Product product){
        return  productRepo.save(product);
    }
    public List<Product> getAllProduct (){
        return productRepo.findAll();
    }
    public void deleteProduct(int id){
        productRepo.deleteById(id);
    }
}
