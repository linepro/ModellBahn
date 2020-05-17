package com.linepro.modellbahn.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.linepro.modellbahn.persistence.DBNames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@formatter:off
@Entity(name = DBNames.USER)
@Table(name = DBNames.USER,
  uniqueConstraints = {
      @UniqueConstraint(name = DBNames.USER + "_UC1", columnNames = { DBNames.EMAIL }) 
  })
//@formatter:on
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {

    private static final long serialVersionUID = -3641616292070768935L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DBNames.ID)
    private int id;
    
    @Column(name = DBNames.EMAIL, nullable = false)
    @Email(message = "{com.linepro.modellbahn.validator.user.email.valid}")
    @NotEmpty(message = "{com.linepro.modellbahn.validator.user.email.notempty}")
    private String email;
    
    @Column(name = DBNames.PASSWORD, nullable = false)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.user.password.notempty}")
    @Transient
    private String password;
    
    @Column(name = DBNames.FIRST_NAME, nullable = false)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.user.firstname.notempty}")
    private String firstName;
    
    @Column(name = DBNames.LAST_NAME, nullable = false)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.user.lastname.notempty}")
    private String lastName;
    
    @Column(name = DBNames.ENABLED)
    private boolean enabled;
    
    @Column(name = DBNames.CONFIRMATION_TOKEN)
    private String confirmationToken;

    @Column(name = DBNames.RESET_TOKEN)
    private String resetToken;
    
    @Column(name = DBNames.EXPIRED)
    private boolean expired;
    
    @Column(name = DBNames.LOCKED)
    private boolean locked;

    @Column(name = DBNames.CREDENTIALS_EXPIRED)
    private boolean credentialsExpired;

    @ElementCollection
    @Column(name = DBNames.ROLES)
    private List<String> roles;

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
    
    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(roles)
                       .map(r -> r.stream()
                                  .map(o -> new SimpleGrantedAuthority(o))
                                  .collect(Collectors.toList()))
                       .orElse(Collections.emptyList());
    }

    @Override
    public String getPassword() {
        return password;
    }
}