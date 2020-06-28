package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("SondermodellRepository")
public interface SondermodellRepository extends NamedItemRepository<Sondermodell> {
}
