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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.VorbildMutator;
import com.linepro.modellbahn.converter.model.VorbildModelMutator;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.VorbildRepository;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.service.criterion.VorbildCriterion;

@Service(PREFIX + "VorbildService")
public class VorbildService extends NamedItemServiceImpl<VorbildModel, Vorbild> {

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
    
    @Transactional
    public VorbildModel add(VorbildModel model) {
        Vorbild item = modelMutator.convert(model);
        item.setDeleted(false);
        Vorbild saveAndFlush = repository.saveAndFlush(item);
        return entityMutator.convert(saveAndFlush);
    }

    @Transactional
    protected Optional<VorbildModel> update(Lookup<Vorbild> lookup, VorbildModel model) {
        return lookup.find()
                     .map(e -> entityMutator.convert(
                         repository.saveAndFlush(
                             modelMutator.apply(model,e)
                             )
                         )
                     );
    }

    public Optional<VorbildModel> updateAbbildung(String name, MultipartFile multipart) {
        return  repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.VORBILD, ApiNames.ABBILDUNG, name));
                             return entityMutator.convert(a);
                             });
    }

    public Optional<VorbildModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return entityMutator.convert(a);
                            });
    }
}
