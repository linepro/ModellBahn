package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.repository.AnderungRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "AnderungLookup")
public class AnderungLookup implements Lookup<Anderung, AnderungModel> {

    private final AnderungRepository repository;

    public Optional<Anderung> find(String artikelId, Integer anderungId) {
        if (StringUtils.hasText(artikelId) && anderungId != null) {
        return repository.findByAnderungId(artikelId, anderungId);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Anderung> find(Anderung item) {
        if (item != null && item.getArtikel() != null) {
            return find(item.getArtikel().getArtikelId(), item.getAnderungId());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Anderung> find(AnderungModel model) {
        if (model != null) {
            return find(model.getArtikelId(), model.getAnderungId());
        }

        return Optional.empty();
    }

}
