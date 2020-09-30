package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "MotorTypRepository")
public interface MotorTypRepository extends NamedItemRepository<MotorTyp> {
}
