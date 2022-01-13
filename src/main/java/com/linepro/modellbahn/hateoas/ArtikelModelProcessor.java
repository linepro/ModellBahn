package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.ARTIKEL_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiNames.NAMEN;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ANDERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ARTIKEL_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ARTIKEL_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ARTIKEL_GROSSANSICHT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ARTIKEL_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.ANDERUNGEN;
import static com.linepro.modellbahn.controller.impl.ApiRels.DECODEREN;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.GROSSANSICHT;
import static com.linepro.modellbahn.controller.impl.ApiRels.PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;
import static com.linepro.modellbahn.controller.impl.ApiRels.VORBILD;

import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Lazy
@Component(PREFIX + "ArtikelModelProcessor")
public class ArtikelModelProcessor extends ModelProcessorImpl<ArtikelModel> implements RepresentationModelProcessor<ArtikelModel> {

    private static final FieldsExtractor VORBILD_EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { NAMEN, ((ArtikelModel) m).getVorbild() } 
        });

    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(ARTIKEL_ID, ((ArtikelModel) m).getArtikelId());

    private static final FieldsExtractor PRODUKT_EXTRACTOR =  (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((ArtikelModel) m).getHersteller() }, 
        { BESTELL_NR, ((ArtikelModel) m).getBestellNr() }, 
        });

    private final AnderungModelProcessor anderungProcessor;

    @Autowired
    public ArtikelModelProcessor(AnderungModelProcessor anderungProcessor) {
        super(
            new LinkTemplateImpl(SEARCH, SEARCH_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(ADD, ADD_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(SELF, GET_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_ARTIKEL, EXTRACTOR, (m) -> BooleanUtils.isFalse(((ArtikelModel) m).getDeleted())),
            new LinkTemplateImpl(ABBILDUNG, ADD_ARTIKEL_ABBILDUNG, EXTRACTOR),
            new LinkTemplateImpl(GROSSANSICHT, ADD_ARTIKEL_GROSSANSICHT, EXTRACTOR),
            new LinkTemplateImpl(ANDERUNGEN, ADD_ANDERUNG, EXTRACTOR),
            new LinkTemplateImpl(DECODEREN, ADD_ARTIKEL_DECODER, EXTRACTOR),
            new LinkTemplateImpl(PRODUKT, GET_PRODUKT, PRODUKT_EXTRACTOR),
            new LinkTemplateImpl(VORBILD, GET_VORBILD, VORBILD_EXTRACTOR)
            );

        this.anderungProcessor = anderungProcessor;
    }

    @Override
    public ArtikelModel process(ArtikelModel model) {
        if (!CollectionUtils.isEmpty(model.getAnderungen())) {
            model.getAnderungen()
                 .forEach(u -> anderungProcessor.process(u));
        }

        if (!CollectionUtils.isEmpty(model.getFunktionen())) {
            model.getFunktionen()
                 .stream()
                 .filter(DecoderFunktionModel::getProgrammable)
                 .forEach(f -> {
                     String update = ServletUriComponentsBuilder.fromCurrentServletMapping()
                                                                .path(UPDATE_DECODER_FUNKTION)
                                                                .buildAndExpand(MapUtils.putAll(new HashMap<String,Object>(), new String[][] {
                                                                    { DECODER_ID, f.getDecoderId() },
                                                                    { FUNKTION, f.getFunktion() },
                                                                    }))
                                                                .toString();

                     f.add(Link.of(update, UPDATE));
                 });
        }

        if (!CollectionUtils.isEmpty(model.getDecoders())) {
            String artikelId = model.getArtikelId();

            model.getDecoders()
                 .forEach(d -> {
                     d.setCvs(null);
                     d.setFunktionen(null);

                     String self = ServletUriComponentsBuilder.fromCurrentServletMapping()
                                                              .path(GET_DECODER)
                                                              .buildAndExpand(Collections.singletonMap(DECODER_ID, d.getDecoderId()))
                                                              .toString();

                     d.add(Link.of(self, SELF));

                     String update = ServletUriComponentsBuilder.fromCurrentServletMapping()
                                                                .path(UPDATE_DECODER)
                                                                .buildAndExpand(Collections.singletonMap(DECODER_ID, d.getDecoderId()))
                                                                .toString();

                     d.add(Link.of(update, UPDATE));

                     String delete = ServletUriComponentsBuilder.fromCurrentServletMapping()
                                                                .path(DELETE_ARTIKEL_DECODER)
                                                                .queryParam(ApiNames.DECODER_ID, d.getDecoderId())
                                                                .buildAndExpand(Collections.singletonMap(ARTIKEL_ID,  artikelId ))
                                                                .toString();

                     d.add(Link.of(delete, DELETE));
                 });
        }

        return super.process(model);
    }
}
