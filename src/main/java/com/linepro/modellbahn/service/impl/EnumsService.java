package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.linepro.modellbahn.model.enums.LandModel;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.model.enums.LeistungsUbertragungModel;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.enums.StatusModel;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.model.enums.SteckerModel;
import com.linepro.modellbahn.model.enums.WahrungModel;

import lombok.RequiredArgsConstructor;

@Service(PREFIX + "EnumsService")
@RequiredArgsConstructor
public class EnumsService {

    @Autowired
    private final MessageTranslator messageTranslator;

    public Page<AdressTypModel> getAdressTyp() {
        return getPage(AdressTypModel::new, AdressTyp.values());
    }

    public Page<AnderungsTypModel> getAnderungTyp() {
        return getPage(AnderungsTypModel::new, AnderungsTyp.values());
    }

    public Page<DecoderStatusModel> getDecoderStatus() {
        return getPage(DecoderStatusModel::new, DecoderStatus.values());
    }

    public Page<KonfigurationModel> getKonfiguration() {
        return getPage(KonfigurationModel::new, Konfiguration.values());
    }

    public Page<LandModel> getLand() {
        final Locale locale = LocaleContextHolder.getLocale();

        return getPage(Stream.of(Locale.getISOCountries())
                             .sorted((c1, c2) -> c1.compareTo(c2))
                             .map(c -> new Locale("", c))
                             .map(l -> new LandModel(
                                                 l.getCountry(), 
                                                 l.getDisplayCountry(locale) 
                                                 )
                             )
                             .collect(Collectors.toList())
                      );
    }

    public Page<LeistungsUbertragungModel> getLeistungsUbertragung() {
        return getPage(LeistungsUbertragungModel::new, LeistungsUbertragung.values());
    }

    public Page<StatusModel> getStatus() {
        return getPage(StatusModel::new, Status.values());
    }

    public Page<SteckerModel> getStecker() {
        return getPage(SteckerModel::new, Stecker.values());
    }

    public Page<WahrungModel> getWahrung() {
        final Locale locale = LocaleContextHolder.getLocale();

        return getPage(Currency.getAvailableCurrencies()
                               .stream()
                               .sorted((c1, c2) -> c1.getCurrencyCode().compareTo(c2.getCurrencyCode()))
                               .map(c -> new WahrungModel(
                                               c.getCurrencyCode(),
                                               c.getDisplayName(locale), 
                                               c.getSymbol(locale), 
                                               c.getDefaultFractionDigits()
                                               )
                               )
                               .collect(Collectors.toList())
                      );
    }

    private <I> Page<I> getPage(List<I> items) {
        return new PageImpl<I>(items, Pageable.unpaged(), items.size());
    }

    private <M extends DescribedEnumModel, E extends DescribedEnum> Page<M> getPage(Supplier<M> supplier, DescribedEnum[] items) {
        return getPage(Stream.of(items)
                             .map(v -> getModel(supplier, v))
                             .collect(Collectors.toList())
                             );
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
