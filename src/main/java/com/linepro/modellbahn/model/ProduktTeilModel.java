package com.linepro.modellbahn.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ProduktDtoTeil.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.TEIL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.PRODUKT, ApiNames.TEIL, ApiNames.ANZAHL, ApiNames.DELETED})
@Schema(name = ApiNames.TEIL, description = "Part of product (spares for rolling stock - contents for set &c).")
public class ProduktTeilModel extends RepresentationModel<ProduktTeilModel> implements ItemModel {

    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = HerstellerModel.class, name = "Manufacturer", accessMode = AccessMode.READ_ONLY, required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY, required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.TEIL_HERSTELLER)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = ProduktModel.class, name = "Sub product Manufacturer", accessMode = AccessMode.READ_ONLY, required = true)
    private String teilHersteller;

    @JsonProperty(ApiNames.TEIL_BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Sub product Part number", example = "3000", accessMode = AccessMode.READ_ONLY, required = true)
    private String teilBestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0", accessMode = AccessMode.READ_ONLY)
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    private UnterKategorieModel unterKategorie;

    @JsonProperty(ApiNames.ANZAHL)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Number included", example = "1", required = true)
    private Integer anzahl;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
