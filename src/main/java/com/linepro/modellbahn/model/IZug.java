package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.impl.ZugTyp;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.ZugTypResolver;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * IZug.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ZUG)
@JsonPropertyOrder({ApiNames.ID, ApiNames.ZUG_TYP, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.CONSIST, ApiNames.LINKS})
public interface IZug extends INamedItem<NameKey> {

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    @JsonGetter(ApiNames.ZUG_TYP)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= ZugTypResolver.class)
    IZugTyp getZugTyp();

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    @JsonSetter(ApiNames.ZUG_TYP)
    @JsonDeserialize(as= ZugTyp.class)
    void setZugTyp(IZugTyp typ);

    /**
     * Gets the consist.
     *
     * @return the consist
     */
    @JsonGetter(ApiNames.CONSIST)
    @JsonView(Views.Public.class)
    Set<IZugConsist> getConsist();

    /**
     * Sets the consist.
     *
     * @param consist the new consist
     */
    @JsonSetter(ApiNames.CONSIST)
    void setConsist(Set<IZugConsist> consist);

    void addConsist(IZugConsist consist);

    void removeConsist(IZugConsist consist);

}