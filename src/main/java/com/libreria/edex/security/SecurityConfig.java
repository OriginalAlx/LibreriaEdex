package com.libreria.edex.security;

import com.libreria.edex.service.CustomUserDetailsService;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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
        // Primero permitir acceso público a recursos estáticos y login
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login").permitAll();
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/VAADIN/**", "/images/**", "/styles/**").permitAll();
            auth.anyRequest().authenticated();
        });

        // Deshabilitar el formLogin por defecto de Spring para usar nuestra vista Vaadin
        http.formLogin().disable();
        http.logout().disable();
        
        // Deshabilitar CSRF para simplificar
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/VAADIN/**"));
        
        // Configurar Vaadin Web Security sin interferir con nuestro login personalizado
        super.configure(http);
    }
}