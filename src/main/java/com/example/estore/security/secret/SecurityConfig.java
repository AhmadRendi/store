package com.example.estore.security.secret;

import com.example.estore.security.jwt.JWTAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig{

    private JWTAuthenticationFilter filter;
    private AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/api/registration/owner").permitAll();
                    auth.requestMatchers("/api/registration/buyer").permitAll();
                    auth.requestMatchers("/api/login/buyer").permitAll();
                    auth.requestMatchers("/api/store/create").permitAll();
                    auth.requestMatchers("api/store/**").hasAnyAuthority("OWNER", "ADMIN");
                    auth.requestMatchers("/api/user/**").hasAnyRole("BUYER", "ADMIN");
                    auth.requestMatchers("/api/owner/**").hasAnyRole("OWNER", "ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(sesMen -> {
                    sesMen.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
