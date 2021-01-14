package com.linepro.modellbahn.service.impl;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.STRING;
import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(PREFIX + "EnumsService")
@RequiredArgsConstructor
public class EnumsService {

    protected static final String CODE = "Code";
    protected static final String COUNTRY = "Country";
    protected static final String DECIMALS = "Decimals";
    protected static final String ISO3 = "ISO3";
    protected static final String NAME = "Name";
    protected static final String SYMBOL = "Symbol";

    protected static final CsvSchema ISO_4217_FORMAT = CsvSchema.builder()
                                                                .addColumn(ISO3, STRING)
                                                                .addColumn(NAME, STRING)
                                                                .addColumn(COUNTRY, STRING)
                                                                .addColumn(DECIMALS, NUMBER)
                                                                .addColumn(SYMBOL, STRING)
                                                                .addColumn(CODE, NUMBER)
                                                                .setUseHeader(true)
                                                                .build();

    @Value("classpath:iso4217.csv")
    protected Resource iso4217;

    @Autowired
    private final MessageTranslator messageTranslator;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsoCodeEntry {

        @JsonProperty(ISO3)
        private String iso3;

        @JsonProperty(NAME)
        private String name;

        @JsonProperty(COUNTRY)
        private String country;

        @JsonProperty(DECIMALS)
        private Integer decimals;

        @JsonProperty(SYMBOL)
        private String symbol;

        @JsonProperty(CODE)
        private Integer code;
    }

    protected Map<String,IsoCodeEntry> isoCodes = new HashMap<>(); 

    @PostConstruct
    public void initialize() {
        ObjectReader reader = CsvMapper.builder().build().readerFor(IsoCodeEntry.class).with(ISO_4217_FORMAT);

        try (MappingIterator<IsoCodeEntry> it = reader.readValues(iso4217.getInputStream())) {
            while (it.hasNext()) {
                IsoCodeEntry entry = it.next();
                isoCodes.put(entry.getIso3(), entry);
                log.debug("Loaded {}", entry);
            }
        } catch (Exception e) {
            log.error("Failed loading iso codes {}", e.getMessage());
        }
    }

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
        return getPage(Stream.of(Locale.getISOCountries())
                             .map(c -> new Locale("", c))
                             .map(this::getLandModel)
                             .sorted((l1, l2) -> l1.getBezeichnung().compareTo(l2.getBezeichnung()))
                             .collect(Collectors.toList())
                      );
    }

    protected LandModel getLandModel(Locale country) {
        return new LandModel(
                        country.getCountry(),
                        country.getDisplayCountry(LocaleContextHolder.getLocale())
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
        return getPage(Currency.getAvailableCurrencies()
                               .stream()
                               .map(this::getWahrungModel)
                               .sorted((c1, c2) -> c1.getBezeichnung().compareTo(c2.getBezeichnung()))
                               .collect(Collectors.toList())
                      );
    }

    protected WahrungModel getWahrungModel(Currency c) {
        Locale locale = LocaleContextHolder.getLocale();

        final IsoCodeEntry entry = isoCodes.get(c.getCurrencyCode());

        String symbol = entry != null ? entry.getSymbol() : null;

        return new WahrungModel(
                        c.getCurrencyCode(),
                        c.getDisplayName(locale),
                        c.getCurrencyCode(),
                        symbol, 
                        c.getDefaultFractionDigits()
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
