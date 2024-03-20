package com.productservice.productservice.services;

import java.util.ArrayList;
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
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.thirdpartyClient.fakestoreclient.FakeStoreClientAdapter;
 // Add missing import statement

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private FakeStoreClientAdapter fakeStoreAdapter;

    FakeStoreProductService(FakeStoreClientAdapter fakeStoreAdapter){
        this.fakeStoreAdapter = fakeStoreAdapter;
    }

    private String UniProductUrl = "https://fakestoreapi.com/products/{id}";
    private String AllProductsUrl = "https://fakestoreapi.com/products";



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
        return convertToGenericStoreProductDto(fakeStoreAdapter.getProductById(id));

    }

    @Override
    public List <GenericStoreProduct> getAllProducts() {

        List <FakeStoreProductDto> fakeStoreProductDtos = fakeStoreAdapter.getAllProducts();
        List <GenericStoreProduct> genericStoreProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            genericStoreProductDtos.add(convertToGenericStoreProductDto(fakeStoreProductDto));
        }


        return (genericStoreProductDtos);
    }

    // ...

    @Override
    public GenericStoreProduct createProduct(GenericStoreCretaProductDto genericStoreCreate) {
        return convertToGenericStoreProductDto(fakeStoreAdapter.createProduct(genericStoreCreate));

    }

    @Override
    // the url will be uni product url
    // add in the body the product without id
    // returns fakestore api product with updated values
    public GenericStoreProduct updateProductById(Long id, GenericStoreCretaProductDto genericStoreUpdate) {
       return convertToGenericStoreProductDto(fakeStoreAdapter.updateProductById(id, genericStoreUpdate));

        // System.out.println("Welcome to Update Product Section ");
        // RestTemplate restTemplate = restTemplateBuilder.build();
        // HttpEntity<GenericStoreCretaProductDto> requestEntity = new HttpEntity<>(genericStoreUpdate);
        // Map<String, Object> uriVariables = new HashMap<>();
        // uriVariables.put("id", id);
        // ResponseEntity <FakeStoreProductDto> responseEntity = restTemplate.exchange(UniProductUrl, HttpMethod.PUT,requestEntity, FakeStoreProductDto.class, uriVariables);
        // return convertToGenericStoreProductDto(responseEntity.getBody());
    }

    @Override
    public GenericStoreProduct deleteProductById(Long id) {

        return convertToGenericStoreProductDto(fakeStoreAdapter.deleteProductById(id));
}

}