package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.persistence.DBNames;

@Primary
@Service(PREFIX + "NameGeneratorImpl")
public class NameGeneratorImpl extends AbstractNameGenerator {

    public NameGeneratorImpl() {
        super(DBNames.NAME_PATTERN, true, 50);
    }
}
