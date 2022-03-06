package com.linepro.modellbahn.service.criterion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.persistence.DBNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZugCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Train code", example = "BAVARIA", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Train description", example = "TEE „Bavaria“")
    private String bezeichnung;

    @JsonProperty(ApiNames.ZUG_TYP)
    @Schema(description = "Train type", required = true)
    private String zugTyp;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00", accessMode = AccessMode.READ_ONLY)
    private BigDecimal lange;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<?> zug) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, zug, where, DBNames.NAME, getName());
        addCondition(criteriaBuilder, zug, where, DBNames.BEZEICHNUNG, getBezeichnung());
        addCondition(criteriaBuilder, zug, where, DBNames.DELETED, getDeleted());
        addJoinCondition(criteriaBuilder, zug, where, DBNames.ZUG_TYP, DBNames.BEZEICHNUNG, getZugTyp());
        return asArray(where);
    }

    @Override
    public String getGraphName() {
        return "zug.noChildren";
    }
}
