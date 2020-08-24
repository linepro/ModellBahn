package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_UNTER_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_KATEGORIE;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.KategorieModel;

@Lazy
@Component(PREFIX + "KategorieModelProcessor")
public class KategorieModelProcessor extends NamedModelProcessor<KategorieModel> implements RepresentationModelProcessor<KategorieModel> {

    private final UnterKategorieModelProcessor unterKategorieProcessor;

    public KategorieModelProcessor(UnterKategorieModelProcessor unterKategorieProcessor) {
        super(
            ADD_KATEGORIE, 
            GET_KATEGORIE, 
            UPDATE_KATEGORIE, 
            DELETE_KATEGORIE, 
            SEARCH_KATEGORIE,
            new LinkTemplateImpl(ApiRels.ADD, ADD_UNTER_KATEGORIE, EXTRACTOR)
            );
        
        this.unterKategorieProcessor = unterKategorieProcessor;
    }

    @Override
    public KategorieModel process(KategorieModel model) {
        if (!CollectionUtils.isEmpty(model.getUnterKategorien())) {
            model.getUnterKategorien()
                 .forEach(u -> unterKategorieProcessor.process(u));
        }

        return super.process(model);
    }
}
