package com.linepro.modellbahn.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.ItemModelImpl;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.ARTIKEL_ID, ApiNames.ANDERUNGSDATUM, ApiNames.ANDERUNGS_TYP, ApiNames.BEZEICHNUNG, ApiNames.STUCK, ApiNames.ANMERKUNG, ApiNames.DELETED})
@ApiModel(value = ApiNames.ANDERUNG, description = "Changes tp an article")
public class AnderungModel extends ItemModelImpl<AnderungModel> {
    
    private static final long serialVersionUID = 7089488648732721954L;

    @JsonProperty(ApiNames.ARTIKEL)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.ArtikelModel", value = "Changed artikel", access = "READ_ONLY", required = true)
    private ArtikelModel artikel;

    @JsonProperty(ApiNames.ANDERUNG_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change number", example = "00001", access = "READ_ONLY")
    private Integer anderungId;

    @JsonProperty(ApiNames.ANDERUNGSDATUM)
    @JsonView(Views.Public.class)
    @JsonFormat(shape=Shape.STRING, pattern= Formats.ISO8601_DATE)
    @ApiModelProperty(dataType = "java.time.LocalDate", value = "Change date", example = "1967-08-10")
    private LocalDate anderungsDatum;

    @JsonProperty(ApiNames.ANDERUNGS_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change type", example = "UMGEBAUT", required = true)
    private AnderungsTyp anderungsTyp;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change description", example = "New 5* motor and decoder", required = true)
    private String bezeichnung;
    
    @JsonProperty(ApiNames.STUCK)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Changed Quantity", example = "1")
    private Integer stuck;

    @JsonProperty(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Remarks", example = "5* Motor and decoder")
    private String anmerkung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
