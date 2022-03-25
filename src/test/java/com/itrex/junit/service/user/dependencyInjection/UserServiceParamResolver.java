package com.itrex.junit.service.user.dependencyInjection;

import com.itrex.junit.service.user.UserService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class UserServiceParamResolver implements ParameterResolver {
//UserServiceParamResolver -  Singleton scope
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == UserService.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
       //do new Object for each method
        var store = extensionContext.getStore(ExtensionContext.Namespace.create(extensionContext.getTestMethod()));
       //return Map<key:class,value:new Object>
        return store.getOrComputeIfAbsent(UserService.class, it -> new UserService());
    }
}
