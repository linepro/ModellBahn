package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * IMotorTyp.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.MOTOR_TYP)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
public interface IMotorTyp extends INamedItem<NameKey> {

}