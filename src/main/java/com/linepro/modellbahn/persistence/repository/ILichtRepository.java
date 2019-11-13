package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface ILichtRepository extends INamedItemRepository<Licht> {
}
