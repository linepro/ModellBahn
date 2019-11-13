package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IGattungRepository extends INamedItemRepository<Gattung> {
}
