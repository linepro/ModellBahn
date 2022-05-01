package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Service;

import com.linepro.modellbahn.persistence.DBNames;

@Service(PREFIX + "AchsfolgNameGenerator")
public class AchsfolgNameGenerator extends AbstractNameGenerator {

    public AchsfolgNameGenerator(String permittedChars, boolean withBrackets, int maxLength) {
        super(DBNames.NAME_PATTERN, false, 50);
    }
}
