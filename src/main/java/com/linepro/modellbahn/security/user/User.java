package com.linepro.modellbahn.security.user;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.linepro.modellbahn.validation.Locale;

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
      @UniqueConstraint(name = DBNames.USER + "_UC1", columnNames = { DBNames.NAME }), 
      @UniqueConstraint(name = DBNames.USER + "_UC2", columnNames = { DBNames.EMAIL }) 
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBNames.ID, nullable = false, updatable = false)
    private Long id;

    @Column(name = DBNames.NAME, length = 50, nullable = false)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.name.notempty}")
    private String name;

    @Column(name = DBNames.PASSWORD, length = 100, nullable = false)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.password.notempty}")
    private String password;

    @Column(name = DBNames.EMAIL, length = 100, nullable = false)
    @Email(message = "{com.linepro.modellbahn.validator.constraints.email.invalid}")
    private String email;

    @Column(name = DBNames.FIRST_NAME, length = 50)
    private String firstName;

    @Column(name = DBNames.LAST_NAME, length = 50)
    private String lastName;

    @Column(name = DBNames.ENABLED, nullable = false)
    private boolean enabled;

    @Column(name = DBNames.LOGIN_ATTEMPTS)
    private Integer loginAttempts;

    @Column(name = DBNames.LOGIN_FAILURES)
    private Integer loginFailures;

    @Column(name = DBNames.PASSWORD_AGING)
    private Integer passwordAging;

    @Column(name = DBNames.PASSWORD_CHANGED)
    private LocalDateTime passwordChanged;

    @Column(name = DBNames.LAST_LOGIN)
    private LocalDateTime lastLogin;

    @Column(name = DBNames.CONFIRMATION_TOKEN, length = 36)
    private UUID confirmationToken;

    @Column(name = DBNames.RESET_TOKEN, length = 36)
    private UUID resetToken;

    @Column(name = DBNames.LOCALE, length = 50)
    @Locale(message = "{com.linepro.modellbahn.validator.constraints.user.locale.valid}")
    private String locale;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = DBNames.ROLES, length = 50)
    private List<String> roles;

    @Transient
    @Override
    public String getUsername() {
        return getName();
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return Optional.ofNullable(getLastLogin())
                       .map(l -> Duration.between(l, LocalDateTime.now()).get(ChronoUnit.YEARS) < 2)
                       .orElse(true);
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return Optional.ofNullable(getLoginFailures())
                        .map(f -> Optional.of(getLoginAttempts())
                                          .map(a -> a > f)
                                          .orElse(true))
                        .orElse(true);
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return Optional.ofNullable(getPasswordAging())
                        .map(a -> Optional.of(getPasswordChanged())
                                          .map(c -> Duration.between(c, LocalDateTime.now()).get(ChronoUnit.DAYS) < a)
                                          .orElse(true))
                        .orElse(true);
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(getRoles())
                       .map(r -> r.stream()
                                  .map(o -> new SimpleGrantedAuthority(o))
                                  .collect(Collectors.toList()))
                       .orElse(Collections.emptyList());
    }

    @Transient
    public void setPassword(String password) {
        this.password = password;
        this.passwordChanged = LocalDateTime.now();
    }

    @Transient
    public void clearConfirmationToken() {
        setConfirmationToken(null);
    }

    @Transient
    public void clearResetToken() {
        setResetToken(null);
    }
}