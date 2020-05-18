package com.linepro.modellbahn.model;

import java.net.URL;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
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
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.TELEFON, ApiNames.URL, ApiNames.DELETED})
@Schema(name = ApiNames.HERSTELLER, description = "Manufacturer.")
public class HerstellerModel extends RepresentationModel<HerstellerModel> implements NamedItemModel {

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

    @Schema(implementation = String.class, name = "Manufacturer's website", example = "https://www.maerklin.de")
    private URL url;

    @JsonProperty(ApiNames.TELEFON)

    @Schema(name = "Manufacturer's phone number", example = "+49 (0) 71 61 608-0")
    private String telefon;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}