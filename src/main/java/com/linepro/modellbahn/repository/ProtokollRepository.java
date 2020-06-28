package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("ProtokollRepository")
public interface ProtokollRepository extends NamedItemRepository<Protokoll> {
}
