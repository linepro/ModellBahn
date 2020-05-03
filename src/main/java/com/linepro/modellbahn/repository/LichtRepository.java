package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface LichtRepository extends NamedItemRepository<Licht> {
}
