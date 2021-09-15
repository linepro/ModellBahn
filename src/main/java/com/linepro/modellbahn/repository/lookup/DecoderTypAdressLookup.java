package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTypAdressLookup")
public class DecoderTypAdressLookup implements Lookup<DecoderTypAdress, DecoderTypAdressModel> {

    private final DecoderTypAdressRepository repository;

    public Optional<DecoderTypAdress> find(String hersteller, String bestellNr, Integer index) {
        if (StringUtils.hasText(hersteller) && StringUtils.hasText(bestellNr) && index != null) {
            return repository.findByIndex(hersteller, bestellNr, index);
        }

        return Optional.empty();
    }

    public Optional<DecoderTypAdress> find(DecoderTyp decoderTyp, Integer index) {
        if (decoderTyp != null && decoderTyp.getHersteller() != null) {
            return find(decoderTyp.getHersteller().getName(), decoderTyp.getBestellNr(), index);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTypAdress> find(DecoderTypAdress item) {
        if (item != null && item.getDecoderTyp() != null) {
            return find(item.getDecoderTyp(), item.getPosition());
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTypAdress> find(DecoderTypAdressModel model) {
        if (model != null) {
            return find(model.getHersteller(), model.getBestellNr(), model.getIndex());
        }

        return Optional.empty();
    }

}
