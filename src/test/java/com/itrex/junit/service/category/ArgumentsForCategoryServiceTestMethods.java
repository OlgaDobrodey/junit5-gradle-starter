package com.itrex.junit.service.category;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.stream.Stream;

public class ArgumentsForCategoryServiceTestMethods {

    static Stream<Arguments> getForAddAllMethods() {
        return Stream.of(

        );
    }

    static Stream<Arguments> findByNameMethods() {
        return Stream.of(
                Arguments.of("technique", Optional.of(Constants.TECHNIQUE)),
                Arguments.of("science", Optional.of(Constants.SCIENCE)),
                Arguments.of("safsg", Optional.empty()),
                Arguments.of(null, Optional.empty())
        );
    }

}
