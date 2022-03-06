package com.linepro.modellbahn.service.criterion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.persistence.DBNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DecoderCriterion extends AbstractCriterion {


    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "ESU", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String artikelId;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Decoder's description", example = "ESU Loksound")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(description = "Maximum current in A", example = "1.100", accessMode = AccessMode.READ_ONLY)
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(description = "Decoder protocol", example = "MFX", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(description = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(description = "True if decoder supports sound", example = "true", accessMode = AccessMode.READ_ONLY)
    private Boolean sound;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(description = "Configuration method", example = "CV", accessMode = AccessMode.READ_ONLY)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(description = "Stecker", example = "NEM352", accessMode = AccessMode.READ_ONLY)
    private Stecker stecker;

    @JsonProperty(ApiNames.KAUFDATUM)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Purchase date", example = "1967-08-10")
    private LocalDate kaufdatum;

    @JsonProperty(ApiNames.WAHRUNG)
    @Schema(description = "Purchase currency, ISO 4217 code", example = "EUR")
    private String wahrung;

    @JsonProperty(ApiNames.PREIS)
    @Schema(description = "Purchase price", example = "115.95")
    private BigDecimal preis;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "ESU Project nnnn")
    private String anmerkung;

    @JsonProperty(ApiNames.STATUS)
    @Schema(description = "Decoder status", example = "INSTALIERT", required = true)
    private DecoderStatus status;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<?> decoder) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, decoder, where, DBNames.DECODER_ID, getDecoderId());
        addCondition(criteriaBuilder, decoder, where, DBNames.BEZEICHNUNG, getBezeichnung());
        if (StringUtils.hasText(getHersteller()) || StringUtils.hasText(getBestellNr()) || getIMax() != null ||
            getSound() != null || getKonfiguration() != null || getStecker() != null) {
            Join<?,?> decoderTyp = decoder.join(DBNames.DECODER_TYP);
            List<Predicate> on = new ArrayList<>();
            addJoinCondition(criteriaBuilder, decoderTyp, on, DBNames.HERSTELLER, DBNames.BEZEICHNUNG, getHersteller());
            addCondition(criteriaBuilder, decoderTyp, on, "bestellNr", getBestellNr());
            addCondition(criteriaBuilder, decoderTyp, on, DBNames.I_MAX, getIMax());
            addCondition(criteriaBuilder, decoderTyp, on, DBNames.SOUND, getSound());
            addCondition(criteriaBuilder, decoderTyp, on, DBNames.KONFIGURATION, getKonfiguration());
            addCondition(criteriaBuilder, decoderTyp, on, DBNames.STECKER, getStecker());
            decoderTyp.on(asArray(on));
        }
        addJoinCondition(criteriaBuilder, decoder, where, DBNames.PROTOKOLL, DBNames.BEZEICHNUNG, getProtokoll());
        addCondition(criteriaBuilder, decoder, where, DBNames.FAHRSTUFE, getFahrstufe());
        addCondition(criteriaBuilder, decoder, where, DBNames.STATUS, getStatus());
        addCondition(criteriaBuilder, decoder, where, DBNames.DELETED, getDeleted());

        return asArray(where);
    }

    @Override
    public String getGraphName() {
        return "decoder.noChildren";
    }
}
