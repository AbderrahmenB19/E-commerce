package com.jwtyt.ecommerce.Service;

import com.jwtyt.ecommerce.Models.Carts;
import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Models.UserEntity;
import com.jwtyt.ecommerce.Repository.CartRepo;
import com.jwtyt.ecommerce.Repository.ProductRepo;
import com.jwtyt.ecommerce.Repository.UserRepo;
import com.jwtyt.ecommerce.Security.JwtFilter;
import com.jwtyt.ecommerce.Security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    public Product addNewProduct(Product product){
        return  productRepo.save(product);
    }
    public List<Product> getAllProduct (int pageNumber ,String searchKey ){
        Pageable pageable= PageRequest.of(pageNumber,12);
        if(searchKey==""){
            return (List<Product>) productRepo.findAll(pageable);
        }else{
            return (List<Product>) productRepo.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey,searchKey,pageable);

        }




    }
    public void deleteProduct(int id){
        productRepo.deleteById(id);
    }

    public Product getProductDetailsById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }
    public List<Product> getProductDetails(boolean isSingleProductCheckout ,Integer productId){
       if(isSingleProductCheckout && productId!=0){
           List<Product> list= new ArrayList<>();
           Product product=productRepo.findById(productId).get();
           list.add(product);
           return list;

       }else {
           String username= JwtFilter.CURRENT_USER;
           UserEntity user= userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
           List<Carts> carts= cartRepo.findByUserEntity(user);
           for(Carts c : carts){
               System.out.println(c.getProduct().getProductName());
           }
           return carts.stream().map(x->x.getProduct()).collect(Collectors.toList());




       }
    }


}
