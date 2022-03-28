package com.itrex.junit.service.category;

import com.itrex.junit.dto.Category;

import java.util.List;

public class Constants {

    public static final Category TECHNIQUE =
            Category.of(1,"technique", List.of("TV","microPhone"),"test about");
    public static final Category SCIENCE =
            Category.of(2,"science", List.of("mathematics","physics"),"test about");



}
