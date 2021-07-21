package com.linepro.modellbahn.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.server.core.Relation;

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
 * ISteuerung.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.STEUERUNG)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.STEUERUNG)
@Schema(name = ApiNames.STEUERUNG, description = "Control system.")
public class SteuerungRequest implements NamedItemRequest {

    /**
     * 
     */
    private static final long serialVersionUID = -6404603256726171348L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Steuerung code", example = "FRU", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Steuerung description", example = "Fahrrichtungsumschalter")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

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

        if (!(obj instanceof SteuerungRequest)) {
            return false; 
        }

        SteuerungRequest other = (SteuerungRequest) obj;

        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }
}