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
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LAND, ApiNames.URL, ApiNames.TELEFON, ApiNames.DELETED })
@Schema(name = ApiNames.HERSTELLER, description = "Manufacturer.")
public class HerstellerRequest implements NamedItemRequest {

    /**
     * 
     */
    private static final long serialVersionUID = 539328448525435071L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Hersteller coding", example = "MARKLIN", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Hersteller description", example = "Märklin")
    private String bezeichnung;

    @JsonProperty(ApiNames.LAND)
    @Schema(description = "Country ISO 3166 Code", example = "DE")
    private String land;

    @JsonProperty(ApiNames.URL)
    @Schema(description = "Manufacturer's website", example = "https://www.maerklin.de")
    private String url;

    @JsonProperty(ApiNames.TELEFON)
    @Schema(description = "Manufacturer's phone number", example = "+49 (0) 71 61 608-0")
    private String telefon;

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

        if (!(obj instanceof HerstellerRequest)) {
            return false; 
        }

        HerstellerRequest other = (HerstellerRequest) obj;

        return new EqualsBuilder()
                .append(name, other.name)
                .isEquals();
    }
}