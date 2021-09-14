package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.repository.DecoderCvRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderCvLookup")
public class DecoderCvLookup implements Lookup<DecoderCv, DecoderCvModel> {

    private final DecoderCvRepository repository;

    public Optional<DecoderCv> find(String decoderId, Integer cv) {
        if (StringUtils.hasText(decoderId) && cv != null) {
            return repository.findByCv(decoderId, cv);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderCv> find(DecoderCv item) {
        if (item != null && item.getDecoder() != null && item.getCv() != null) {
                return find(item.getDecoder().getDecoderId(), item.getCv().getCv());
            }

            return Optional.empty();
    }

    @Override
    public Optional<DecoderCv> find(DecoderCvModel model) {
        if (model != null) {
            return find(model.getDecoderId(), model.getCv());
        }

        return Optional.empty();
    }

}
