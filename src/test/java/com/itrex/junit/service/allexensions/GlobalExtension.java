package com.itrex.junit.service.allexensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class GlobalExtension implements BeforeAllCallback, AfterTestExecutionCallback {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("prepare before all"+ context.getRequiredTestClass());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("after test execution");
        System.out.println(context.getTestMethod().get().getName());
    }
}
