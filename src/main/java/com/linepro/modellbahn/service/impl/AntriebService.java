package com.linepro.modellbahn.service.impl;

/**
 * AntriebService. CRUD service for Antrieb
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.AntriebMutator;
import com.linepro.modellbahn.converter.model.AntriebModelMutator;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.AntriebModel;
import com.linepro.modellbahn.repository.AntriebRepository;

@Service(PREFIX + "AntriebService")
public class AntriebService extends NamedItemServiceImpl<AntriebModel, Antrieb> {

    private final AntriebRepository repository;

    private final FileService fileService;

    @Autowired
    public AntriebService(AntriebRepository repository, AntriebModelMutator modelMutator, AntriebMutator entityMutator, FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<AntriebModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name).map(a -> {
            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.ANTRIEB, ApiNames.ABBILDUNG, name));
            return repository.saveAndFlush(a);
        }).flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<AntriebModel> deleteAbbildung(String name) {
        return repository.findByName(name).map(a -> { a.setAbbildung(fileService.deleteFile(a.getAbbildung())); return repository.saveAndFlush(a); })
                        .flatMap(e -> this.get(name));
    }
}
