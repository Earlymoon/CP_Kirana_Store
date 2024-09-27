package com.example.kirana.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration// as we are deeling with custom securtiy services class
@EnableWebSecurity  //this enables the security features
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth->auth.
                requestMatchers("/api/transactions/**").
                hasRole("READ_WRITE")
                .requestMatchers("/api/reporting/**")
                .hasAnyRole("READ_WRITE","READ_ONLY").
                anyRequest().authenticated()).httpBasic(withDefaults());
        return http.build();

    }
@Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1= User.withUsername("owner").password(passwordEncoder().encode("owner123")).roles("READ_WRITE").build();
        UserDetails user2=User.withUsername("user").password(passwordEncoder().encode("user123")).roles("READ_ONLY").build();
        return new InMemoryUserDetailsManager(user1,user2);
    }


//    to encode plain password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
