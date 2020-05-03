package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.ItemModelImpl;
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
 * IDecoderAdress.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ADRESS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.DECODER, ApiNames.INDEX, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.DELETED })
@ApiModel(value = ApiNames.ADRESS, description = "Decoder address setting.")
public class DecoderAdressModel extends ItemModelImpl<DecoderAdressModel> {

    private static final long serialVersionUID = 5617027998164314206L;

    @JsonProperty(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderModel", value = "Parent decoder", required = true)
    private DecoderModel decoder;

    @JsonProperty(ApiNames.INDEX)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "0 based address index (always 0 for single address decoders)", example = "0", required = true)
    private Integer index;

    @JsonProperty(ApiNames.ADRESS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Decoder address", example = "128", required = true)
    private Integer adress;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}