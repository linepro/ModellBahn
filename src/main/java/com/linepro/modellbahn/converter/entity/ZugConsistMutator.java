package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ZugConsistMutator implements Mutator<ZugConsist, ZugConsistModel> {

    @Autowired
    private ArtikelMutator artikelConverter;

    @Override
    public ZugConsistModel applyFields(ZugConsist source, ZugConsistModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setZug(source.getZug().getName());
            destination.setPosition(source.getPosition());
            destination.setArtikel(artikelConverter.summarize(source.getArtikel()));
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public ZugConsistModel get() {
        return new ZugConsistModel();
    }
}
