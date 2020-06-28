package com.linepro.modellbahn.repository.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.repository.DecoderTypRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component("DecoderTypLookup")
public class DecoderTypLookup {
    
    @Autowired
    private final DecoderTypRepository repository;

    public DecoderTyp find(String hersteller, String bestellNr) {
        if (StringUtils.hasText(hersteller) && StringUtils.hasText(bestellNr)) {
            return repository.findByBestellNr(hersteller, bestellNr)
                             .orElse(null);
        }
        
        return null;
    }
}
