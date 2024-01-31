package com.example.database_user.configs.security;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
  @Bean
  public UserDetailsService singleUser() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(
        User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build());
    return manager;
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .formLogin(withDefaults()).httpBasic(withDefaults());
    return httpSecurity.build();
  }


/*  // We can also create multiple users and give role based access to them
  @Bean
  public UserDetailsService multipleUsers() {
    *//*
     * Note: do not use withDefaultPasswordEncoder() method in production since its
     * not recommended and its not safe. This is used only for development and learning purpose ,
     * use your own password generating methods.
   *//*
    UserBuilder user = User.withDefaultPasswordEncoder();
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(user.username("faiz").password("user_password").roles("USER").build());
    manager.createUser(user.username("user").password("user").roles("USER").build());

    return manager;
  }*/

  @Bean
  public SecurityFilterChain configureMultipleUserRoles(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf((csrf) -> csrf.disable()).securityMatcher("/bookstore/api/v1/admin**")
        .authorizeHttpRequests(auth -> auth.anyRequest().hasRole("ADMIN")).httpBasic(withDefaults());

    return httpSecurity.build();

  }
}
