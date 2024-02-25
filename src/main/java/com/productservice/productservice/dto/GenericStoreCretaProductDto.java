package com.productservice.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericStoreCretaProductDto {
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;

}