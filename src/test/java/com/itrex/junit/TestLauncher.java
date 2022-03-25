package com.itrex.junit;

import com.itrex.junit.service.user.UserServiceExceptionsTest;
import com.itrex.junit.service.user.UserServiceTest;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

public class TestLauncher {

    public static void main(String[] args) {
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();


        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectClass(UserServiceTest.class))
                .selectors(DiscoverySelectors.selectClass(UserServiceExceptionsTest.class))
                //                .selectors(DiscoverySelectors.selectDirectory("com.itrex.junit.service"))
                .filters(TagFilter.includeTags("login"))
                .build();
        launcher.execute(request, listener);
        try (var writer = new PrintWriter(System.out)) {
            listener.getSummary().printTo(writer);
        }
    }
}
