package com.linepro.modellbahn.security.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.service.criterion.AbstractCriterion;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.USER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({
                ApiNames.NAMEN, ApiNames.EMAIL, ApiNames.FIRST_NAME, ApiNames.LAST_NAME, ApiNames.LOCALE, ApiNames.ENABLED,
                ApiNames.LOGIN_ATTEMPTS, ApiNames.PASSWORD_AGING, ApiNames.ROLES
})
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.USER)
@Schema(name = ApiNames.USER, description = "System User")
public class UserCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Screen Name", example = "linepro", accessMode = AccessMode.READ_ONLY)
    private String username;

    @JsonProperty(ApiNames.EMAIL)
    @Schema(description = "eMail address", example = "linepro@compuserve.com")
    private String email;

    @JsonProperty(ApiNames.FIRST_NAME)
    @Schema(description = "First Name", example = "John")
    private String firstName;

    @JsonProperty(ApiNames.LAST_NAME)
    @Schema(description = "Surname", example = "Goff")
    private String lastName;

    @JsonProperty(ApiNames.LOCALE)
    @Schema(description = "Locale", example = "en_GB")
    private String locale;

    @JsonProperty(ApiNames.ENABLED)
    @Schema(description = "false if user blocked", example = "true")
    private Boolean enabled;

    @JsonProperty(ApiNames.LOGIN_ATTEMPTS)
    @Schema(description = "Number of login attempts before lockout", example = "5")
    private Integer loginAttempts;

    @JsonProperty(ApiNames.PASSWORD_AGING)
    @Schema(description = "Password aging enabled", example = "true")
    private Integer passwordAging;

    @JsonProperty(ApiNames.PASSWORD_CHANGED)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Las Password change", example = "1960-02-15T07:00:00", accessMode = AccessMode.READ_ONLY)
    private LocalDateTime passwordChanged;

    @JsonProperty(ApiNames.CONFIRMATION_EXPIRES)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Confirmation token expires", example = "2011-12-31T00:00:00", accessMode = AccessMode.READ_ONLY)
    private LocalDateTime confirmationExpires;

    @JsonProperty(ApiNames.LAST_LOGIN)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Date of last login", example = "2021-05-31T12:00:00", accessMode = AccessMode.READ_ONLY)
    private LocalDateTime lastLogin;

    @JsonProperty(ApiNames.ROLES)
    @Schema(description = "Roles", example = "[USER]", accessMode = AccessMode.READ_ONLY)
    private List<String> roles;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> user) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, user, where, DBNames.NAME, getUsername());
        addCondition(criteriaBuilder, user, where, DBNames.EMAIL, getEmail());
        addCondition(criteriaBuilder, user, where, DBNames.FIRST_NAME, getFirstName());
        addCondition(criteriaBuilder, user, where, DBNames.LAST_NAME, getLastName());
        addCondition(criteriaBuilder, user, where, DBNames.ENABLED, getEnabled());
        addCondition(criteriaBuilder, user, where, DBNames.LOGIN_ATTEMPTS, getLoginAttempts());
        addCondition(criteriaBuilder, user, where, DBNames.PASSWORD_AGING, getPasswordAging());
        addCondition(criteriaBuilder, user, where, DBNames.LOCALE, getLocale());
        if (CollectionUtils.isNotEmpty(getRoles())) {
            where.add(criteriaBuilder.in(user.get(DBNames.ROLES)).value(getRoles()));
        }
        
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "user.noChildren";
    }
}
