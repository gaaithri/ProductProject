package com.productservice.productservice.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.productservice.productservice.dto.FakeStoreProductDto;

@Service
public class FakeStoreProductServiceImpl implements  ProductService{

    @Override
    public FakeStoreProductDto getProductById(Long id) {
        return ;

    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProductById(Long id) {

    }
}
