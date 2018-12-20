package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * IAchsfolg.
 * Represents an axle configuration (German nomenclature)
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ACHSFOLG)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
public interface IAchsfolg extends INamedItem<NameKey> {

}