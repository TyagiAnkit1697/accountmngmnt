package com.sec.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)throws Exception{

        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((auth)->auth.requestMatchers("/myAccount","mylones","myCards").authenticated().
                requestMatchers("/notices","/contact","/register", "/error").permitAll()).
        formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

    return http.build();
    }
    /*@Bean
    public InMemoryUserDetailsManager userDetailService(){

        *//*UserDetails user = User.withDefaultPasswordEncoder()
                .username("xyz")
                .password("12345")
                .authorities("read").build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("ankit")
                .password("Pakfat50")
                .authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);*//*

        // using noOppassword encoder instead of default password encoder
        UserDetails user = User.withUsername("xyz")
                .password("12345")
                .authorities("read").build();

        UserDetails admin = User.withUsername("ankit")
                .password("Pakfat50")
                .authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    }*/
   /* @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
