package com.itrex.junit.service.allexensions;

import com.itrex.junit.service.category.CategoryService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;

public class PostProcessingExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        System.out.println("postProcessTestInstance");
        System.out.println();
        Field[] declaredFields = testInstance.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Override.class)) {
                field.set(testInstance, new CategoryService());
            }
        }

    }
}
