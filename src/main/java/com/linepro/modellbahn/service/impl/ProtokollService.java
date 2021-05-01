package com.linepro.modellbahn.service.impl;

/**
 * ProtokollService. CRUD service for Protokoll
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
import com.linepro.modellbahn.converter.entity.ProtokollMutator;
import com.linepro.modellbahn.converter.model.ProtokollModelMutator;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.ProtokollModel;
import com.linepro.modellbahn.repository.ProtokollRepository;

@Service(PREFIX + "ProtokollService")
public class ProtokollService extends NamedItemServiceImpl<ProtokollModel, Protokoll> {

    private final FileService fileService;

    @Autowired
    public ProtokollService(ProtokollRepository repository, ProtokollModelMutator modelMutator, ProtokollMutator entityMutator,
                    FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.fileService = fileService;
    }

    @Transactional
    public Optional<ProtokollModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name).map(a -> {
            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PROTOKOLL, ApiNames.ABBILDUNG, name));
            return repository.saveAndFlush(a);
        }).flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<ProtokollModel> deleteAbbildung(String name) {
        return repository.findByName(name).map(a -> { a.setAbbildung(fileService.deleteFile(a.getAbbildung())); return repository.saveAndFlush(a); })
                        .flatMap(e -> this.get(name));
    }
}
