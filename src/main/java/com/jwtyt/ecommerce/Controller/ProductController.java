package com.jwtyt.ecommerce.Controller;

import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/addNewProduct")
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
        return new ResponseEntity<>("the product added avec succes", HttpStatus.OK);
    }
}
