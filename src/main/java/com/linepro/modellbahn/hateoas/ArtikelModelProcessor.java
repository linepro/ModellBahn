package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.ACHSFOLG;
import static com.linepro.modellbahn.controller.impl.ApiNames.ANDERUNG;
import static com.linepro.modellbahn.controller.impl.ApiNames.ARTIKEL_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiNames.BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.EPOCH;
import static com.linepro.modellbahn.controller.impl.ApiNames.GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiNames.KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiNames.KUPPLUNG;
import static com.linepro.modellbahn.controller.impl.ApiNames.LICHT;
import static com.linepro.modellbahn.controller.impl.ApiNames.MASSSTAB;
import static com.linepro.modellbahn.controller.impl.ApiNames.MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiNames.NAMEN;
import static com.linepro.modellbahn.controller.impl.ApiNames.PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiNames.SONDERMODELL;
import static com.linepro.modellbahn.controller.impl.ApiNames.SPURWEITE;
import static com.linepro.modellbahn.controller.impl.ApiNames.STEUERUNG;
import static com.linepro.modellbahn.controller.impl.ApiNames.UNTER_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ANDERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ARTIKEL_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ACHSFOLG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_EPOCH;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_KUPPLUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_LICHT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_MASSSTAB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_MOTOR_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_SONDERMODELL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_SPURWEITE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_STEUERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_UNTER_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.IMAGE;
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

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ArtikelModel;

@Lazy
@Component(PREFIX + "ArtikelModelProcessor")
public class ArtikelModelProcessor extends ModelProcessorImpl<ArtikelModel> implements RepresentationModelProcessor<ArtikelModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(ARTIKEL_ID, ((ArtikelModel) m).getArtikelId());
    
    @Autowired
    public ArtikelModelProcessor() {
        super(
            new LinkTemplateImpl(ADD, ADD_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(SELF, GET_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_ARTIKEL, EXTRACTOR, (m) -> BooleanUtils.isFalse(((ArtikelModel) m).getDeleted())),
            new LinkTemplateImpl(SEARCH, SEARCH_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(IMAGE, ADD_ARTIKEL_ABBILDUNG, EXTRACTOR),
            new LinkTemplateImpl(ANDERUNG, ADD_ANDERUNG, EXTRACTOR),
            new LinkTemplateImpl(PRODUKT, GET_PRODUKT, (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
                { HERSTELLER, ((ArtikelModel) m).getHersteller() }, 
                { BESTELL_NR, ((ArtikelModel) m).getBestellNr() } 
                })),
            new LinkTemplateImpl(KATEGORIE, GET_KATEGORIE, (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getKategorie())),
            new LinkTemplateImpl(UNTER_KATEGORIE, GET_UNTER_KATEGORIE, (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
                { KATEGORIE, ((ArtikelModel) m).getKategorie() }, 
                { UNTER_KATEGORIE, ((ArtikelModel) m).getUnterKategorie() } 
                })),
            new LinkTemplateImpl(MASSSTAB, GET_MASSSTAB, 
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getMassstab()),
                            (m) -> ((ArtikelModel) m).getMassstab() != null
                            ),
            new LinkTemplateImpl(SPURWEITE, GET_SPURWEITE,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getSpurweite()),
                            (m) -> ((ArtikelModel) m).getSpurweite() != null
                            ),
            new LinkTemplateImpl(EPOCH, GET_EPOCH,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getEpoch()),
                            (m) -> ((ArtikelModel) m).getEpoch() != null
                            ),
            new LinkTemplateImpl(BAHNVERWALTUNG, GET_BAHNVERWALTUNG,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getBahnverwaltung()),
                            (m) -> ((ArtikelModel) m).getBahnverwaltung() != null
                            ),
            new LinkTemplateImpl(GATTUNG, GET_GATTUNG,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getGattung()),
                            (m) -> ((ArtikelModel) m).getGattung() != null
                            ),
            new LinkTemplateImpl(ACHSFOLG, GET_ACHSFOLG,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getAchsfolg()),
                            (m) -> ((ArtikelModel) m).getAchsfolg() != null
                            ),
            new LinkTemplateImpl(SONDERMODELL, GET_SONDERMODELL,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getSondermodell()),
                            (m) -> ((ArtikelModel) m).getSondermodell() != null
                            ),
            new LinkTemplateImpl(AUFBAU, GET_AUFBAU,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getAufbau()),
                            (m) -> ((ArtikelModel) m).getAufbau() != null
                            ),
            new LinkTemplateImpl(LICHT, GET_LICHT,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getLicht()),
                            (m) -> ((ArtikelModel) m).getLicht() != null
                            ),
            new LinkTemplateImpl(KUPPLUNG, GET_KUPPLUNG,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getKupplung()),
                            (m) -> ((ArtikelModel) m).getKupplung() != null
                            ),
            new LinkTemplateImpl(STEUERUNG, GET_STEUERUNG, 
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getSteuerung()),
                            (m) -> ((ArtikelModel) m).getSteuerung() != null
                            ),
            new LinkTemplateImpl(DECODER, GET_DECODER,  
                            (m) -> Collections.singletonMap(DECODER_ID, ((ArtikelModel) m).getDecoder()),
                            (m) -> ((ArtikelModel) m).getDecoder() != null
                            ),
            new LinkTemplateImpl(MOTOR_TYP, GET_MOTOR_TYP,  
                            (m) -> Collections.singletonMap(NAMEN, ((ArtikelModel) m).getMotorTyp()),
                            (m) -> ((ArtikelModel) m).getMotorTyp() != null
                            )
            );
    }
}
