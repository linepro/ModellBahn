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
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.persistence.DBNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ArtikelCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String artikelId;

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
    @Schema(description = "Category", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00", accessMode = AccessMode.READ_ONLY)
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale", example = "H0", accessMode = AccessMode.READ_ONLY)
    private String massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge", example = "H0", accessMode = AccessMode.READ_ONLY)
    private String spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA", example = "IV", accessMode = AccessMode.READ_ONLY)
    private String epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", example = "DB", accessMode = AccessMode.READ_ONLY)
    private String bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", example = "BR89.0", accessMode = AccessMode.READ_ONLY)
    private String gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration", example = "CH2T", accessMode = AccessMode.READ_ONLY)
    private String achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator", example = "MHI", accessMode = AccessMode.READ_ONLY)
    private String sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction", accessMode = AccessMode.READ_ONLY)
    private String aufbau;

    @JsonProperty(ApiNames.LICHT)
    @Schema(description = "Light Configuration", example = "L1V")
    private String licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(description = "Coupling configuration", example = "RELEX")
    private String kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(description = "Control method", example = "Digital")
    private String steuerung;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder", example = "00001")
    private String decoder;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type", example = "5*")
    private String motorTyp;

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

    @JsonProperty(ApiNames.MENGE)
    @Schema(description = "Purchase Quantity", example = "1", required = true)
    private Integer menge;

    @JsonProperty(ApiNames.VERBLEIBENDE)
    @Schema(description = "Remaining Quantity", example = "1", required = true)
    private Integer verbleibende;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "5* Motor and decoder")
    private String anmerkung;

    @JsonProperty(ApiNames.BELADUNG)
    @Schema(description = "Wagon load", example = "holz")
    private String beladung;

    @JsonProperty(ApiNames.STATUS)
    @Schema(description = "Status", example = "GEKAUFT", required = true)
    private Status status;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<?> artikel) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, artikel, where, DBNames.ARTIKEL_ID, getArtikelId());
        if (StringUtils.hasText(getHersteller()) || StringUtils.hasText(getBestellNr())) {
            Join<?, ?> produkt = artikel.join(DBNames.PRODUKT);
            List<Predicate> on = new ArrayList<>();
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.HERSTELLER, DBNames.BEZEICHNUNG, getHersteller());
            addCondition(criteriaBuilder, produkt, on, "bestellNr", getBestellNr());
            if (StringUtils.hasText(getKategorie()) || StringUtils.hasText(getUnterKategorie())) {
                Join<?,?> unterkategorie = produkt.join(DBNames.UNTER_KATEGORIE);
                List<Predicate> onKategorie = new ArrayList<>();
                addJoinCondition(criteriaBuilder, unterkategorie, onKategorie, DBNames.KATEGORIE, DBNames.BEZEICHNUNG, getKategorie());
                addCondition(criteriaBuilder, unterkategorie, onKategorie, DBNames.BEZEICHNUNG, getUnterKategorie());
                unterkategorie.on(asArray(onKategorie));
            }
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.MASSSTAB, DBNames.BEZEICHNUNG, getMassstab());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.SPURWEITE, DBNames.BEZEICHNUNG, getSpurweite());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.EPOCH, DBNames.BEZEICHNUNG, getEpoch());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.BAHNVERWALTUNG, DBNames.BEZEICHNUNG, getBahnverwaltung());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.GATTUNG, DBNames.BEZEICHNUNG, getGattung());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.ACHSFOLG, DBNames.BEZEICHNUNG, getAchsfolg());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.SONDERMODELL, DBNames.BEZEICHNUNG, getSondermodell());
            addJoinCondition(criteriaBuilder, produkt, on, DBNames.AUFBAU, DBNames.BEZEICHNUNG, getAufbau());
            addCondition(criteriaBuilder, produkt, on, DBNames.BETREIBSNUMMER, getBetreibsnummer());
            addCondition(criteriaBuilder, produkt, on, DBNames.LANGE, getLange());
            produkt.on(asArray(on));
        }
        addCondition(criteriaBuilder, artikel, where, DBNames.BEZEICHNUNG, getBezeichnung());
        addJoinCondition(criteriaBuilder, artikel, where, DBNames.LICHT, DBNames.BEZEICHNUNG, getLicht());
        addJoinCondition(criteriaBuilder, artikel, where, DBNames.KUPPLUNG, DBNames.BEZEICHNUNG, getKupplung());
        addJoinCondition(criteriaBuilder, artikel, where, DBNames.STEUERUNG, DBNames.BEZEICHNUNG, getSteuerung());
        addJoinCondition(criteriaBuilder, artikel, where, DBNames.DECODER, DBNames.DECODER_ID, getDecoder());
        addJoinCondition(criteriaBuilder, artikel, where, DBNames.MOTOR_TYP, DBNames.BEZEICHNUNG, getMotorTyp());
        addCondition(criteriaBuilder, artikel, where, DBNames.KAUFDATUM, getKaufdatum());
        addCondition(criteriaBuilder, artikel, where, DBNames.WAHRUNG, getWahrung());
        addCondition(criteriaBuilder, artikel, where, DBNames.PREIS, getPreis());
        addCondition(criteriaBuilder, artikel, where, DBNames.MENGE, getMenge());
        addCondition(criteriaBuilder, artikel, where, DBNames.VERBLEIBENDE, getVerbleibende());
        addCondition(criteriaBuilder, artikel, where, DBNames.ANMERKUNG, getAnmerkung());
        addCondition(criteriaBuilder, artikel, where, DBNames.BELADUNG, getBeladung());
        addCondition(criteriaBuilder, artikel, where, DBNames.STATUS, getStatus());
        return asArray(where);
    }

    @Override
    public String getGraphName() {
        return "artikel.noChildren";
    }
}
