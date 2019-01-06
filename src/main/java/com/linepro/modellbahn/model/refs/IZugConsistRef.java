package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public interface IZugConsistRef extends ILinkRef {

    @JsonGetter(ApiNames.POSITION)
    Integer getPosition();
    
    @JsonGetter(ApiNames.ARTIKEL)
    IArtikelRef getArtikel();
}