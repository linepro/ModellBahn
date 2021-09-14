package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.DecoderTypRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "DecoderTypLookup")
public class DecoderTypLookup implements Lookup<DecoderTyp, DecoderTypModel>  {

    @Autowired
    private final DecoderTypRepository repository;

    public Optional<DecoderTyp> find(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr);
    }

    @Override
    public Optional<DecoderTyp> find(DecoderTyp item) {
        if (item != null && item.getHersteller() != null && StringUtils.hasText(item.getHersteller().getName()) && StringUtils.hasText(item.getBestellNr())) {
            return find(item.getHersteller().getName(), item.getBestellNr());
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTyp> find(DecoderTypModel item) {
        if (item != null &&  StringUtils.hasText(item.getHersteller()) && StringUtils.hasText(item.getBestellNr())) {
            return find(item.getHersteller(), item.getBestellNr());
        }

        return Optional.empty();
    }
}
