package com.productservice.productservice.thirdpartyClient.fakestoreclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericStoreCretaProductDto;
import com.productservice.productservice.dto.GenericStoreProduct;
import com.productservice.productservice.exceptions.ProductNotFoundException;

@Component
public class FakeStoreClientAdapter {

   private RestTemplateBuilder  restTemplateBuilder;
    private String UniProductUrl = "https://fakestoreapi.com/products/{id}";
    private String AllProductsUrl = "https://fakestoreapi.com/products";

    FakeStoreClientAdapter(RestTemplateBuilder restTemplateBuilder)  {
        this.restTemplateBuilder = restTemplateBuilder;
    }

   


    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("Hello World Fake"+id);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(UniProductUrl, FakeStoreProductDto.class ,id);
    //    breakpoint

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if (fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with id:.."+ id + " doesn't exist");
        }
        return fakeStoreProductDto;

    }


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

    public FakeStoreProductDto createProduct(GenericStoreCretaProductDto genericStoreCreate) {

        System.out.println("Hello World Fake Create Product");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.postForEntity(AllProductsUrl, genericStoreCreate, FakeStoreProductDto.class);
        return (responseEntity.getBody());
        // throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    // the url will be uni product url
    // add in the body the product without id
    // returns fakestore api product with updated values
    public FakeStoreProductDto updateProductById(Long id, GenericStoreCretaProductDto genericStoreUpdate) {
        System.out.println("Welcome to Update Product Section ");
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<GenericStoreCretaProductDto> requestEntity = new HttpEntity<>(genericStoreUpdate);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.exchange(UniProductUrl, HttpMethod.PUT,requestEntity, FakeStoreProductDto.class, uriVariables);
        return (responseEntity.getBody());
    }


    public FakeStoreProductDto deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.execute(UniProductUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);
        System.out.println("body of del");
        return responseEntity.getBody();

}

}
