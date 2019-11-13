package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IAntriebRepository extends INamedItemRepository<Antrieb> {
}
