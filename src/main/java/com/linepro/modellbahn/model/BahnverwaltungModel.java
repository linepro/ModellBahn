package com.linepro.modellbahn.model;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IBahnverwaltung.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.BAHNVERWALTUNG)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LAND, ApiNames.DELETED })
@Relation(collectionRelation = ApiNames.BAHNVERWALTUNG, itemRelation = ApiNames.BAHNVERWALTUNG)
@Schema(name = ApiNames.BAHNVERWALTUNG, description = "Railway company.")
public class BahnverwaltungModel extends RepresentationModel<BahnverwaltungModel> implements NamedItemModel, Comparable<BahnverwaltungModel> {

    private static final long serialVersionUID = -6997053850560073389L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Company code", example = "DB", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Company name", example = "Deutschen Bundesbahn (DB)")
    private String bezeichnung;

    @JsonProperty(ApiNames.LAND)
    @Schema(description = "Country ISO 3166 Code", example = "DE")
    private String land;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;


    @Override
    public int compareTo(BahnverwaltungModel other) {
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

        if (!(obj instanceof BahnverwaltungModel)) {
            return false; 
        }

        BahnverwaltungModel other = (BahnverwaltungModel) obj;
        
        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }
}