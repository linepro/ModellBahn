package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Land;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface LandRepository extends NamedItemRepository<Land> {
}
