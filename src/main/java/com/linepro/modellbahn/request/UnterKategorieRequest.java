package com.linepro.modellbahn.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IUnterKategorie.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.UNTER_KATEGORIE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.KATEGORIE, ApiNames.KATEGORIE_BEZEICHNUNG, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.UNTER_KATEGORIE, description = "Sub category.")
public class UnterKategorieRequest implements NamedItemRequest {

    private static final long serialVersionUID = 251649405613258177L;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category coding", example = "LOKOMOTIV", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Sub category coding", example = "ELEKTRO", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Sub category description", example = "Elektro")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                        .append(kategorie)
                        .append(name)
                        .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UnterKategorieRequest)) {
            return false;
        }

        UnterKategorieRequest other = (UnterKategorieRequest) obj;

        return new EqualsBuilder()
                        .append(kategorie, other.kategorie)
                        .append(name, other.name)
                        .isEquals();
    }
}