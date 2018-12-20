package com.linepro.modellbahn.model;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import io.swagger.annotations.ApiModelProperty;

/**
 * ILicht.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.LICHT)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
public interface ILicht extends INamedItem<NameKey> {

    /**
     * Gets the abbildung.
     *
     * @return the abbildung
     */
    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as=String.class, using = PathSerializer.class)
    @ApiModelProperty(name = ApiNames.ABBILDUNG, dataType = "String", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Path getAbbildung();

    /**
     * Sets the abbildung.
     *
     * @param abbildung
     *            the new abbildung
     */
    @JsonIgnore
    void setAbbildung(Path abbildung);
}