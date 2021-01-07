package com.linepro.modellbahn.model.enums;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonRootName(value = ApiNames.DECODER_STATUS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.DECODER_STATUS)
@Schema(name = ApiNames.DECODER_STATUS, description = "Decoder statues")
public class DecoderStatusModel extends DescribedEnumModel {

    public DecoderStatusModel(String name, String bezeichnung, String tooltip) {
        super(name, bezeichnung, tooltip);
    }

    @Override
    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Decoder status name", example = "INSTALIERT", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Decoder status description", example = "Instaliert.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return super.getBezeichnung();
    }

    @Override
    @JsonProperty(ApiNames.TOOLTIP)
    @Schema(description = "Decoder status tooltip", example = "Instaliert.", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return super.getTooltip();
    }

    @Schema(name = ApiNames.DECODER_STATUS + "Page")
    public static class PagedDecoderStatusModel extends PagedSchema<DecoderStatusModel>{};
}
