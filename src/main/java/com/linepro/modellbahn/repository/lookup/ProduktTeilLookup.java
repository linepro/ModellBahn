package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.ProduktTeilRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ProduktTeilLookup")
public class ProduktTeilLookup implements Lookup<ProduktTeil, ProduktTeilModel> {

    private final ProduktTeilRepository repository;

    public Optional<ProduktTeil> find(String herstellerStr, String bestellNr, String teilHerstellerStr, String teilBestellNr) {
        if (StringUtils.hasText(herstellerStr) && StringUtils.hasText(bestellNr) && StringUtils.hasText(teilHerstellerStr) && StringUtils.hasText(teilBestellNr)) {
        return repository.findByTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr);
        }

        return Optional.empty();
    }

    @Override
    public Optional<ProduktTeil> find(ProduktTeil item) {
        if (item != null && item.getProdukt() != null && item.getTeil() != null) {
            return find(item.getProdukt().getHersteller().getName(), item.getProdukt().getBestellNr(), item.getTeil().getHersteller().getName(), item.getTeil().getBestellNr());
        }

        return Optional.empty();
    }

    @Override
    public Optional<ProduktTeil> find(ProduktTeilModel model) {
        if (model != null) {
            return find(model.getHersteller(), model.getBestellNr(), model.getTeilHersteller(), model.getTeilBestellNr());
        }

        return Optional.empty();
    }

}
