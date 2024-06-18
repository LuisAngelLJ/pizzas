package com.la.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//sesabilitar csrf
                .cors().and()//habilitar cors
                .authorizeHttpRequests()//autorizar las peticiones http
                .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")//permitir a los metodos get el acceso solo a estos 2
                .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")//solo puede acceder el admin
                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")//solo puede acceder el admin
                .requestMatchers("/api/orders/random").hasAuthority("ramdom_order")
                .requestMatchers("/api/orders/**").hasRole("ADMIN")//solo el admin puede acceder a todos los métodos
                .anyRequest()//cualquier petición
                .authenticated()//debe estar autenticado
                .and()
                .httpBasic();
        //retornar la configuración
        return http.build();
    }

    @Bean//agregarlo al coclo de vida de spring
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
