package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.NAMEN;
import static com.linepro.modellbahn.controller.impl.ApiPaths.CHANGE_PASSWORD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_USER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.FORGOT_PASSWORD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_USER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.REGISTER_USER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_USER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_USER;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.FORGOT;
import static com.linepro.modellbahn.controller.impl.ApiRels.PASSWORD;
import static com.linepro.modellbahn.controller.impl.ApiRels.REGISTER;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;

@Lazy
@Component(PREFIX + "UserModelProcessor")
public class UserModelProcessor extends ModelProcessorImpl<UserModel> implements RepresentationModelProcessor<UserModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(NAMEN, ((UserModel) m).getUsername());

    @Autowired
    public UserModelProcessor() {
        super(
            new LinkTemplateImpl(REGISTER, REGISTER_USER, EXTRACTOR),
            new LinkTemplateImpl(FORGOT, FORGOT_PASSWORD, EXTRACTOR),
            new LinkTemplateImpl(SEARCH, SEARCH_USER, EXTRACTOR),
            new LinkTemplateImpl(SELF, GET_USER, EXTRACTOR),
            new LinkTemplateImpl(PASSWORD, CHANGE_PASSWORD, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_USER, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_USER, EXTRACTOR)
            );
    }
}
