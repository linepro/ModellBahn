package com.linepro.modellbahn.model.refs;

import java.util.Set;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.LinkSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

public interface ILinkRef extends IRef {
    @JsonGetter(ApiNames.LINKS)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentUsing= LinkSerializer.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.refs.ILink;", value = "HEATOS Links for item", accessMode = AccessMode.READ_ONLY)
    /**
     * Get the HEATOS links for this entity
     * @return a set of HAETOS links
     */
    Set<Link> getLinks();
}
