package com.linepro.modellbahn.service.impl;

/**
 * SteuerungService. CRUD service for Steuerung
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
import com.linepro.modellbahn.converter.entity.SteuerungMutator;
import com.linepro.modellbahn.converter.model.SteuerungModelMutator;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.SteuerungModel;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "SteuerungService")
public class SteuerungService extends NamedItemServiceImpl<SteuerungModel, Steuerung> implements ItemService<SteuerungModel> {

    private final FileService fileService;

    @Autowired
    public SteuerungService(SteuerungRepository repository, SteuerungModelMutator modelMutator, SteuerungMutator entityMutator,
                    FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.fileService = fileService;
    }

    @Transactional
    public Optional<SteuerungModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name).map(a -> {
            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.STEUERUNG, ApiNames.ABBILDUNG, name));
            return repository.saveAndFlush(a);
        }).flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<SteuerungModel> deleteAbbildung(String name) {
        return repository.findByName(name).map(a -> { a.setAbbildung(fileService.deleteFile(a.getAbbildung())); return repository.saveAndFlush(a); })
                        .flatMap(e -> this.get(name));
    }
}
