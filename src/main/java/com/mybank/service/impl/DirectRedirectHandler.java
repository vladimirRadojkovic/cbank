package com.mybank.service.impl;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Direct implementation of AuthenticationSuccessHandler with hardcoded redirects
 */
public class DirectRedirectHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        // Default URL if no matching roles are found
        String targetUrl = "/pocetna";
        
        boolean isAdmin = false;
        boolean isUser = false;
        
        // Check each authority
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority auth : authorities) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                isAdmin = true;
            }
            if ("ROLE_USER".equals(auth.getAuthority())) {
                isUser = true;
            }
        }
        
        // Hardcoded logic for URL determination
        if (isAdmin && isUser) {
            // User has both roles - redirect to the role selection page
            targetUrl = "/redirect";
        } else if (isAdmin) {
            // User is admin only
            targetUrl = "/admin";
        } else if (isUser) {
            // User is regular user only
            targetUrl = "/pocetna";
        }
        
        // Handle context path
        if (request.getContextPath() != null && !request.getContextPath().isEmpty()) {
            targetUrl = request.getContextPath() + targetUrl;
        }
        
        // Perform the redirect
        response.sendRedirect(targetUrl);
    }
} 