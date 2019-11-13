package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface ISonderModellRepository extends INamedItemRepository<SonderModell> {
}
