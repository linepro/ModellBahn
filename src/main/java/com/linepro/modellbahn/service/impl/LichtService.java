package com.linepro.modellbahn.service.impl;

/**
 * LichtService. CRUD service for Licht
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
import com.linepro.modellbahn.converter.entity.LichtMapper;
import com.linepro.modellbahn.converter.request.LichtRequestMapper;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.LichtModel;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.repository.lookup.LichtLookup;
import com.linepro.modellbahn.request.LichtRequest;

@Service(PREFIX + "LichtService")
public class LichtService extends NamedItemServiceImpl<LichtModel, LichtRequest, Licht> {

    private final LichtRepository repository;

    private final FileService fileService;

    @Autowired
    public LichtService(LichtRepository repository, LichtRequestMapper requestMapper, LichtMapper entityMapper, FileService fileService, LichtLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<LichtModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.LICHT, ApiNames.ABBILDUNG, name));
                            return repository.saveAndFlush(a);
                            })
                        .flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<LichtModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return repository.saveAndFlush(a);
                            })
                        .flatMap(e -> this.get(name));
    }
}
