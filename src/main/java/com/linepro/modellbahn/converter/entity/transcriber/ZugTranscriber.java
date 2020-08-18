package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import com.linepro.modellbahn.converter.entity.ZugConsistMutator;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZugTranscriber extends NamedTranscriber<Zug, ZugModel> {

    private final ZugConsistMutator consistMutator;

    @Override
    public ZugModel apply(Zug source, ZugModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setZugTyp(getCode(source.getZugTyp()));

            if (isAvailable(source.getConsist())) {
                destination.setConsist(source.getConsist().stream().sorted().map(c -> attempt(() -> consistMutator.convert(c))).collect(success())
                                .orElseThrow());
            }
        }

        return super.apply(source, destination);
    }
}
