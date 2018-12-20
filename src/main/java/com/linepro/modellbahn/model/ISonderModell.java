package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * ISonderModell.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.SONDERMODELL)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
public interface ISonderModell extends INamedItem<NameKey> {

}