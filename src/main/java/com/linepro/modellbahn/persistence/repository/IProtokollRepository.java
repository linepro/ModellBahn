package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IProtokollRepository extends INamedItemRepository<Protokoll> {
}
