package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface AchsfolgRepository extends NamedItemRepository<Achsfolg> {
}
