package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IBahnverwaltungRepository extends INamedItemRepository<Bahnverwaltung> {
}
