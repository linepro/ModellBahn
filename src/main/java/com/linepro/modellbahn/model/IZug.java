package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.refs.IZugConsistRef;
import com.linepro.modellbahn.model.refs.IZugRef;
import com.linepro.modellbahn.model.refs.IZugTypRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.ZugTypDeserializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IZug.
 * @author $Author$
 * @version $Id$
 */
@JsonRootName(value = ApiNames.ZUG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.ID, ApiNames.ZUG_TYP, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED,
        ApiNames.CONSIST, ApiNames.LINKS})
@ApiModel(value = ApiNames.ZUG, description = "A running train configuration.")
public interface IZug extends INamedItem, IZugRef {

    /**
     * Gets the typ.
     * @return the typ
     */
    @JsonGetter(ApiNames.ZUG_TYP)
    @JsonSerialize(as= IZugTypRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IZugTypRef", value = "Train type", required = true)
    IZugTyp getZugTyp();

    /**
     * Sets the typ.
     * @param typ the new typ
     */
    @JsonSetter(ApiNames.ZUG_TYP)
    @JsonDeserialize(using= ZugTypDeserializer.class)
    void setZugTyp(IZugTyp typ);

    /**
     * Gets the consist.
     * @return the consist
     */
    @JsonGetter(ApiNames.CONSIST)
    @JsonSerialize(contentAs = IZugConsistRef.class)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.refs.IZugConsistRef;", value = "Train composition", access = "READ_ONLY")
    Set<IZugConsist> getSortedConsist();

    @JsonIgnore
    Set<IZugConsist> getConsist();

    @JsonIgnore
    void setConsist(Set<IZugConsist> consist);

    void addConsist(IZugConsist consist);

    void removeConsist(IZugConsist consist);

}
