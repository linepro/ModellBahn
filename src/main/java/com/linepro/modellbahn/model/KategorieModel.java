/*
 * IKategorie
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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
 * IKategorie.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.KATEGORIE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.UNTER_KATEGORIEN,
        ApiNames.LINKS })
@Schema(name = ApiNames.KATEGORIE, description = "Category.")
public class KategorieModel extends RepresentationModel<KategorieModel> implements NamedItemModel, Comparable<KategorieModel> {
    
    private static final long serialVersionUID = -1609829141621577668L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(name = "Category coding", example = "LOKOMOTIV", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Category description", example = "Lokomotiv")
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIEN)
    @Schema(implementation = UnterKategorieModel.class, name = "Sub categories", accessMode = AccessMode.READ_ONLY, required = true)
    private List<UnterKategorieModel> unterKategorien;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(KategorieModel other) {
        return new CompareToBuilder()
            .append(name, other.name)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(name)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof KategorieModel)) {
            return false; 
        }

        KategorieModel other = (KategorieModel) obj;
        
        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }
}