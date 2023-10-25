package com.example.estore.config;

import com.example.estore.Entity.Buyer;
import com.example.estore.Entity.Owner;
import com.example.estore.extend.UserDetailService;
import com.example.estore.repo.BuyerRepo;
import com.example.estore.repo.OwnerRepo;
import com.example.estore.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.UserDatabase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

    private final BuyerRepo buyerRepo;
    private final OwnerRepo ownerRepo;
    private  UserDetailService userDetailService = new UserDetailService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return null;
        }

        @Override
        public UserDetails loadUserByEmails(String email) throws UsernameNotFoundException {
            return null;
        }
    };

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            Buyer buyer = buyerRepo.findBuyerByEmails(email).orElse(null);
            if(buyer == null){
                log.info(email);
                return ownerRepo.findOwnerByUsernames(email).orElseThrow(() -> new UsernameNotFoundException("not found"));
            }else {
                return buyerRepo.findBuyerByUsernames(email).orElseThrow(() -> new UsernameNotFoundException("no"));
            }

        };
//         return username -> buyerRepo.findBuyerByEmails(username).get();
//         return email -> ownerRepo.findOwnerByEmails(email).get();
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
