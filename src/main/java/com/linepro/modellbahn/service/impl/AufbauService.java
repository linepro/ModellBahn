package com.linepro.modellbahn.service.impl;

/**
 * AufbauService. CRUD service for Aufbau
 * 
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
import com.linepro.modellbahn.converter.entity.AufbauMutator;
import com.linepro.modellbahn.converter.model.AufbauModelMutator;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.AufbauModel;
import com.linepro.modellbahn.repository.AufbauRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "AufbauService")
public class AufbauService extends NamedItemServiceImpl<AufbauModel,Aufbau> implements ItemService<AufbauModel> {

    private final AufbauRepository repository;

    private final FileService fileService;

    @Autowired
    public AufbauService(AufbauRepository repository, AufbauModelMutator modelMutator, AufbauMutator entityMutator, FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<AufbauModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.AUFBAU, ApiNames.ABBILDUNG, name));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<AufbauModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(name));
    }
}
