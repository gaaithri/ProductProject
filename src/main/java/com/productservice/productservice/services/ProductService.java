package com.productservice.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productservice.productservice.dto.GenericStoreProduct;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.dto.GenericStoreCretaProductDto;
@Service
public interface ProductService {

    GenericStoreProduct getProductById(Long id) throws ProductNotFoundException;

    List <GenericStoreProduct> getAllProducts();

    GenericStoreProduct deleteProductById(Long id);

    GenericStoreProduct createProduct(GenericStoreCretaProductDto genericStoreCretaProductDto);

    GenericStoreProduct updateProductById(Long id, GenericStoreCretaProductDto genericStoreUpdate);

}