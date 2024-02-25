package com.productservice.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.productservice.productservice.dto.FakeStoreProductDto;
import com.productservice.productservice.dto.GenericStoreProduct;
import com.productservice.productservice.dto.GenericStoreCretaProductDto;
@Service
public interface ProductService {

    GenericStoreProduct getProductById(Long id);


    List <FakeStoreProductDto> getAllProducts();


    void deleteProductById();


    GenericStoreProduct createProduct(GenericStoreCretaProductDto genericStoreCretaProductDto);



    void updateProductById(Long id);

}