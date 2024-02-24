package com.productservice.productservice.dto;

import lombok.Setter;
import lombok.Getter;
@Getter
@Setter
public class GenericStoreProduct {
        private Long id;
        private String title;
        private int price;
        private String category;
        private String description;
        private String image;

    }

