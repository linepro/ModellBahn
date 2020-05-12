package com.linepro.modellbahn.model;

import java.net.URL;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.rest.json.Views;

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
    @JsonView(Views.DropDown.class)
    @Schema(name = "Hersteller coding", example = "MARKLIN", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Hersteller description", example = "M�rklin")
    private String bezeichnung;

    @JsonProperty(ApiNames.URL)
    @JsonView(Views.Public.class)
    @Schema(implementation = String.class, name = "Manufacturer's website", example = "https://www.maerklin.de")
    private URL url;

    @JsonProperty(ApiNames.TELEFON)
    @JsonView(Views.Public.class)
    @Schema(name = "Manufacturer's phone number", example = "+49 (0) 71 61 608-0")
    private String telefon;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}