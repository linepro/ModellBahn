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
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.AdressTyp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IDecoderAdress.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ADRESS)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.INDEX,  ApiNames.BEZEICHNUNG,  ApiNames.SPAN, ApiNames.ADRESS_TYP,  ApiNames.WERKSEINSTELLUNG, ApiNames.ADRESS, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.ADRESS)
@Schema(name = ApiNames.ADRESS, description = "Decoder address setting.")
public class DecoderAdressModel extends SpringdocModel<DecoderAdressModel> implements ItemModel, Comparable<DecoderAdressModel> {

    private static final long serialVersionUID = 5617027998164314206L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.INDEX)
    @Schema(description = "0 based address index (always 0 for single address decoders)", example = "0", accessMode = AccessMode.READ_ONLY)
    private Integer index;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "First Motorola Address")
    private String bezeichnung;

    @JsonProperty(ApiNames.SPAN)
    @Schema(description = "Number of addresses consumed (1-16)", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer span;

    @JsonProperty(ApiNames.ADRESS_TYP)
    @Schema(description = "Address type", required = true)
    private AdressTyp adressTyp;

    @JsonProperty(ApiNames.WERKSEINSTELLUNG)
    @Schema(description = "Default digital address", example = "80", accessMode = AccessMode.READ_ONLY)
    private Integer werkeinstellung;

    @JsonProperty(ApiNames.ADRESS)
    @Schema(description = "Digital address", example = "80", required = true)
    private Integer adress;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(DecoderAdressModel other) {
        return new CompareToBuilder()
            .append(decoderId, other.decoderId)
            .append(index, other.index)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(decoderId)
            .append(index)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderAdressModel)) {
            return false; 
        }

        DecoderAdressModel other = (DecoderAdressModel) obj;

        return new EqualsBuilder()
                .append(decoderId, other.decoderId)
                .append(index, other.index)
                .isEquals();
    }
}