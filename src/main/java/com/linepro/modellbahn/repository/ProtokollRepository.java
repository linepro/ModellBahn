package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "ProtokollRepository")
public interface ProtokollRepository extends NamedItemRepository<Protokoll> {
}
