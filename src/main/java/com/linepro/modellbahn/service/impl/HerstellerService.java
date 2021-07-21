package com.linepro.modellbahn.service.impl;

/**
 * HerstellerService. CRUD service for Hersteller
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
import com.linepro.modellbahn.converter.entity.HerstellerMutator;
import com.linepro.modellbahn.converter.request.HerstellerRequestMapper;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.request.HerstellerRequest;

@Service(PREFIX + "HerstellerService")
public class HerstellerService extends NamedItemServiceImpl<HerstellerModel, HerstellerRequest,  Hersteller> {

    private final FileService fileService;

    @Autowired
    public HerstellerService(HerstellerRepository repository, HerstellerRequestMapper requestMapper, HerstellerMutator entityMapper,
                    FileService fileService) {
        super(repository, requestMapper, entityMapper);

        this.fileService = fileService;
    }

    @Transactional
    public Optional<HerstellerModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name).map(a -> {
            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.HERSTELLER, ApiNames.ABBILDUNG, name));
            return repository.saveAndFlush(a);
        }).flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<HerstellerModel> deleteAbbildung(String name) {
        return repository.findByName(name).map(a -> { a.setAbbildung(fileService.deleteFile(a.getAbbildung())); return repository.saveAndFlush(a); })
                        .flatMap(e -> this.get(name));
    }
}
