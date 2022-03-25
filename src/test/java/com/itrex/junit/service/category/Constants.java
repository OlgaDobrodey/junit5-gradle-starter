package com.itrex.junit.service.category;

import com.itrex.junit.dto.Category;

import java.util.List;

public class Constants {

    protected static final Category TECHNIQUE =
            Category.of(1,"technique", List.of("TV","microPhone"),"test about");
    protected static final Category SCIENCE =
            Category.of(2,"science", List.of("mathematics","physics"),"test about");



}
