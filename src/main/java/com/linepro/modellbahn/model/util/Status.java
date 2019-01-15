package com.linepro.modellbahn.model.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Status.
 * The status of an article
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.STATUS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.STATUS, description = "Article status")
public enum Status implements IDescribedEnum {
	
    WUNSCHMODEL("A wanted model."),
	
	  GEKAUFT("Purchased."),
	
	  BASTLER("For spares and repairs."),
	
    VERKAUFT("Sold.");

    private final String description;
    
    Status(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getBezeichnung() {
        return description;
    }
}