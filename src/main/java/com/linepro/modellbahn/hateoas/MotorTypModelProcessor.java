package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_MOTOR_TYP_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.MotorTypModel;

@Lazy
@Component(PREFIX + "MotorTypModelProcessor")
public class MotorTypModelProcessor extends NamedModelProcessor<MotorTypModel> implements RepresentationModelProcessor<MotorTypModel> {

    @Autowired
    public MotorTypModelProcessor() {
        super(
            ADD_MOTOR_TYP, 
            GET_MOTOR_TYP, 
            UPDATE_MOTOR_TYP, 
            DELETE_MOTOR_TYP, 
            SEARCH_MOTOR_TYP,
            new LinkTemplateImpl(ABBILDUNG, ADD_MOTOR_TYP_ABBILDUNG, EXTRACTOR)
            );
    }
}
