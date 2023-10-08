package com.example.estore.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_buyer")
public class Buyer extends User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emails;

    private String usernames;

    private String passwords;

    private String cellphone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role roles;


    @Override
    public void setEmail(String email) {
        this.setEmails(email);
    }

    @Override
    public String getEmail() {
        return this.getEmails();
    }


    @Override
    public String getPassword() {
        return this.getPasswords();
    }

    @Override
    public String getUsername() {
        return this.getUsernames();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
