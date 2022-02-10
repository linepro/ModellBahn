package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.ZugConsistMapper;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ZugTranscriber")
public class ZugTranscriber extends NamedTranscriber<Zug, ZugModel> {

    private static final ArrayList<ZugConsistModel> KEIN_CONSISTEN = new ArrayList<>();

    private final ZugConsistMapper consistMapper;

    @Override
    public ZugModel apply(Zug source, ZugModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setZugTyp(getCode(source.getZugTyp()));

            destination.setConsist(
                            isAvailable(source.getConsist()) ?
                                source.getConsist()
                                      .stream()
                                      .sorted()
                                      .map(c -> attempt(() -> consistMapper.convert(c)))
                                      .collect(success())
                                      .getValue()
                                      .orElse(KEIN_CONSISTEN) :
                                KEIN_CONSISTEN
                                );

            if (CollectionUtils.isNotEmpty(destination.getConsist())) {
                destination.getConsist()
                           .get(destination.getConsist().size() - 1)
                           .setLast(true);

                destination.setLange(destination.getConsist()
                                                .stream()
                                                .map(c -> c.getLange())
                                                .reduce(BigDecimal.ZERO, BigDecimal::add));
            } else {
                destination.setLange(BigDecimal.ZERO);
            }
        }

        return super.apply(source, destination);
    }
}
