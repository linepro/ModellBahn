package com.linepro.modellbahn.model.refs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.LocalDateSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@JsonPropertyOrder({ApiNames.ANDERUNGSDATUM, ApiNames.ANDERUNGS_TYP, ApiNames.BEZEICHNUNG, ApiNames.STUCK})
@ApiModel(value = ApiNames.ANDERUNG, description = "Change to an article")
public interface IAnderungRef extends ILinkRef {

    @JsonGetter(ApiNames.ANDERUNGS_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change number", example = "00001", accessMode = AccessMode.READ_ONLY)
    Integer getAnderungsId();

    @JsonGetter(ApiNames.ANDERUNGSDATUM)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonView(Views.Public.class)
    @JsonFormat(shape=Shape.STRING, pattern= Formats.ISO8601_DATE)
    @ApiModelProperty(dataType = "java.time.LocalDate", value = "Change date", example = "1967-08-10")
    LocalDate getAnderungsDatum();

    @JsonGetter(ApiNames.ANDERUNGS_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change type", example = "UMGEBAUT", required = true)
    AnderungsTyp getAnderungsTyp();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change description", example = "New 5* motor and decoder", required = true)
    String getBezeichnung();
    
    @JsonGetter(ApiNames.STUCK)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Changed Quantity", example = "1")
    Integer getStuck();
}
