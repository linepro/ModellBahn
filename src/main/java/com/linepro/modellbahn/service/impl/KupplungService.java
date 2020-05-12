package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.FileService;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.AufbauModel;
import com.linepro.modellbahn.model.KupplungModel;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * KupplungService. CRUD service for Kupplung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class KupplungService extends NamedItemServiceImpl<KupplungModel,Kupplung> implements ItemService<KupplungModel> {

    protected final FileService fileService;
    
    @Autowired
    public KupplungService(KupplungRepository repository, FileService fileService) {
        super(repository, () -> new KupplungModel(), () -> new Kupplung());
        
        this.fileService = fileService;
    }

    public Optional<AufbauModel> updateAbbildung(String name, MultipartFile multipart) {
        // TODO Auto-generated method stub
        return null;
    }

    public Optional<AufbauModel> deleteAbbildung(String name) {
        // TODO Auto-generated method stub
        return null;
    }
}
