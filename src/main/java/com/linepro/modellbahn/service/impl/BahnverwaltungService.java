package com.linepro.modellbahn.service.impl;

/**
 * BahnverwaltungService. CRUD service for Bahnverwaltung
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.BahnverwaltungMutator;
import com.linepro.modellbahn.converter.model.BahnverwaltungModelMutator;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.BahnverwaltungModel;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;

@Service(PREFIX + "BahnverwaltungService")
public class BahnverwaltungService extends NamedItemServiceImpl<BahnverwaltungModel, Bahnverwaltung> {

    private final FileService fileService;

    @Autowired
    public BahnverwaltungService(BahnverwaltungRepository repository, BahnverwaltungModelMutator modelMutator, BahnverwaltungMutator entityMutator,
                    FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.fileService = fileService;
    }

    @Transactional
    public Optional<BahnverwaltungModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name).map(a -> {
            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.BAHNVERWALTUNG, ApiNames.ABBILDUNG, name));
            return repository.saveAndFlush(a);
        }).flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<BahnverwaltungModel> deleteAbbildung(String name) {
        return repository.findByName(name).map(a -> { a.setAbbildung(fileService.deleteFile(a.getAbbildung())); return repository.saveAndFlush(a); })
                        .flatMap(e -> this.get(name));
    }
}
