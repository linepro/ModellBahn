package com.linepro.modellbahn.model;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.rest.json.Formats;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ArtikelModel.
 * @author   $Author$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ANDERUNG)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.ARTIKEL_ID, ApiNames.ANDERUNGSDATUM, ApiNames.ANDERUNGS_TYP, ApiNames.BEZEICHNUNG, ApiNames.STUCK, ApiNames.ANMERKUNG, ApiNames.DELETED})
@Schema(name = ApiNames.ANDERUNG, description = "Changes tp an article")
public class AnderungModel extends RepresentationModel<AnderungModel> implements ItemModel, Comparable<AnderungModel> {
    
    private static final long serialVersionUID = 7089488648732721954L;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(name = "Artikel id", example = "00001", required = true)
    private String artikelId;

    @JsonProperty(ApiNames.ANDERUNG_ID)
    @Schema(name = "Change number", example = "00001", accessMode = AccessMode.READ_ONLY)
    private Integer anderungId;

    @JsonProperty(ApiNames.ANDERUNGSDATUM)
    @JsonFormat(shape=Shape.STRING, pattern= Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Change date", example = "1967-08-10")
    private LocalDate anderungsDatum;

    @JsonProperty(ApiNames.ANDERUNGS_TYP)
    @Schema(name = "Change type", example = "UMGEBAUT", required = true)
    private AnderungsTyp anderungsTyp;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Change description", example = "New 5* motor and decoder", required = true)
    private String bezeichnung;
    
    @JsonProperty(ApiNames.STUCK)
    @Schema(name = "Changed Quantity", example = "1")
    private Integer stuck;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(name = "Remarks", example = "5* Motor and decoder")
    private String anmerkung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(AnderungModel other) {
        return new CompareToBuilder()
            .append(anderungId, other.anderungId)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(artikelId)
            .append(anderungId)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AnderungModel)) {
            return false; 
        }

        AnderungModel other = (AnderungModel) obj;
        
        return new EqualsBuilder()
                .append(artikelId, other.artikelId)
                .append(anderungId, other.anderungId)
                .isEquals();
    }
}
