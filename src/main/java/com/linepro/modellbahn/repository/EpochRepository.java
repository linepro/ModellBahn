package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("EpochRepository")
public interface EpochRepository extends NamedItemRepository<Epoch> {
}
