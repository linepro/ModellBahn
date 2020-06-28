package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("SpurweiteRepository")
public interface SpurweiteRepository extends NamedItemRepository<Spurweite> {
}
