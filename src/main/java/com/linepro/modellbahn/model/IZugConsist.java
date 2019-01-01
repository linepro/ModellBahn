package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.keys.ZugConsistKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.ZugResolver;
import com.linepro.modellbahn.rest.json.serialization.ArtikelSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * IZugConsist.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.CONSIST)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.ZUG, ApiNames.POSITION, ApiNames.ARTIKEL, ApiNames.DELETED, ApiNames.LINKS })
@ApiModel(value = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public interface IZugConsist extends IItem<ZugConsistKey> {

    /**
     * Gets the zug.
     *
     * @return the zug
     */
    @JsonGetter(ApiNames.ZUG)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= ZugResolver.class)
    @ApiModelProperty(dataType = "String", value = "", accessMode = AccessMode.READ_ONLY, required = true)
    IZug getZug();

    /**
     * Sets the zug.
     *
     * @param zug the new zug
     */
    @JsonSetter(ApiNames.ZUG)
    @JsonDeserialize(as= Zug.class)
    void setZug(IZug zug);

    /**
     * Gets the position.
     *
     * @return the position
     */
    @JsonGetter(ApiNames.POSITION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", accessMode = AccessMode.READ_ONLY, required = true)
    Integer getPosition();

    /**
     * Sets the position.
     *
     * @param position the new position
     */
    @JsonSetter(ApiNames.POSITION)
    void setPosition(Integer position);

    /**
     * Gets the artikel.
     *
     * @return the artikel
     */
    @JsonGetter(ApiNames.ARTIKEL)
    @JsonSerialize(contentUsing = ArtikelSerializer.class)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.rest.json.serialization.IArtikelRef", value = "", accessMode = AccessMode.READ_ONLY, required = true)
    IArtikel getArtikel();

    /**
     * Sets the artikel.
     *
     * @param artikel the new artikel
     */
    @JsonSetter(ApiNames.ARTIKEL)
    @JsonDeserialize(contentAs = Artikel.class)
    void setArtikel(IArtikel artikel);

}