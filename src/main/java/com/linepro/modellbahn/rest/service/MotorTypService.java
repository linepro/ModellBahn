package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * MotorTypService.
 * CRUD service for MotorTyp
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.MOTOR_TYP)
public class MotorTypService extends NamedItemService<MotorTyp> {

    /**
     * Instantiates a new motor typ service.
     */
    public MotorTypService() {
        super(MotorTyp.class);
    }
}
