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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.ZugConsistMapper;
import com.linepro.modellbahn.converter.entity.ZugMapper;
import com.linepro.modellbahn.converter.request.ZugRequestMapper;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.repository.ZugConsistRepository;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.repository.lookup.ZugLookup;
import com.linepro.modellbahn.request.ZugRequest;

@Service(PREFIX + "ZugService")
public class ZugService extends NamedItemServiceImpl<ZugModel, ZugRequest, Zug> {

    private final ZugRepository repository;

    private final  ArtikelRepository artikelRepository;

    private final ZugConsistRepository consistRepository;

    private final ZugConsistMapper consistMapper;

    @Autowired
    public ZugService(ZugRepository repository, ZugRequestMapper requestMapper, ZugMapper entityMapper, ArtikelRepository artikelRepository,
                      ZugConsistRepository consistRepository, ZugConsistMapper consistMapper, ZugLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.artikelRepository = artikelRepository;
        this.consistRepository = consistRepository;
        this.consistMapper = consistMapper;
    }

    @Override
    public Optional<ZugModel> get(String name) {
        return repository.findZug(name)
                         .map(e -> entityMapper.convert(e));
    }

    @Transactional
    @Override
    public Optional<ZugModel> update(String name, ZugRequest request) {
        return repository.findZug(name)
                         .map(e -> {
                             Boolean deleted = e.getDeleted();
                             Zug zug = requestMapper.apply(request,e);
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

                             return consistMapper.convert(fahrzeug);
                             });
    }

    @Transactional
    public Optional<ZugConsistModel> updateConsist(String zugStr, Integer position, String artikelId) {
        return consistRepository.findByPosition(zugStr, position)
                                .map(c -> {
                                    c.setArtikel(artikelRepository.findByArtikelId(artikelId).orElse(null));

                                    return consistMapper.convert(consistRepository.saveAndFlush(c));
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
