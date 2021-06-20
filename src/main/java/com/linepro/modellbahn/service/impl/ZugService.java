package com.linepro.modellbahn.service.impl;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.ZugConsistMutator;
import com.linepro.modellbahn.converter.entity.ZugMutator;
import com.linepro.modellbahn.converter.model.ZugModelMutator;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.repository.ZugConsistRepository;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.service.criterion.ZugCriterion;

@Service(PREFIX + "ZugService")
public class ZugService extends NamedItemServiceImpl<ZugModel,Zug> {

    private final ZugRepository repository;

    private final  ArtikelRepository artikelRepository;

    private final ZugConsistRepository consistRepository;

    private final ZugConsistMutator consistMutator;

    @Autowired
    public ZugService(ZugRepository repository, ZugModelMutator modelMutator, ZugMutator entityMutator, ArtikelRepository artikelRepository, ZugConsistRepository consistRepository, ZugConsistMutator consistMutator) {
        super(repository, modelMutator, entityMutator);

        this.repository = repository;
        this.artikelRepository = artikelRepository;
        this.consistRepository = consistRepository;
        this.consistMutator = consistMutator;
    }

    @Override
    public Optional<ZugModel> get(String name) {
        return repository.findZug(name)
                         .map(e -> entityMutator.convert(e));
    }

    @Override
    protected Page<Zug> findAll(Optional<ZugModel> model, Pageable pageRequest) {
        return repository.findAll(new ZugCriterion(model), pageRequest);
    }

    @Transactional
    @Override
    public Optional<ZugModel> update(String name, ZugModel model) {
        return repository.findZug(name)
                         .map(e -> {
                             Boolean deleted = e.getDeleted();
                             Zug zug = modelMutator.apply(model,e);
                             e.setDeleted(deleted);
                             return repository.saveAndFlush(zug);
                         })
                         .flatMap(e -> get(name)); // Fetch again to populate entity graphs
    }

    @Transactional
    public Optional<ZugConsistModel> addConsist(String name, String artikelId) {
        return repository.findZug(name)
                         .map(z -> {
                             ZugConsist fahrzeug = new ZugConsist();
                             fahrzeug.setArtikel(artikelRepository.findByArtikelId(artikelId).orElse(null));
                             z.addConsist(fahrzeug);

                             consistRepository.saveAndFlush(fahrzeug);

                             return consistMutator.convert(fahrzeug);
                         });
    }

    @Transactional
    public Optional<ZugConsistModel> updateConsist(String zugStr, Integer position, String artikelId) {
        return consistRepository.findByPosition(zugStr, position)
                                .map(c -> {
                                    c.setArtikel(artikelRepository.findByArtikelId(artikelId).orElse(null));

                                    return consistMutator.convert(consistRepository.saveAndFlush(c));
                                });
    }

    @Transactional
    public boolean deleteConsist(String zugStr, Integer position) {
        return consistRepository.findByPosition(zugStr, position)
                                .map(c -> {
                                    AtomicInteger index = new AtomicInteger(1);

                                    Zug zug = c.getZug();

                                    zug.getConsist().remove(c);

                                    // Remove
                                    zug = repository.saveAndFlush(zug);

                                    // Renumber remaining
                                    zug.getConsist()
                                       .stream()
                                       .sorted()
                                       .forEach(p -> {
                                           int pos = index.getAndIncrement();

                                           if (p.getPosition() >= position) {
                                               p.setPosition(pos);

                                               consistRepository.saveAndFlush(p);
                                           }
                                       });

                                    return true;
                                })
                                .orElse(false);
    }
}
