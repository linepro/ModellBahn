package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "EpochRepository")
public interface EpochRepository extends NamedItemRepository<Epoch> {
}
