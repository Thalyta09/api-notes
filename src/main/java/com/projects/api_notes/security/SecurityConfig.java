package com.projects.api_notes.security;

import com.projects.api_notes.exception.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    final MyUserDetailsService myUserDetailsService;
    final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(MyUserDetailsService myUserDetailsService, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.myUserDetailsService = myUserDetailsService;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() { //Cost Factor padrão (10)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.DELETE, "/api/notes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/notes").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated())
                .userDetailsService(myUserDetailsService)
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e ->
                        e.accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }

}