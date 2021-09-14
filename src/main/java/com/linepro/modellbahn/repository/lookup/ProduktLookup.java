package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.repository.ProduktRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "ProduktLookup")
public class ProduktLookup implements Lookup<Produkt, ProduktModel>  {

    @Autowired
    private final ProduktRepository repository;

    public Optional<Produkt> find(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr);
    }

    @Override
    public Optional<Produkt> find(Produkt item) {
        if (item != null && item.getHersteller() != null && StringUtils.hasText(item.getHersteller().getName()) && StringUtils.hasText(item.getBestellNr())) {
            return find(item.getHersteller().getName(), item.getBestellNr());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Produkt> find(ProduktModel item) {
        if (item != null &&  StringUtils.hasText(item.getHersteller()) && StringUtils.hasText(item.getBestellNr())) {
            return find(item.getHersteller(), item.getBestellNr());
        }

        return Optional.empty();
    }
}
