package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

public interface IDecoderRef extends ILinkRef {

    @JsonGetter(ApiNames.DECODER_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "This Decoder's id", accessMode = AccessMode.READ_ONLY, required = true)
    String getName();
    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypRef.class)
    @ApiModelProperty(value = "This Decoder's type", required = true)
    IDecoderTyp getDecoderTyp();
}
