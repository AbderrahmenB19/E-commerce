package com.jwtyt.ecommerce.Models;

import com.jwtyt.ecommerce.Repository.ImageModelRepo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String productDescription;
    private Double productDiscount ;
    private Double productActualPrice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="product_images" ,
            joinColumns = {@JoinColumn(name="product_ID")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Set<ImageModel>  productImages;


}
