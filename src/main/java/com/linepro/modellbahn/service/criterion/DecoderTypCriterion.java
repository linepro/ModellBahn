package com.linepro.modellbahn.service.criterion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.persistence.DBNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DecoderTypCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "LokSound M4")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(description = "Maximum current in mA", example = "1100")
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(description = "Default protocoll", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(description = "Default speed steps", example = "127", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(description = "True if decoder supports sound", example = "true", required = true)
    private Boolean sound;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(description = "Configuration method", example = "CV", required = true)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(description = "Stecker", example = "NEM352")
    private Stecker stecker;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> decoderTyp) {
        List<Predicate> where = new ArrayList<>();
        addJoinCondition(criteriaBuilder, decoderTyp, where, DBNames.HERSTELLER, getHersteller());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.BESTELL_NR, getBestellNr());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.BEZEICHNUNG, getBezeichnung());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.I_MAX, getIMax());
        addJoinCondition(criteriaBuilder, decoderTyp, where, DBNames.PROTOKOLL, getProtokoll());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.FAHRSTUFE, getFahrstufe());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.SOUND, getSound());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.KONFIGURATION, getKonfiguration());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.STECKER, getStecker());
        addCondition(criteriaBuilder, decoderTyp, where, DBNames.DELETED, getDeleted());

        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "decoderTyp.noChildren";
    }
}
