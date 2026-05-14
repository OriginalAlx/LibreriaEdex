package com.libreria.edex.security;

import com.libreria.edex.service.CustomUserDetailsService;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configurar vistas públicas ANTES de llamar al configure de Vaadin
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login/**").permitAll();
            auth.requestMatchers("/").permitAll();
        });
        
        // Llamar al configure de VaadinWebSecurity que configura las rutas de Vaadin
        // Esto habilita el soporte para vistas Vaadin protegidas
        super.configure(http);
        
        // NOTA: No usamos http.formLogin() ni http.logout() aquí porque
        // estamos usando una vista personalizada de Vaadin (Inicio) que maneja
        // la autenticación manualmente mediante httpReq.login().
        // Agregar formLogin() interfiere con la navegación de Vaadin Flow.
    }
}
