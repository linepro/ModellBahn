package com.linepro.modellbahn.service.impl;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.KategorieMapper;
import com.linepro.modellbahn.converter.entity.UnterKategorieMapper;
import com.linepro.modellbahn.converter.request.KategorieRequestMapper;
import com.linepro.modellbahn.converter.request.UnterKategorieRequestMapper;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.KategorieRepository;
import com.linepro.modellbahn.repository.UnterKategorieRepository;
import com.linepro.modellbahn.request.KategorieRequest;
import com.linepro.modellbahn.request.UnterKategorieRequest;
import com.linepro.modellbahn.service.criterion.KategorieenCriteria;
import com.linepro.modellbahn.service.criterion.PageCriteria;

@Service(PREFIX + "KategorieService")
public class KategorieService extends NamedItemServiceImpl<KategorieModel, KategorieRequest,  Kategorie> {

    private final KategorieRepository repository;

    private final UnterKategorieRepository unterKategorieRepository;

    private final UnterKategorieMapper unterKategorieMapper;

    private final UnterKategorieRequestMapper unterKategorieRequestMapper;

    @Autowired
    public KategorieService(KategorieRepository repository, KategorieRequestMapper requestMapper, KategorieMapper entityMapper, UnterKategorieRepository unterKategorieRepository, UnterKategorieMapper unterKategorieMapper, UnterKategorieRequestMapper unterKategorieRequestMapper) {
        super(repository, requestMapper, entityMapper);

        this.repository = repository;
        this.unterKategorieRepository = unterKategorieRepository;
        this.unterKategorieMapper = unterKategorieMapper;
        this.unterKategorieRequestMapper = unterKategorieRequestMapper;
     }

    @Transactional
    public Optional<UnterKategorieModel> addUnterKategorie(String kategorieStr, UnterKategorieRequest request) {
        UnterKategorie unterKategorie = unterKategorieRequestMapper.convert(request);
        unterKategorie.setDeleted(false);

        return repository.findByName(kategorieStr)
                         .map(k -> {
                             k.addUnterKategorie(unterKategorie);

                             repository.saveAndFlush(k);

                             return unterKategorieMapper.convert(unterKategorie);
                             });
    }

    @Transactional
    public Optional<UnterKategorieModel> updateUnterKategorie(String kategorieStr, String unterKategorieStr, UnterKategorieRequest request) {
        return unterKategorieRepository.findByName(kategorieStr, unterKategorieStr)
                                       .map(u -> {
                                           Boolean deleted = u.getDeleted();
                                           UnterKategorie unterKategorie = unterKategorieRequestMapper.apply(request, u);
                                           unterKategorie.setDeleted(deleted);
                                           return unterKategorieMapper.convert(unterKategorieRepository.saveAndFlush(unterKategorie));
                                       });
    }

    @Transactional
    public boolean deleteUnterKategorie(String kategorieStr, String unterKategorieStr) {
        return unterKategorieRepository.findByName(kategorieStr, unterKategorieStr)
                                       .map(u -> {
                                           unterKategorieRepository.delete(u);
                                           return true;
                                       })
                                       .orElse(false);
    }

    @Transactional(readOnly = true)
    public Page<KategorieModel> searchUnterKategorie(Optional<List<String>> kategorieen, PageCriteria page) {
        final Page<Kategorie> data = repository.findAll(new KategorieenCriteria(kategorieen), page.getPageRequest());

        return getModelPage(data);
    }
}
