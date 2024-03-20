package com.productservice.productservice.controllers;
import com.productservice.productservice.dto.ExceptionDto;
import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericStoreCretaProductDto;
import com.productservice.productservice.dto.GenericStoreProduct;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.services.ProductService;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

//    Constructor Injection
    ProductController(ProductService productService){
        this.productService = productService;
    };

    @GetMapping
    public List <GenericStoreProduct> getAllProducts(){
        System.out.println("Get All Productes");
        return productService.getAllProducts();
    };

    @GetMapping("/{id}")
    public GenericStoreProduct getProductById(@PathVariable("id") Long id)throws ProductNotFoundException{
        // Call the FakeStoreProduct
       System.out.println ("Hello World"+id);
       return productService.getProductById(id);
    }


    @DeleteMapping("/{id}")
    public GenericStoreProduct deleteProductById(@PathVariable("id") Long id){
        System.out.println("Delete Product Controller, Tada");
       return  productService.deleteProductById(id);
    }

    @PostMapping
    public GenericStoreProduct createProduct(@RequestBody GenericStoreCretaProductDto genericStoreCreate){
        System.out.println("Create Product, Tada");
        return productService.createProduct(genericStoreCreate);
    }

    @PutMapping("/{id}")
    public GenericStoreProduct updateProductById(@PathVariable("id") Long id, @RequestBody GenericStoreCretaProductDto genericStoreUpdate){
        System.out.println("Update Product Controller, Tada");
        return productService.updateProductById(id,genericStoreUpdate);
    }

}
