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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IBahnverwaltung.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.BAHNVERWALTUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.BAHNVERWALTUNG, description = "Railway company.")
public class BahnverwaltungModel extends RepresentationModel<BahnverwaltungModel> implements NamedItemModel {

    private static final long serialVersionUID = -6997053850560073389L;

    @JsonProperty(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Company code", example = "DB", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Company name", example = "Deutschen Bundesbahn (DB)")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}