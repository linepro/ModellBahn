package com.linepro.modellbahn.service.impl;

/**
 * AntriebService. CRUD service for Antrieb
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.AntriebMapper;
import com.linepro.modellbahn.converter.request.AntriebRequestMapper;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.AntriebModel;
import com.linepro.modellbahn.repository.AntriebRepository;
import com.linepro.modellbahn.request.AntriebRequest;

@Service(PREFIX + "AntriebService")
public class AntriebService extends NamedItemServiceImpl<AntriebModel, AntriebRequest, Antrieb> {

    private final AntriebRepository repository;

    private final FileService fileService;

    @Autowired
    public AntriebService(AntriebRepository repository, AntriebRequestMapper requestMapper, AntriebMapper entityMapper, FileService fileService) {
        super(repository, requestMapper, entityMapper);

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
