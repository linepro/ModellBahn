package com.linepro.modellbahn.service.impl;

/**
 * SondermodellService. CRUD service for Sondermodell
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
import com.linepro.modellbahn.converter.entity.SondermodellMapper;
import com.linepro.modellbahn.converter.request.SondermodellRequestMapper;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.SondermodellModel;
import com.linepro.modellbahn.repository.SondermodellRepository;
import com.linepro.modellbahn.repository.lookup.SondermodellLookup;
import com.linepro.modellbahn.request.SondermodellRequest;

@Service(PREFIX + "SondermodellService")
public class SondermodellService extends NamedItemServiceImpl<SondermodellModel, SondermodellRequest, Sondermodell> {

    private final SondermodellRepository repository;
    private final FileService fileService;

    @Autowired
    public SondermodellService(SondermodellRepository repository, SondermodellRequestMapper requestMapper, SondermodellMapper entityMapper,
                               FileService fileService, SondermodellLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<SondermodellModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.SONDERMODELL, ApiNames.ABBILDUNG, name));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<SondermodellModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }
}
