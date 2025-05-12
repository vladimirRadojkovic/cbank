package com.mybank.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RedirectService implements AuthenticationSuccessHandler {

    private Map<String, String> roleUrlMap;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        System.out.println("******************************");
        System.out.println("* REDIRECT SERVICE CALLED!!! *");
        System.out.println("******************************");
        
        // Debug roleUrlMap
        System.out.println("DEBUG: roleUrlMap contents:");
        if (roleUrlMap != null) {
            for (Map.Entry<String, String> entry : roleUrlMap.entrySet()) {
                System.out.println("DEBUG: Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        } else {
            System.out.println("DEBUG: roleUrlMap is NULL!");
        }
        
        String targetUrl = "/pocetna"; // Default
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = false;
        boolean isUser = false;
        
        // Get username for logging
        String username = "";
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            username = authentication.getPrincipal().toString();
        }
        System.out.println("DEBUG: Authentication for user: " + username);
        System.out.println("DEBUG: Roles detected:");
        
        for (GrantedAuthority auth : authorities) {
            System.out.println("Authority: " + auth.getAuthority());
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                isAdmin = true;
                System.out.println("DEBUG: ROLE_ADMIN detected");
            }
            if ("ROLE_USER".equals(auth.getAuthority())) {
                isUser = true;
                System.out.println("DEBUG: ROLE_USER detected");
            }
        }
        
        System.out.println("DEBUG: isAdmin = " + isAdmin);
        System.out.println("DEBUG: isUser = " + isUser);
        
        // Try different key combinations for dual role
        String roleKey = null;
        if (isAdmin && isUser) {
            System.out.println("DEBUG: User has both roles, trying various key formats:");
            
            // Try different key formats
            String[] keyFormats = {
                "ROLE_ADMIN,ROLE_USER",
                "ROLE_USER,ROLE_ADMIN",
                "ROLE_ADMIN, ROLE_USER",
                "ROLE_USER, ROLE_ADMIN"
            };
            
            for (String format : keyFormats) {
                System.out.println("DEBUG: Trying key format: " + format);
                if (roleUrlMap.containsKey(format)) {
                    roleKey = format;
                    System.out.println("DEBUG: Found matching key: " + roleKey);
                    break;
                }
            }
            
            if (roleKey != null) {
                targetUrl = roleUrlMap.get(roleKey);
                System.out.println("DEBUG: Using targetUrl from key " + roleKey + ": " + targetUrl);
            } else {
                // Fallback to explicit URL
                targetUrl = "/dualuser";
                System.out.println("DEBUG: No matching key found in roleUrlMap, using hardcoded /dualuser");
            }
        } else if (isAdmin) {
            // User has only ADMIN role
            targetUrl = roleUrlMap.get("ROLE_ADMIN");
            System.out.println("DEBUG: User has only ADMIN role");
        } else if (isUser) {
            // User has only USER role
            targetUrl = roleUrlMap.get("ROLE_USER");
            System.out.println("DEBUG: User has only USER role");
        }
        
        System.out.println("DEBUG: Target URL from roleUrlMap: " + targetUrl);
        
        if (targetUrl == null) {
            targetUrl = "/pocetna"; // fallback to default
            System.out.println("DEBUG: Using default target URL: " + targetUrl);
        }
        
        System.out.println("Redirecting to: " + targetUrl);
        
        // Handle null context path
        if (request.getContextPath() != null && !request.getContextPath().isEmpty()) {
            targetUrl = request.getContextPath() + targetUrl;
        }
        
        System.out.println("Final redirect URL: " + targetUrl);
        response.sendRedirect(targetUrl);
    }

    public void setRoleUrlMap(Map<String, String> roleUrlMap) {
        this.roleUrlMap = roleUrlMap;
        System.out.println("DEBUG: roleUrlMap was set with " + (roleUrlMap != null ? roleUrlMap.size() : "null") + " entries");
    }
}
