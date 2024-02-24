package com.productservice.productservice.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericStoreProduct;
import org.springframework.http.ResponseEntity; // Add missing import statement
 // Add missing import statement

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplateBuilder  restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductsUrl = "https://fakestoreapi.com/products";

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder)  {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public static GenericStoreProduct convertToGenericStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericStoreProduct genericStoreProductDto = new GenericStoreProduct();
        System.out.println("Hello World Fake Generic"+fakeStoreProductDto.getId());
        genericStoreProductDto.setId(fakeStoreProductDto.getId());
        genericStoreProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericStoreProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericStoreProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericStoreProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericStoreProductDto.setImage(fakeStoreProductDto.getImage());
        return genericStoreProductDto;
    }



    @Override
    public GenericStoreProduct getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("Hello World Fake"+id);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class ,id);
    //    breakpoint
        return convertToGenericStoreProductDto(responseEntity.getBody());

    }

    // ...

    // ...

    @Override
    public List <FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("Hello World Fake All Products");
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = responseEntity.getBody();
        List <FakeStoreProductDto> productList = List.of(products);
        return productList;
        // throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    // ...

    @Override
    public void createProduct() {
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    public void updateProductById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'updateProductById'");
    }

    @Override
    public void deleteProductById() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductById'");
    }



}
