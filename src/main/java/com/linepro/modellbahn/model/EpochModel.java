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
 * IEpoch.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.EPOCH)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Relation(collectionRelation = ApiNames.EPOCH, itemRelation = ApiNames.EPOCH)
@Schema(name = ApiNames.EPOCH, description = "Era - NEM 800.")
public class EpochModel extends RepresentationModel<EpochModel> implements NamedItemModel, Comparable<EpochModel> {

    private static final long serialVersionUID = 2877272020386187147L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "ERA", example = "III", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "ERA description", example = "III : 1949 - 1970")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(EpochModel other) {
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

        if (!(obj instanceof EpochModel)) {
            return false; 
        }

        EpochModel other = (EpochModel) obj;
        
        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }
}