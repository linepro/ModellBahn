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
 * IHersteller.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.HERSTELLER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LAND, ApiNames.URL, ApiNames.TELEFON, ApiNames.DELETED})
@Relation(collectionRelation = ApiNames.HERSTELLER, itemRelation = ApiNames.HERSTELLER)
@Schema(name = ApiNames.HERSTELLER, description = "Manufacturer.")
public class HerstellerModel extends RepresentationModel<HerstellerModel> implements NamedItemModel, Comparable<HerstellerModel> {

    private static final long serialVersionUID = 2975751105718552754L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(name = "Hersteller coding", example = "MARKLIN", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Hersteller description", example = "Märklin")
    private String bezeichnung;

    @JsonProperty(ApiNames.LAND)
    @Schema(name = "Country ISO 3166 Code", example = "DE")
    private String land;
    
    @JsonProperty(ApiNames.URL)
    @Schema(name = "Manufacturer's website", example = "https://www.maerklin.de")
    private String url;

    @JsonProperty(ApiNames.TELEFON)
    @Schema(name = "Manufacturer's phone number", example = "+49 (0) 71 61 608-0")
    private String telefon;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(HerstellerModel other) {
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

        if (!(obj instanceof HerstellerModel)) {
            return false; 
        }

        HerstellerModel other = (HerstellerModel) obj;
        
        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }
}