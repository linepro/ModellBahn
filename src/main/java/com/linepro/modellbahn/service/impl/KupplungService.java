package com.linepro.modellbahn.service.impl;

/**
 * KupplungService. CRUD service for Kupplung
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.KupplungMutator;
import com.linepro.modellbahn.converter.model.KupplungModelMutator;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.KupplungModel;
import com.linepro.modellbahn.repository.KupplungRepository;

@Service(PREFIX + "KupplungService")
public class KupplungService extends NamedItemServiceImpl<KupplungModel,Kupplung> {

    private final KupplungRepository repository;

    private final FileService fileService;

    @Autowired
    public KupplungService(KupplungRepository repository, KupplungModelMutator modelMutator, KupplungMutator entityMutator, FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<KupplungModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.KUPPLUNG, ApiNames.ABBILDUNG, name));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<KupplungModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(name));
    }
}
