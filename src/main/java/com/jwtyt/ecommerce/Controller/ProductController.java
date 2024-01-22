package com.jwtyt.ecommerce.Controller;

import com.jwtyt.ecommerce.Models.ImageModel;
import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping(value = "/addNewProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addNewProduct(@RequestPart("product") Product product,
                                                @RequestPart("imageFile")MultipartFile[] file){
        try{
            product.setProductImages(this.uploadImage(file));
            productService.addNewProduct(product);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("the product added avec succes", HttpStatus.OK);

    }
    public  Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels= new HashSet<>();
        for(MultipartFile f: multipartFiles){
            ImageModel imageModel= new ImageModel(
                    f.getOriginalFilename(),
                    f.getContentType(),
                    f.getBytes()
            );
            imageModels.add(imageModel);

        }return imageModels;
    }
}
