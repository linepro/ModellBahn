package com.linepro.modellbahn.service.impl;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.ZugMapper;
import com.linepro.modellbahn.converter.request.ZugRequestMapper;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.entity.ZugConsist;
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

    @Autowired
    public ZugService(ZugRepository repository, ZugRequestMapper requestMapper, ZugMapper entityMapper, ArtikelRepository artikelRepository,
                      ZugConsistRepository consistRepository, ZugLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.artikelRepository = artikelRepository;
        this.consistRepository = consistRepository;
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
    public Optional<ZugModel> addConsist(String zugStr, String artikelId) {
        return repository.findZug(zugStr)
                         .map(z -> {
                             ZugConsist fahrzeug = new ZugConsist();
                             fahrzeug.setArtikel(artikelRepository.findByArtikelId(artikelId).orElse(null));
                             z.addConsist(fahrzeug);

                             consistRepository.saveAndFlush(fahrzeug);

                             return z;
                             })
                         .flatMap(z -> get(zugStr));
    }

    @Transactional
    public Optional<ZugModel> moveConsist(String zugStr, Integer position, boolean up) {
        return repository.findByName(zugStr)
                         .map(z -> {
                             Set<ZugConsist> consists = z.getConsist();

                             Optional<ZugConsist> optFrom = consists.stream()
                                                                    .filter(c -> c.getPosition().equals(position))
                                                                    .findFirst();

                             Optional<ZugConsist> optTo = consists.stream()
                                                                  .filter(c -> c.getPosition().equals(up ? position + 1 : position - 1))
                                                                  .findFirst();

                             if (optFrom.isPresent() && optTo.isPresent()) {
                                 ZugConsist from = optFrom.get();
                                 ZugConsist to = optTo.get();

                                 Artikel artikel = to.getArtikel();
                                 to.setArtikel(from.getArtikel());
                                 from.setArtikel(artikel);

                                 z = repository.save(z);
                             }

                             return z;
                         })
                         .flatMap(z -> get(zugStr));
    }

    @Transactional
    public Optional<ZugModel> deleteConsist(String zugStr, Integer position) {
        return repository.findByName(zugStr)
                         .map(z -> {
                             List<ZugConsist> consists = new ArrayList<>(z.getConsist());

                             for (int i = 0; i < consists.size(); i++) {
                                 ZugConsist consist = consists.get(i);

                                 if (consist.getPosition() >= position) {
                                     if (consist.getPosition() >= consists.size()) {
                                         z.getConsist().remove(consist);
                                     } else {
                                         ZugConsist next = consists.get(i + 1);

                                         consist.setArtikel(next.getArtikel());

                                         consistRepository.saveAndFlush(consist);
                                     }
                                 }
                             }

                             return z;
                         })
                         .flatMap(z -> get(zugStr));
    }
}
