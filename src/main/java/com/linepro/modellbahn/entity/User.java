package com.linepro.modellbahn.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static final long serialVersionUID = -3641616292070768935L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;
    
    @Column(name = "password")
    @Transient
    private String password;
    
    @Column(name = "first_name")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;
    
    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;
    
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "reset_token")
    private String resetToken;
    
    @Column(name = "expired")
    private boolean expired;
    
    @Column(name = "locked")
    private boolean locked;

    @Column(name = "credentialsExpired")
    private boolean credentialsExpired;
    
    @Column(name = "authorities")
    private Collection<? extends GrantedAuthority> authorities;

    @Transient
    @Override
    public String getUsername() {
        return firstName + " " + lastName;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
}