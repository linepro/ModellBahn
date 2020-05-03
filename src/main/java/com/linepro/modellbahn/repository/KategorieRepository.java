package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface KategorieRepository extends NamedItemRepository<Kategorie> {
}
