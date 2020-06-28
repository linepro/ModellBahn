package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.MotorTypModel;

@Lazy
@Component("MotorTypModelProcessor")
public class MotorTypModelProcessor extends NamedModelProcessor<MotorTypModel> implements RepresentationModelProcessor<MotorTypModel> {

    @Autowired
    public MotorTypModelProcessor() {
        super(
            ApiPaths.ADD_MOTOR_TYP, 
            ApiPaths.GET_MOTOR_TYP, 
            ApiPaths.UPDATE_MOTOR_TYP, 
            ApiPaths.DELETE_MOTOR_TYP, 
            ApiPaths.SEARCH_MOTOR_TYP
            );
    }
}
