package com.itrex.junit.service.category;

import com.itrex.junit.dto.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

/*
used annotations:
- @ArgumentsSource(NullArgumentsProvider.class)
- @EmptySource only Map, List, Set, String
- @NullAndEmptySource
 */

class CategoryServiceTest {

    private CategoryService categoryService;

    @BeforeEach
    void prepare() {
        categoryService = new CategoryService();
    }

    @Test
    void findAll_returnListOfCategoryIsEmpty() {
        //given && when
        List<Category> categories = categoryService.findAll();

        //then
        assertTrue(categories.isEmpty());

    }

    @Test
    @DisplayName("findAll")
    void findAll_returnListOfCategory() {
        //given
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);

        //when
        List<Category> categories = categoryService.findAll();

        //then
        assertAll("Assert for find all", () -> assertEquals(2, categories.size()), () -> assertEquals(categories.get(0), Constants.TECHNIQUE), () -> assertEquals(categories.get(1), Constants.SCIENCE), () -> assertTrue(categories.contains(Constants.TECHNIQUE)), () -> assertTrue(categories.contains(Constants.SCIENCE)));

    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0, 3, 4})
    void findById_parameterizedTest_returnCategoryIsEmpty(Integer id) {
        //given && when
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);

        //then
        assertTrue(categoryService.findById(id).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void findById_parameterizedTest_returnCategory(Integer id) {
        //given && when
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);

        //then
        assertTrue(categoryService.findById(id).isPresent());
    }

    @Test
    void addAll_returnCategoriesList() {
        //given && when
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);
        List<Category> categories = categoryService.findAll();

        //then
        assertAll("Assert for find all", () -> assertEquals(2, categories.size()), () -> assertEquals(categories.get(0), Constants.TECHNIQUE), () -> assertEquals(categories.get(1), Constants.SCIENCE), () -> assertTrue(categories.contains(Constants.TECHNIQUE)), () -> assertTrue(categories.contains(Constants.SCIENCE)));
    }

    @ParameterizedTest
    @MethodSource("com.itrex.junit.service.category.ArgumentsForCategoryServiceTestMethods#findByNameMethods")
    void findByName_returnCategory(String name, Optional<Category> expected) {
        //given
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);

        //when
        Optional<Category> actual = categoryService.findByName(name);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/name-category.csv", delimiter = ';', numLinesToSkip = 1)
    void findByName_returnCategory(String name, String toString) {
        //given
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);

        //when
        Optional<Category> actual = categoryService.findByName(name);

        //then
        assertEquals(toString, actual.toString());
    }

    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER+" test")
    @CsvSource(value = {"technique;Optional[Category(id=1, name=technique, products=[TV, microPhone], information=test about)]", "science;Optional[Category(id=2, name=science, products=[mathematics, physics], information=test about)]"}, delimiter = ';')
    void findByName_returnCategory_csv(String name, String toString) {
        //given
        categoryService.addAll(Constants.TECHNIQUE, Constants.SCIENCE);

        //when
        Optional<Category> actual = categoryService.findByName(name);

        //then
        assertEquals(toString, actual.toString());
    }

}