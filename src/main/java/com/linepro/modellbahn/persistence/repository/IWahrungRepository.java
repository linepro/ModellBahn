package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IWahrungRepository extends INamedItemRepository<Wahrung> {
}
