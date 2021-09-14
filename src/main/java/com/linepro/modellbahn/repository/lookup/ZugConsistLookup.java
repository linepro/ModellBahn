package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.repository.ZugConsistRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ZugConsistLookup")
public class ZugConsistLookup implements Lookup<ZugConsist, ZugConsistModel> {

    private final ZugConsistRepository repository;

    public Optional<ZugConsist> find(String zug, Integer position) {
        if (StringUtils.hasText(zug) && position != null) {
        return repository.findByPosition(zug, position);
        }

        return Optional.empty();
    }

    @Override
    public Optional<ZugConsist> find(ZugConsist item) {
        if (item != null && item.getZug() != null) {
            return find(item.getZug().getName(), item.getPosition());
        }

        return Optional.empty();
    }

    @Override
    public Optional<ZugConsist> find(ZugConsistModel model) {
        if (model != null) {
            return find(model.getZug(), model.getPosition());
        }

        return Optional.empty();
    }

}
