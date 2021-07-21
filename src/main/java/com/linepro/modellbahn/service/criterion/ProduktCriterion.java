package com.linepro.modellbahn.service.criterion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.request.ProduktTeilRequest;
import com.linepro.modellbahn.util.impexp.impl.SuppressExport;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProduktCriterion extends AbstractCriterion {


    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "Marklin", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale")
    private String massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge")
    private String spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA")
    private String epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company")
    private String bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class")
    private String gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.BAUZEIT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration")
    private String achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator")
    private String sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction")
    private String aufbau;

    @JsonProperty(ApiNames.LICHT)
    @Schema(description = "Light configuration")
    private String licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(description = "Coupling configuration")
    private String kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(description = "Control method")
    private String steuerung;

    @JsonProperty(ApiNames.DECODER_HERSTELLER)
    @Schema(description = "Decoder type")
    private String decoderTypHersteller;

    @JsonProperty(ApiNames.DECODER_BESTELL_NR)
    @Schema(description = "Decoder type")
    private String decoderTypBestellNr;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type")
    private String motorTyp;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "Ex set")
    private String anmerkung;

    @SuppressExport
    @JsonProperty(ApiNames.TEILEN)
    @Schema(implementation = ProduktTeilRequest.class, description = "Product components", accessMode = AccessMode.READ_ONLY)
    private List<ProduktTeilRequest> teilen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> produkt) {
        List<Predicate> where = new ArrayList<>();
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.HERSTELLER, getHersteller());
        addCondition(criteriaBuilder, produkt, where, DBNames.BESTELL_NR, getBestellNr());
        addCondition(criteriaBuilder, produkt, where, DBNames.BEZEICHNUNG, getBezeichnung());
        if (StringUtils.hasText(getKategorie()) || StringUtils.hasText(getUnterKategorie())) {
            Join<?,?> unterkategorie = produkt.join(DBNames.UNTER_KATEGORIE);
            if (StringUtils.hasText(getKategorie())) {
                addJoinCondition(criteriaBuilder, unterkategorie, where, DBNames.KATEGORIE, getKategorie());
            }
            addCondition(criteriaBuilder, unterkategorie, where, DBNames.NAME, getUnterKategorie());
        }
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.MASSSTAB, getMassstab());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.SPURWEITE, getSpurweite());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.EPOCH, getEpoch());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.BAHNVERWALTUNG, getBahnverwaltung());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.GATTUNG, getGattung());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.ACHSFOLG, getAchsfolg());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.SONDERMODELL, getSondermodell());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.AUFBAU, getAufbau());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.LICHT, getLicht());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.VORBILD, getGattung());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.KUPPLUNG, getKupplung());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.STEUERUNG, getSteuerung());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.DECODER_TYP, getDecoderTypHersteller());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.DECODER_TYP, getDecoderTypBestellNr());
        addJoinCondition(criteriaBuilder, produkt, where, DBNames.MOTOR_TYP, getMotorTyp());
        addCondition(criteriaBuilder, produkt, where, DBNames.ANMERKUNG, getAnmerkung());
        addCondition(criteriaBuilder, produkt, where, DBNames.BETREIBSNUMMER, getBetreibsnummer());
        addCondition(criteriaBuilder, produkt, where, DBNames.BAUZEIT, getBauzeit());
        addCondition(criteriaBuilder, produkt, where, DBNames.LANGE, getLange());
        addCondition(criteriaBuilder, produkt, where, DBNames.DELETED, getDeleted());

        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "produkt.noChildren";
    }
}
