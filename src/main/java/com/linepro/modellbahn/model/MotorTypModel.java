package com.linepro.modellbahn.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IMotorTyp.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.MOTOR_TYP)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.MOTOR_TYP)
@Schema(name = ApiNames.MOTOR_TYP, description = "Type of motor - Märklin coding.")
public class MotorTypModel extends SpringdocModel<MotorTypModel> implements NamedWithAbbildungModel, Comparable<MotorTypModel> {

    private static final long serialVersionUID = -3424740287690628265L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Motor Type Code", example = "SFCM", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Motor Type description", example = "Scheibenkollektor (klein)")
    private String bezeichnung;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(description = "Image URL", example = "http://localhost:8086/ModellBahn/motorTyp/HLM/abbildung.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(MotorTypModel other) {
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

        if (!(obj instanceof MotorTypModel)) {
            return false; 
        }

        MotorTypModel other = (MotorTypModel) obj;

        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }

    @Schema(name = ApiNames.MOTOR_TYP + "Page")
    public static class PagedMotorTypModel extends PagedSchema<MotorTypModel>{}
}