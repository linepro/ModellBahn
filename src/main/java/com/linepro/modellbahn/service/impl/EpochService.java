package com.linepro.modellbahn.service.impl;

/**
 * EpochService. CRUD service for Epoch
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
import com.linepro.modellbahn.converter.entity.EpochMapper;
import com.linepro.modellbahn.converter.request.EpochRequestMapper;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.EpochModel;
import com.linepro.modellbahn.repository.EpochRepository;
import com.linepro.modellbahn.repository.lookup.EpochLookup;
import com.linepro.modellbahn.request.EpochRequest;

@Service(PREFIX + "EpochService")
public class EpochService extends NamedItemServiceImpl<EpochModel, EpochRequest,  Epoch> {

    private final EpochRepository repository;

    private final FileService fileService;

    @Autowired
    public EpochService(EpochRepository repository, EpochRequestMapper requestMapper, EpochMapper entityMapper, FileService fileService, EpochLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<EpochModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.EPOCH, ApiNames.ABBILDUNG, name));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<EpochModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }
}
