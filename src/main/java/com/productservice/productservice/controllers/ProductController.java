package com.productservice.productservice.controllers;
import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericStoreCretaProductDto;
import com.productservice.productservice.dto.GenericStoreProduct;
import com.productservice.productservice.services.ProductService;
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
    public List <FakeStoreProductDto> getAllProducts(){
        System.out.println("Get All Productes");
        return productService.getAllProducts();
    };

    @GetMapping("/{id}")
    public GenericStoreProduct getProductById(@PathVariable("id") Long id){
        // Call the FakeStoreProduct
       System.out.println ("Hello World"+id);
       return productService.getProductById(id);
    }



    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id){


    }

    @PostMapping
    public GenericStoreProduct createProduct(@RequestBody GenericStoreCretaProductDto genericStoreCreate){
        System.out.println("Create Product, Tada");
        return productService.createProduct(genericStoreCreate);
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}
