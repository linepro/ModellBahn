package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.persistence.DBNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EpochCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "ERA", example = "III", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "ERA description", example = "III : 1949 - 1970")
    private String bezeichnung;

    @JsonProperty(ApiNames.START_YEAR)
    @Schema(description = "Start year", example = "1949")
    private Integer startYear;

    @JsonProperty(ApiNames.END_YEAR)
    @Schema(description = "End year", example = "1970")
    private Integer endYear;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> root) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, root, where, DBNames.NAME, getName());
        addCondition(criteriaBuilder, root, where, DBNames.BEZEICHNUNG, getBezeichnung());
        addCondition(criteriaBuilder, root, where, DBNames.DELETED, getDeleted());
        return where.toArray(new Predicate[0]);
    }
}
