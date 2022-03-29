package com.itrex.junit.service.allexensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.IOException;

public class ThrowableExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
       if(throwable instanceof IOException){
           System.out.println("it's Exception");
           throw throwable;
       }
        System.out.println("It is OK, work to do");
      }
}
