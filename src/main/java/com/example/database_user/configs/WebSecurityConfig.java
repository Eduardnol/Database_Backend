//package com.example.database_user.configs;
//
//import java.util.List;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
////TODO revisar que significa todo esto
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//
//    http.cors().and().csrf().disable();
//  }
//
//
//  @Bean
//  CorsConfigurationSource corsConfigurationSource() {
//
//    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.setAllowedOrigins(List.of("*"));
//    configuration.setAllowedMethods(List.of("*"));
//    configuration.setAllowedHeaders(List.of("*"));
//    configuration.setAllowCredentials(false);
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//    return source;
//  }
//
//}