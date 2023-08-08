package com.example.MutsaSNS.config;

import com.example.MutsaSNS.jwt.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/users/signup",
                                "/users/signin")
                        .permitAll()
                        .requestMatchers(
                                "/users",
                                "/users/image",
                                "/users/follow/{followId}",
                                "/users/friend",
                                "/users/friend/{friendId}",
                                "/articles",
                                "/articles/follow",
                                "/articles/{articleId}",
                                "/articles/{articleId}/image",
                                "/articles/{articleId}/likes",
                                "/articles/{articleId}/image/{imageId}",
                                "/articles/{articleId}/comments",
                                "/articles/{articleId}/comments/{commentId}")
                        .authenticated()

                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS)
                ).addFilterBefore(
                        jwtTokenFilter,
                        AuthorizationFilter.class
                );
        return httpSecurity.build();
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
