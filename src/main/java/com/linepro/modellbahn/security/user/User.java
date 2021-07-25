package com.linepro.modellbahn.security.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
@NamedEntityGraphs({
    @NamedEntityGraph(name="user.lookup",
        attributeNodes = {
            @NamedAttributeNode(value = "name"),
            @NamedAttributeNode(value = "email"),
            @NamedAttributeNode(value = "firstName"),
            @NamedAttributeNode(value = "lastName"),
            @NamedAttributeNode(value = "enabled")
        },
        subgraphs = {
        }),
    @NamedEntityGraph(name="user.basic",
        attributeNodes = {
            @NamedAttributeNode(value = "name"),
            @NamedAttributeNode(value = "email"),
            @NamedAttributeNode(value = "firstName"),
            @NamedAttributeNode(value = "lastName"),
            @NamedAttributeNode(value = "enabled"),
            @NamedAttributeNode(value = "lastLogin"),
            @NamedAttributeNode(value = "locale"),
            @NamedAttributeNode(value = "roles")
        },
        subgraphs = {
        }),
    @NamedEntityGraph(name="user.detail",
        includeAllAttributes = true, 
        attributeNodes = {
            @NamedAttributeNode(value = "name"),
            @NamedAttributeNode(value = "email"),
            @NamedAttributeNode(value = "firstName"),
            @NamedAttributeNode(value = "lastName"),
            @NamedAttributeNode(value = "enabled"),
            @NamedAttributeNode(value = "loginAttempts"),
            @NamedAttributeNode(value = "loginFailures"),
            @NamedAttributeNode(value = "passwordAging"),
            @NamedAttributeNode(value = "passwordChanged"),
            @NamedAttributeNode(value = "lastLogin"),
            @NamedAttributeNode(value = "confirmationExpires"),
            @NamedAttributeNode(value = "locale"),
            @NamedAttributeNode(value = "roles")
        },
        subgraphs = {
        })
    })
//@formatter:on
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBNames.ID, nullable = false, updatable = false)
    private Long id;

    @Column(name = DBNames.NAME, length = 50, nullable = false)
    @Pattern(regexp = "^" + DBNames.USERNAME_PATTERN + "$", message = "{com.linepro.modellbahn.validator.constraints.username.invalid}")
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
    private Boolean enabled;

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

    @Column(name = DBNames.CONFIRMATION_EXPIRES)
    private LocalDateTime confirmationExpires;

    @Column(name = DBNames.LOCALE, length = 50)
    @Locale(message = "{com.linepro.modellbahn.validator.constraints.user.locale.valid}")
    private String locale;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = DBNames.ROLES, length = 50)
    private List<String> roles;
}