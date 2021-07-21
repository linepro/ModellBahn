package com.linepro.modellbahn.service.impl;

/**
 * VorbildService. CRUD service for Vorbild
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.VorbildMutator;
import com.linepro.modellbahn.converter.request.VorbildRequestMapper;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.VorbildRepository;
import com.linepro.modellbahn.request.VorbildRequest;

@Service(PREFIX + "VorbildService")
public class VorbildService extends NamedItemServiceImpl<VorbildModel, VorbildRequest,  Vorbild> {

    private final VorbildRepository repository;

    private final FileService fileService;

    @Autowired
    public VorbildService(VorbildRepository repository, FileService fileService, VorbildRequestMapper vorbildRequestMapper, VorbildMutator vorbildMutator) {
        super(repository, vorbildRequestMapper, vorbildMutator);
        this.repository = repository;
        this.fileService = fileService;
    }

    public Optional<VorbildModel> updateAbbildung(String name, MultipartFile multipart) {
        return  repository.findByName(name)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.VORBILD, ApiNames.ABBILDUNG, name));
                             return repository.saveAndFlush(a);
                             })
                         .flatMap(e -> this.get(name));
    }

    public Optional<VorbildModel> deleteAbbildung(String name) {
        return repository.findByName(name)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return  repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(name));
    }
}
