package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface SteuerungRepository extends NamedItemRepository<Steuerung> {
}
