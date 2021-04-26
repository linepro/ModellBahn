package com.linepro.modellbahn.service.impl;

/**
 * MotorTypService. CRUD service for MotorTyp
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
import com.linepro.modellbahn.converter.entity.MotorTypMutator;
import com.linepro.modellbahn.converter.model.MotorTypModelMutator;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.MotorTypModel;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "MotorTypService")
public class MotorTypService extends NamedItemServiceImpl<MotorTypModel, MotorTyp> implements ItemService<MotorTypModel> {

    private final FileService fileService;

    @Autowired
    public MotorTypService(MotorTypRepository repository, MotorTypModelMutator modelMutator, MotorTypMutator entityMutator, FileService fileService) {
        super(repository, modelMutator, entityMutator);

        this.fileService = fileService;
    }

    @Transactional
    public Optional<MotorTypModel> updateAbbildung(String name, MultipartFile multipart) {
        return repository.findByName(name).map(a -> {
            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.MOTOR_TYP, ApiNames.ABBILDUNG, name));
            return repository.saveAndFlush(a);
        }).flatMap(e -> this.get(name));
    }

    @Transactional
    public Optional<MotorTypModel> deleteAbbildung(String name) {
        return repository.findByName(name).map(a -> { a.setAbbildung(fileService.deleteFile(a.getAbbildung())); return repository.saveAndFlush(a); })
                        .flatMap(e -> this.get(name));
    }
}
