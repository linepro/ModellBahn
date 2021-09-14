package com.linepro.modellbahn.service.impl;

/**
 * MotorTypService. CRUD service for MotorTyp
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
import com.linepro.modellbahn.converter.entity.MotorTypMapper;
import com.linepro.modellbahn.converter.request.MotorTypRequestMapper;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.MotorTypModel;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.repository.lookup.MotorTypLookup;
import com.linepro.modellbahn.request.MotorTypRequest;

@Service(PREFIX + "MotorTypService")
public class MotorTypService extends NamedItemServiceImpl<MotorTypModel, MotorTypRequest,  MotorTyp> {

    private final MotorTypRepository repository;
    private final FileService fileService;

    @Autowired
    public MotorTypService(MotorTypRepository repository, MotorTypRequestMapper requestMapper, MotorTypMapper entityMapper, FileService fileService, MotorTypLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.fileService = fileService;
    }

    @Transactional
    public Optional<MotorTypModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.MOTOR_TYP, ApiNames.ABBILDUNG, name));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<MotorTypModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }
}
