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
 * IDecoderCv.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CV)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.DECODER, ApiNames.CV, ApiNames.WERT, ApiNames.DELETED })
@ApiModel(value = ApiNames.CV, description = "Decoder CV setting.")
public class DecoderCvModel extends ItemModelImpl<DecoderCvModel> {

    private static final long serialVersionUID = 6780491207710890606L;

    @JsonProperty(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderModel", value = "Parent decoder", required = true)
    private DecoderModel decoder;

    @JsonProperty(ApiNames.CV)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "CV number", example = "08", required = true)
    private Integer cv;

    @JsonProperty(ApiNames.WERT)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Assigned value", required = true)
    private Integer wert;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}