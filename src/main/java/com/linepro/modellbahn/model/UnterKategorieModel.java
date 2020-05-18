package com.linepro.modellbahn.model;
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
 * IUnterKategorie.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.UNTER_KATEGORIE)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.KATEGORIE, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED})
@Schema(name = ApiNames.UNTER_KATEGORIE, description = "Sub category.")
public class UnterKategorieModel extends RepresentationModel<UnterKategorieModel> implements NamedItemModel {

    private static final long serialVersionUID = -6922373932038545571L;

    @JsonProperty(ApiNames.KATEGORIE)

    @Schema(name = "Category coding", example = "LOKOMOTIV", required = true)
    private String kategorie;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "Sub category coding", example = "ELEKTRO", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Sub category description", example = "Elektro")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}