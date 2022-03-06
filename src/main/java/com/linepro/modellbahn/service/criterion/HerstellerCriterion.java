package com.linepro.modellbahn.service.criterion;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class HerstellerCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Hersteller coding", example = "MARKLIN", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Hersteller description", example = "Märklin")
    private String bezeichnung;

    @JsonProperty(ApiNames.LAND)
    @Schema(description = "Country ISO 3166 Code", example = "DE")
    private String land;

    @JsonProperty(ApiNames.URL)
    @Schema(description = "Manufacturer's website", example = "https://www.maerklin.de")
    private String url;

    @JsonProperty(ApiNames.TELEFON)
    @Schema(description = "Manufacturer's phone number", example = "+49 (0) 71 61 608-0")
    private String telefon;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<?> root) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, root, where, DBNames.NAME, getName());
        addCondition(criteriaBuilder, root, where, DBNames.BEZEICHNUNG, getBezeichnung());
        addCondition(criteriaBuilder, root, where, DBNames.LAND, getLand());
        addCondition(criteriaBuilder, root, where, DBNames.URL, getUrl());
        addCondition(criteriaBuilder, root, where, DBNames.TELEFON, getTelefon());
        addCondition(criteriaBuilder, root, where, DBNames.DELETED, getDeleted());
        return asArray(where);
    }
}
