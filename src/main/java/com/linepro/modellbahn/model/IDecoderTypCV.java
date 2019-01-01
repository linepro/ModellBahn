package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.keys.DecoderTypCVKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoderTypCV.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.CV)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER_TYP,  ApiNames.CV,  ApiNames.BEZEICHNUNG,  ApiNames.MINIMAL,  ApiNames.MAXIMAL,  ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.CV, description = "Decoder type CV - template for Decoder.")
public interface IDecoderTypCV extends IItem<DecoderTypCVKey> {

    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using= DecoderTypSerializer.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.rest.json.serialization.IDecoderTypRef", value = "", required = true)
    IDecoderTyp getDecoderTyp();

    /**
     * Sets the decoder typ.
     *
     * @param decoderTyp the new decoder typ
     */
    @JsonSetter(ApiNames.DECODER_TYP)
    @JsonDeserialize(as= DecoderTyp.class)
    void setDecoderTyp(IDecoderTyp decoderTyp);

    /**
     * Gets the cv.
     *
     * @return the cv
     */
    @JsonGetter(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    Integer getCv();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    @JsonSetter(ApiNames.CV)
    void setCv(Integer cv);

    /**
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    String getBezeichnung();

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    @JsonSetter(ApiNames.BEZEICHNUNG)
    void setBezeichnung(String bezeichnung);

    /**
     * Gets the minimal.
     *
     * @return the minimal
     */
    @JsonGetter(ApiNames.MINIMAL)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getMinimal();

    /**
     * Sets the minimal.
     *
     * @param minimal the new minimal
     */
    @JsonSetter(ApiNames.MINIMAL)
    void setMinimal(Integer minimal);

    /**
     * Gets the maximal.
     *
     * @return the maximal
     */
    @JsonGetter(ApiNames.MAXIMAL)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getMaximal();

    /**
     * Sets the maximal.
     *
     * @param maximal the new maximal
     */
    @JsonSetter(ApiNames.MAXIMAL)
    void setMaximal(Integer maximal);

    /**
     * Gets the werkseinstellung.
     *
     * @return the werkseinstellung
     */
    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getWerkseinstellung();

    /**
     * Sets the werkseinstellung.
     *
     * @param werkseinstellung the new werkseinstellung
     */
    @JsonSetter(ApiNames.WERKSEINSTELLUNG)
    void setWerkseinstellung(Integer werkseinstellung);

}