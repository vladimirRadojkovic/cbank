package com.mybank.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String MULTIPART_CONFIG_LOCATION = "";
    private static final long MULTIPART_MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final long MULTIPART_MAX_REQUEST_SIZE = 20 * 1024 * 1024; // 20MB
    private static final int MULTIPART_FILE_SIZE_THRESHOLD = 5 * 1024 * 1024; // 5MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { 
            DatabaseConfig.class, 
            SecurityConfig.class,
            LocalizationConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(
            new MultipartConfigElement(
                MULTIPART_CONFIG_LOCATION,
                MULTIPART_MAX_FILE_SIZE,
                MULTIPART_MAX_REQUEST_SIZE,
                MULTIPART_FILE_SIZE_THRESHOLD
            )
        );
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { 
            new DelegatingFilterProxy("springSecurityFilterChain")
        };
    }
} 