package com.sec.account.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)throws Exception{

//        http.authorizeHttpRequests((auth)->auth.requestMatchers("/myAccount","mylones","myCards").authenticated().
//                requestMatchers("/notices","/contacts").permitAll()).
//        formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

        //Deny all request, If you want to permit all request just put permitAll() in place of denyAll()
        http.authorizeHttpRequests((auth)->auth.anyRequest().denyAll()).
                formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
       return http.build();

    }

}
