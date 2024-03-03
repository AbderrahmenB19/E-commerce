package com.jwtyt.ecommerce.Controller;

import com.jwtyt.ecommerce.Models.Carts;
import com.jwtyt.ecommerce.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;
    @PreAuthorize("hasRole('User')")
    @GetMapping("/addToCart/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable(name = "productId") Integer productId){
        cartService.addToCart(productId);
        return new ResponseEntity<>("cart saved", HttpStatus.OK);

    }
    @GetMapping("/getCartDetails")
    public ResponseEntity<List<Carts>> getCartDetails(){
        return  new ResponseEntity<>(cartService.getCartDetails(),HttpStatus.OK);


    }

}
