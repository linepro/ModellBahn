package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.ZugModelTranscriber;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

@Component(PREFIX + "ZugModelMutator")
public class ZugModelMutator extends MutatorImpl<ZugModel, Zug> {

    @Autowired
    public ZugModelMutator(ZugTypRepository repository, ItemLookup lookup) {
        super(() -> new Zug(), new ZugModelTranscriber(repository, lookup));
    }
}
