package com.itrex.junit.service.category;

import com.itrex.junit.dto.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryService {

    List<Category> categories = new ArrayList<>();

    public List<Category> findAll() {
        return categories;
    }

    public Optional<Category> findById(Integer id) {
        return categories
                .stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }

    public boolean addAll(Category... addCategories) {
        return categories.addAll(List.of(addCategories));
    }

    public Optional<Category> findByName(String name){
        return categories
                .stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();
    }

    public boolean addAllProductsForCategory(Category category, String... products) {
        return true;
    }
}
