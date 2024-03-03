package com.jwtyt.ecommerce.Controller;

import com.jwtyt.ecommerce.Models.ImageModel;
import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/addNewProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addNewProduct(@RequestPart("product") Product product,
                                                @RequestPart("imageFile") MultipartFile[] file) {
        try {
            product.setProductImages(this.uploadImage(file));
            productService.addNewProduct(product);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("the product added avec succes", HttpStatus.OK);

    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile f : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    f.getOriginalFilename(),
                    f.getContentType(),
                    f.getBytes()

            );
            imageModels.add(imageModel);

        }
        return imageModels;
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(
            @RequestParam(defaultValue = "0") int  pageNumber,
            @RequestParam (defaultValue = "") String searchKey
    ) {

        return new ResponseEntity<>(productService.getAllProduct(pageNumber, searchKey), HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getProductDetailsById/{id}")
    public ResponseEntity<Product> getProductDetailsById(@PathVariable("id") Integer id) {
        Product product = productService.getProductDetailsById(id);
        return (product != null) ?
                new ResponseEntity<>(product, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deletProduct(@PathVariable("productId") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("succes", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout ,
                                  @PathVariable(name = "productId") Integer productId){
        return productService.getProductDetails(isSingleProductCheckout,productId);

    }
}

