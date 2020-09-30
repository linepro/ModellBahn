package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "ZugTypRepository")
public interface ZugTypRepository extends NamedItemRepository<ZugTyp> {
}
