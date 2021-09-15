package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTypCvLookup")
public class DecoderTypCvLookup implements Lookup<DecoderTypCv, DecoderTypCvModel> {

    private final DecoderTypCvRepository repository;

    public Optional<DecoderTypCv> find(String hersteller, String bestellNr, Integer cv) {
        if (StringUtils.hasText(hersteller) && StringUtils.hasText(bestellNr) && cv != null) {
            return repository.findByCv(hersteller, bestellNr, cv);
        }

        return Optional.empty();
    }

    public Optional<DecoderTypCv> find(DecoderTyp decoderTyp, Integer cv) {
        if (decoderTyp != null && decoderTyp.getHersteller() != null) {
            return find(decoderTyp.getHersteller().getName(), decoderTyp.getBestellNr(), cv);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTypCv> find(DecoderTypCv item) {
        if (item != null && item.getDecoderTyp() != null) {
            return find(item.getDecoderTyp(), item.getCv());
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTypCv> find(DecoderTypCvModel model) {
        if (model != null) {
            return find(model.getHersteller(), model.getBestellNr(), model.getCv());
        }

        return Optional.empty();
    }

}
