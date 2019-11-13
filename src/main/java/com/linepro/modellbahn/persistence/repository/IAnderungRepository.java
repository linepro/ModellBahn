package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Anderung;
import com.linepro.modellbahn.persistence.util.IItemRepository;

@Repository
public interface IAnderungRepository extends IItemRepository<Anderung> {

    Anderung findByArtikelIdAndAnderungId(String artikelId, Integer anderungId);
}
