package com.productservice.productservice.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericStoreCretaProductDto;
import com.productservice.productservice.dto.GenericStoreProduct;
import org.springframework.http.ResponseEntity; // Add missing import statement

import com.productservice.productservice.exceptions.ProductNotFoundException;;;;;
 // Add missing import statement

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplateBuilder  restTemplateBuilder;
    private String UniProductUrl = "https://fakestoreapi.com/products/{id}";
    private String AllProductsUrl = "https://fakestoreapi.com/products";

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
    public GenericStoreProduct getProductById(Long id) throws ProductNotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("Hello World Fake"+id);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(UniProductUrl, FakeStoreProductDto.class ,id);
    //    breakpoint

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if (fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with id:.."+ id + " doesn't exist");
        }
        return convertToGenericStoreProductDto(fakeStoreProductDto);

    }

    @Override
    public List <FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("Hello World Fake All Products");
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(AllProductsUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = responseEntity.getBody();
        List <FakeStoreProductDto> productList = List.of(products);
        return productList;
        // throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    // ...

    @Override
    public GenericStoreProduct createProduct(GenericStoreCretaProductDto genericStoreCreate) {

        System.out.println("Hello World Fake Create Product");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.postForEntity(AllProductsUrl, genericStoreCreate, FakeStoreProductDto.class);
        return convertToGenericStoreProductDto(responseEntity.getBody());
        // throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    // the url will be uni product url
    // add in the body the product without id
    // returns fakestore api product with updated values
    public GenericStoreProduct updateProductById(Long id, GenericStoreCretaProductDto genericStoreUpdate) {
        System.out.println("Welcome to Update Product Section ");
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<GenericStoreCretaProductDto> requestEntity = new HttpEntity<>(genericStoreUpdate);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.exchange(UniProductUrl, HttpMethod.PUT,requestEntity, FakeStoreProductDto.class, uriVariables);
        return convertToGenericStoreProductDto(responseEntity.getBody());
    }

    @Override
    public GenericStoreProduct deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.execute(UniProductUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);
        System.out.println("body of del");
        return convertToGenericStoreProductDto(responseEntity.getBody());

}

}