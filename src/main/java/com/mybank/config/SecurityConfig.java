package com.mybank.config;

import com.mybank.service.impl.DirectRedirectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;
    
    @Bean
    public AuthenticationSuccessHandler redirectHandler() {
        return new DirectRedirectHandler();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin**").hasRole("ADMIN")
                .antMatchers("/pocetna**").authenticated()
                .antMatchers("/redirect").authenticated()
                .antMatchers("/roleselect").authenticated()
                .antMatchers("/uplatnica**").authenticated()
                .antMatchers("/transferi**").authenticated()
                .antMatchers("/detalji**").authenticated()
                .antMatchers("/tdetalji**").authenticated()
                .antMatchers("/krediti**").authenticated()
                .antMatchers("/inicirajTransfer**").authenticated()
                .antMatchers("/posaljiNalog**").authenticated()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login**").permitAll()
                .antMatchers("/aktivirajServis**").permitAll()
            .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/j_spring_security_check")
                    .defaultSuccessUrl("/pocetna", true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(redirectHandler())
            .and()
                .logout()
                    .logoutUrl("/j_spring_security_logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            .and()
                .csrf()
            .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
            .and()
                .sessionManagement()
                    .invalidSessionUrl("/login")
                    .sessionFixation().newSession()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false); // Allow new login, expire the previous session
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Using plain text password encoder - not recommended for production
        return new PlainTextPasswordEncoder();
    }
    
    // Custom plain text password encoder to match the original XML configuration
    private static class PlainTextPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }
        
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return rawPassword.toString().equals(encodedPassword);
        }
    }
} 