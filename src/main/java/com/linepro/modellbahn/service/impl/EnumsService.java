package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.AdressTypModel;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.enums.AnderungsTypModel;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.DecoderStatusModel;
import com.linepro.modellbahn.model.enums.DescribedEnum;
import com.linepro.modellbahn.model.enums.DescribedEnumModel;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.KonfigurationModel;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.model.enums.LeistungsUbertragungModel;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.enums.StatusModel;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.model.enums.SteckerModel;

import lombok.RequiredArgsConstructor;

@Service(PREFIX + "EnumsService")
@RequiredArgsConstructor
public class EnumsService {

    @Autowired
    private final MessageTranslator messageTranslator;

    public Page<AdressTypModel> getAdressTyp() {
        return new PageImpl<>(Stream.of(AdressTyp.values())
                        .map(v -> getModel(AdressTypModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), AdressTyp.values().length);
    }

    public Page<AnderungsTypModel> getAnderungTyp() {
        return new PageImpl<>(Stream.of(AnderungsTyp.values())
                        .map(v -> getModel(AnderungsTypModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), AnderungsTyp.values().length);
    }

    public Page<DecoderStatusModel> getDecoderStatus() {
        return new PageImpl<>(Stream.of(DecoderStatus.values())
                        .map(v -> getModel(DecoderStatusModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), DecoderStatus.values().length);
    }

    public Page<SteckerModel> getStecker() {
        return new PageImpl<>(Stream.of(Stecker.values())
                        .map(v -> getModel(SteckerModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), Stecker.values().length);
    }

    public Page<KonfigurationModel> getKonfiguration() {
        return new PageImpl<>(Stream.of(Konfiguration.values())
                        .map(v -> getModel(KonfigurationModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), Konfiguration.values().length);
    }

    public Page<StatusModel> getStatus() {
        return new PageImpl<>(Stream.of(Status.values())
                        .map(v -> getModel(StatusModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), Status.values().length);
    }

    public Page<LeistungsUbertragungModel> getLeistungsUbertragung() {
        return new PageImpl<>(Stream.of(LeistungsUbertragung.values())
                        .map(v -> getModel(LeistungsUbertragungModel::new, v))
                        .collect(Collectors.toList()), Pageable.unpaged(), LeistungsUbertragung.values().length);
    }

    private <M extends DescribedEnumModel, E extends DescribedEnum> M getModel(Supplier<M> supplier, E value) {
        M model = supplier.get();
        model.setName(value.getName());
        model.setBezeichnung(translate(value.getBezeichnung()));
        model.setTooltip(translate(value.getTooltip()));
        return model;
    }

    private String translate(String e) {
        if (StringUtils.hasText(e)) {
            return messageTranslator.getMessage(e);
        }

        return e;
    }
}
