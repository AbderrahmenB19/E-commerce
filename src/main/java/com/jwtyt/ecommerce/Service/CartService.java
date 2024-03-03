package com.jwtyt.ecommerce.Service;

import com.jwtyt.ecommerce.Exception.ProductNotFoundExeption;
import com.jwtyt.ecommerce.Models.Carts;
import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Models.UserEntity;
import com.jwtyt.ecommerce.Repository.CartRepo;
import com.jwtyt.ecommerce.Repository.ProductRepo;
import com.jwtyt.ecommerce.Repository.UserRepo;
import com.jwtyt.ecommerce.Security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    public Carts addToCart(Integer productId){
        Product product =productRepo.findById(productId).orElseThrow(()-> new ProductNotFoundExeption("Product NotFound "));

        String username= JwtFilter.CURRENT_USER;
        UserEntity user=userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(" user not founded "));
        List<Carts> carts=cartRepo.findByUserEntity(user);
        List<Carts>filtredList=carts.stream().filter(x->x.getProduct().getProductId()==productId).collect(Collectors.toList());
        if(!filtredList.isEmpty()) return null;

        Carts cart= new Carts(product,user);
        return cartRepo.save(cart);

    }

    public List<Carts> getCartDetails(){
        String username= JwtFilter.CURRENT_USER;
        UserEntity user= userRepo.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("user not found "));
        return  cartRepo.findByUserEntity(user);

    }
}
