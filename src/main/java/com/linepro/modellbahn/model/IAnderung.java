package com.linepro.modellbahn.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.keys.AnderungKey;
import com.linepro.modellbahn.model.refs.IAnderungRef;
import com.linepro.modellbahn.model.refs.IArtikelRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.ArtikelDeserializer;
import com.linepro.modellbahn.rest.json.serialization.LocalDateDeserializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * IArtikel.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ANDERUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.ID, ApiNames.ARTIKEL_ID, ApiNames.ANDERUNGSDATUM, ApiNames.ANDERUNGS_TYP, ApiNames.BEZEICHNUNG, ApiNames.STUCK, ApiNames.ANMERKUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.ANDERUNG, description = "Changes tp an article")
public interface IAnderung extends IItem<AnderungKey>, IAnderungRef {
    
    @JsonGetter(ApiNames.ARTIKEL)
    @JsonSerialize(as = IArtikelRef.class)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IArtikelRef", value = "Changed artikel", accessMode = AccessMode.READ_ONLY, required = true)
    IArtikel getArtikel();

    @JsonSetter(ApiNames.ARTIKEL)
    @JsonDeserialize(using = ArtikelDeserializer.class)
    void setArtikel(IArtikel artikel);

    @JsonSetter(ApiNames.ANDERUNGS_ID)
    void setAnderungsId(Integer name);

    @JsonSetter(ApiNames.ANDERUNGSDATUM)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    void setAnderungsDatum(LocalDate kaufdatum);

    @JsonSetter(ApiNames.ANDERUNGS_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Change type", required = true)
    void setAnderungsTyp(AnderungsTyp changeTyp);

    @JsonSetter(ApiNames.BEZEICHNUNG)
    void setBezeichnung(String bezeichnung);

    @JsonSetter(ApiNames.STUCK)
    void setStuck(Integer stuck);

    @JsonGetter(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Remarks", example = "5* Motor and decoder")
    String getAnmerkung();

    @JsonSetter(ApiNames.ANMERKUNG)
    void setAnmerkung(String anmerkung);
}
