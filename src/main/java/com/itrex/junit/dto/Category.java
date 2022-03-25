package com.itrex.junit.dto;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class Category {

    private Integer id;
    private String name;
    private List<String> products;
    private String information;

}
