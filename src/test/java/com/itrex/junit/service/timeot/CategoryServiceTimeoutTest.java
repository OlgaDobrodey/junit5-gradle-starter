package com.itrex.junit.service.timeot;

import com.itrex.junit.dto.Category;
import com.itrex.junit.service.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/*
used annotations:
- @ArgumentsSource(NullArgumentsProvider.class)
- @EmptySource only Map, List, Set, String
- @NullAndEmptySource
 */

@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
class CategoryServiceTimeoutTest {

    private CategoryService categoryService;

    @BeforeEach
    void prepare() {
        categoryService = new CategoryService();
    }

    @Test
    void findAll_returnListOfCategoryIsEmpty() throws InterruptedException {
        //given && when
        List<Category> categories = categoryService.findAll();
        Thread.sleep(100);

        //then
        assertTrue(categories.isEmpty());
    }

    @Test
    @RepeatedTest(10)
    void findAll_returnListOfCategory_timeout() {

        List<Category> categories = assertTimeout(Duration.ofMillis(200l), () -> categoryService.findAll());

        assertTimeoutPreemptively(Duration.ofMillis(200l), () -> categoryService.findAll()); //diferent thread not good
    }

}