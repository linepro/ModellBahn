package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IKategorieRepository extends INamedItemRepository<Kategorie> {
}
