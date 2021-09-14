package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.repository.DecoderAdressRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderAdressLookup")
public class DecoderAdressLookup implements Lookup<DecoderAdress, DecoderAdressModel> {

    private final DecoderAdressRepository repository;

    public Optional<DecoderAdress> find(String decoderId, Integer index) {
        if (StringUtils.hasText(decoderId) && index != null) {
            return repository.findByIndex(decoderId, index);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderAdress> find(DecoderAdress item) {
        if (item != null && item.getDecoder() != null && item.getTyp() != null) {
                return find(item.getDecoder().getDecoderId(), item.getTyp().getPosition());
            }

            return Optional.empty();
    }

    @Override
    public Optional<DecoderAdress> find(DecoderAdressModel model) {
        if (model != null) {
            return find(model.getDecoderId(), model.getIndex());
        }

        return Optional.empty();
    }

}
