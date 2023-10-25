package com.example.estore.security.jwt;

import com.example.estore.Entity.Buyer;
import com.example.estore.Entity.Owner;
import com.example.estore.service.BuyerService;
import com.example.estore.service.impl.BuyerServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private  JWTService service;
    private UserDetailsService  userDetailsService;
    private BuyerServiceImpl buyerService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = header.substring(7);
        log.info(jwt);
        username = service.extractUsername(jwt);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails  = this.userDetailsService.loadUserByUsername(username);

            if(service.isValidToken(jwt, userDetails)){

                    UsernamePasswordAuthenticationToken authenticationFilter = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    authenticationFilter.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationFilter);
            }
            filterChain.doFilter(request, response);
        }
    }


}
