package com.linepro.modellbahn.service.impl;

/**
 * VorbildService. CRUD service for Vorbild
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.VorbildMutator;
import com.linepro.modellbahn.converter.model.VorbildModelMutator;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.VorbildRepository;
import com.linepro.modellbahn.service.ItemService;
import com.linepro.modellbahn.service.criterion.VorbildCriterion;

@Service(PREFIX + "VorbildService")

public class VorbildService extends ItemServiceImpl<VorbildModel, Vorbild> implements ItemService<VorbildModel> {

    private final VorbildRepository repository;
    
    private final FileService fileService;

    @Autowired
    public VorbildService(VorbildRepository repository, FileService fileService, VorbildModelMutator vorbildModelMutator, VorbildMutator vorbildMutator) {
        super(repository, vorbildModelMutator, vorbildMutator);
        this.repository = repository;
        this.fileService = fileService;
    }

    @Override
    protected Page<Vorbild> findAll(Optional<VorbildModel> model, Pageable pageRequest) {
        return repository.findAll(new VorbildCriterion(model), pageRequest);
    }

    public Optional<VorbildModel> get(String gattung) {
        return super.get(() -> repository.findByGattung(gattung));
    }

    public Optional<VorbildModel> update(String gattung, VorbildModel model) {
        return super.update(() -> repository.findByGattung(gattung), model);
    }

    public boolean delete(String gattung) {
        return super.delete(() -> repository.findByGattung(gattung));
    }

    public Optional<VorbildModel> updateAbbildung(String gattung, MultipartFile multipart) {
        return  repository.findByGattung(gattung)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.VORBILD, ApiNames.ABBILDUNG, gattung));
                             return entityMutator.convert(a);
                             });
    }

    public Optional<VorbildModel> deleteAbbildung(String gattung) {
        return repository.findByGattung(gattung)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return entityMutator.convert(a);
                            });
    }
}
