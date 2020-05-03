package com.linepro.modellbahn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.UnterKategorieRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class UnterKategorieService extends AbstractNamedItemService<UnterKategorieModel, UnterKategorie> {

    @Autowired
    public UnterKategorieService(UnterKategorieRepository persister) {
        super(persister);
    }

    public ResponseEntity<?> search(List<String> kategorieen, PageRequest pageRequest) {
        // TODO Auto-generated method stub
        return null;
    }
}
