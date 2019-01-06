package com.linepro.modellbahn.model.refs;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

public interface IPictureRef {

    /**
     * Gets the abbildung.
     *
     * @return the abbildung
     */
    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.Public.class)
    @JsonSerialize(using = PathSerializer.class)
    @ApiModelProperty(dataType = "String", value = "Image URL", accessMode = AccessMode.READ_ONLY)
    Path getAbbildung();
}
