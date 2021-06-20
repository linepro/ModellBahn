package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderModelProcessor")
public class DecoderModelProcessor extends ModelProcessorImpl<DecoderModel> implements RepresentationModelProcessor<DecoderModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(DECODER_ID, ((DecoderModel) m).getDecoderId());

    private static final FieldsExtractor DECODER_TYP_EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((DecoderModel) m).getHersteller() }, 
        { BESTELL_NR, ((DecoderModel) m).getBestellNr() }, 
        });

    private final DecoderAdressModelProcessor adressProcessor;

    private final DecoderCvModelProcessor cvProcessor;

    private final DecoderFunktionModelProcessor funktionProcessor;

    @Autowired
    public DecoderModelProcessor(DecoderAdressModelProcessor adressProcessor, DecoderCvModelProcessor cvProcessor,
                    DecoderFunktionModelProcessor funktionProcessor) {
        super(
            new LinkTemplateImpl(ADD, ADD_DECODER, DECODER_TYP_EXTRACTOR),
            new LinkTemplateImpl(SELF, GET_DECODER, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_DECODER, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(SEARCH, SEARCH_DECODER, EXTRACTOR),
            new LinkTemplateImpl(DECODER_TYP, GET_DECODER_TYP, DECODER_TYP_EXTRACTOR)
            );

        this.adressProcessor = adressProcessor;

        this.cvProcessor = cvProcessor;

        this.funktionProcessor = funktionProcessor;
    }

    @Override
    public DecoderModel process(DecoderModel model) {
        if (!CollectionUtils.isEmpty(model.getAdressen())) {
            model.getAdressen()
                 .forEach(a -> adressProcessor.process(a));
        }

        if (!CollectionUtils.isEmpty(model.getCvs())) {
            model.getCvs()
                 .forEach(c -> cvProcessor.process(c));
        }

        if (!CollectionUtils.isEmpty(model.getFunktionen())) {
            model.getFunktionen()
                 .forEach(f -> funktionProcessor.process(f));
        }

        return super.process(model);
    }
}
