package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("ZugTypRepository")
public interface ZugTypRepository extends NamedItemRepository<ZugTyp> {
}
