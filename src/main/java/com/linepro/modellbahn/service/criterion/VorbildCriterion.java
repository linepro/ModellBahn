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
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.persistence.DBNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VorbildCriterion extends AbstractCriterion {

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Unique name, use gattung, bauhreihe or betreibsnummer as appropriate", example = "BR 89.0", required = true)
    private String name;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Rolling stock class", example = "BR 89.0", required = true)
    private String gattung;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String unterKategorie;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", required = true)
    private String bahnverwaltung;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manunfacturer", example = "Henschel")
    private String hersteller;

    @JsonProperty(ApiNames.BAUZEIT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.MENGE)
    @Schema(description = "Number built", example = "10")
    private Integer Menge;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsNummer;

    @JsonProperty(ApiNames.ANTRIEB)
    @Schema(description = "Drive method")
    private String antrieb;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration")
    private String achsfolg;

    @JsonProperty(ApiNames.ANFAHRZUGKRAFT)
    @Schema(description = "Tractive Effort in kN", example = "300")
    private BigDecimal anfahrzugkraft;

    @JsonProperty(ApiNames.LEISTUNG)
    @Schema(description = "Power in kW", example = "385")
    private BigDecimal leistung;

    @JsonProperty(ApiNames.DIENSTGEWICHT)
    @Schema(description = "Service weight in metric tons", example = "46")
    private BigDecimal dienstgewicht;

    @JsonProperty(ApiNames.GESCHWINDIGKEIT)
    @Schema(description = "Maximum speed in km/h", example = "45")
    private Integer geschwindigkeit;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in mm", example = "9600")
    private BigDecimal lange;

    @JsonProperty(ApiNames.AUSSERDIENST)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Out of service date", example = "1962")
    private LocalDate ausserdienst;

    @JsonProperty(ApiNames.DMTREIBRAD)
    @Schema(description = "Drive wheel diamerter in mm", example = "1100")
    private BigDecimal dmTreibrad;

    @JsonProperty(ApiNames.DMLAUFRADVORN)
    @Schema(description = "Running wheel diameter front", example = "0")
    private BigDecimal dmLaufradVorn;

    @JsonProperty(ApiNames.DMLAUFRADHINTEN)
    @Schema(description = "Running wheel diameter rear", example = "0")
    private BigDecimal dmLaufradHinten;

    @JsonProperty(ApiNames.ZYLINDER)
    @Schema(description = "Number of cylinders", example = "2")
    private Integer Zylinder;

    @JsonProperty(ApiNames.DMZYLINDER)
    @Schema(description = "Cylinder diameter", example = "500")
    private BigDecimal dmZylinder;

    @JsonProperty(ApiNames.KOLBENHUB)
    @Schema(description = "Cylinder stroke", example = "550")
    private BigDecimal kolbenhub;

    @JsonProperty(ApiNames.KESSELUBERDRUCK)
    @Schema(description = "Boiler pressure in bar", example = "14")
    private BigDecimal kesseluberdruck;

    @JsonProperty(ApiNames.ROSTFLACHE)
    @Schema(description = "Grate area in m²", example = "1.42")
    private BigDecimal rostflache;

    @JsonProperty(ApiNames.UBERHITZERFLACHE)
    @Schema(description = "Super heater area in m²", example = "11.9")
    private BigDecimal uberhitzerflache;

    @JsonProperty(ApiNames.WASSERVORRAT)
    @Schema(description = "Water capactity in m³", example = "5.5")
    private BigDecimal wasservorrat;

    @JsonProperty(ApiNames.VERDAMPFUNG)
    @Schema(description = "Evaporative heater area in m²", example = "118.4")
    private BigDecimal verdampfung;

    @JsonProperty(ApiNames.FAHRMOTOREN)
    @Schema(description = "Number of drive motors", example = "1")
    private Integer fahrmotoren;

    @JsonProperty(ApiNames.MOTORBAUART)
    @Schema(description = "Engine manufacturer and model (IC engines)", example = "Henschel 12V1516A")
    private String motorbauart;

    @JsonProperty(ApiNames.LEISTUNGSUBERTRAGUNG)
    @Schema(description = "Power transfer method (IC engines)", example = "MECHANISH")
    private LeistungsUbertragung leistungsUbertragung;

    @JsonProperty(ApiNames.REICHWEITE)
    @Schema(description = "Range (fueled vehicles) km", example = "500")
    private BigDecimal reichweite;

    @JsonProperty(ApiNames.KAPAZITAT)
    @Schema(description = "Battery capacity in kwH", example = "190")
    private BigDecimal kapazitat;

    @JsonProperty(ApiNames.KLASSE)
    @Schema(description = "Number of classes (passenger wagens / multiple units)", example = "2")
    private Integer klasse;

    @JsonProperty(ApiNames.SITZPLATZEKL1)
    @Schema(description = "First class seating", example = "20")
    private Integer sitzplatzeKL1;

    @JsonProperty(ApiNames.SITZPLATZEKL2)
    @Schema(description = "Second class seating", example = "80")
    private Integer sitzplatzeKL2;

    @JsonProperty(ApiNames.SITZPLATZEKL3)
    @Schema(description = "Third class seating", example = "90")
    private Integer sitzplatzeKL3;

    @JsonProperty(ApiNames.SITZPLATZEKL4)
    @Schema(description = "Fourth class seating", example = "150")
    private Integer sitzplatzeKL4;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction", example = "Holz")
    private String aufbau;

    @JsonProperty(ApiNames.TRIEBKOPF)
    @Schema(description = "Number of drive wagons (multiple units)", example = "2")
    private Integer triebkopf;

    @JsonProperty(ApiNames.MITTELWAGEN)
    @Schema(description = "Number of middle wagons (multiple units)", example = "6")
    private Integer mittelwagen;

    @JsonProperty(ApiNames.DREHGESTELLBAUART)
    @Schema(description = "Bogie Manufacturer and type", example = "Y 25")
    private String drehgestellBauart;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> vorbild) {
        List<Predicate> where = new ArrayList<>();
        addJoinCondition(criteriaBuilder, vorbild, where, DBNames.GATTUNG, getGattung());
        addCondition(criteriaBuilder, vorbild, where, DBNames.BEZEICHNUNG, getBezeichnung());
        if (StringUtils.hasText(getKategorie()) || StringUtils.hasText(getUnterKategorie())) {
            Join<?,?> unterkategorie = vorbild.join(DBNames.UNTER_KATEGORIE);
            if (StringUtils.hasText(getKategorie())) {
                addJoinCondition(criteriaBuilder, unterkategorie, where, DBNames.KATEGORIE, getKategorie());
            }
            addCondition(criteriaBuilder, unterkategorie, where, DBNames.NAME, getUnterKategorie());
        }
        addCondition(criteriaBuilder, vorbild, where, DBNames.BAHNVERWALTUNG, getBahnverwaltung());
        addCondition(criteriaBuilder, vorbild, where, DBNames.HERSTELLER, getHersteller());
        addCondition(criteriaBuilder, vorbild, where, DBNames.BAUZEIT, getBauzeit());
        addCondition(criteriaBuilder, vorbild, where, DBNames.MENGE, getMenge());
        addCondition(criteriaBuilder, vorbild, where, DBNames.BETREIBSNUMMER, getBetreibsNummer());
        addJoinCondition(criteriaBuilder, vorbild, where, DBNames.ANTRIEB, getAntrieb());
        addJoinCondition(criteriaBuilder, vorbild, where, DBNames.ACHSFOLG, getAchsfolg());
        addCondition(criteriaBuilder, vorbild, where, DBNames.ANFAHRZUGKRAFT, getAnfahrzugkraft());
        addCondition(criteriaBuilder, vorbild, where, DBNames.LEISTUNG, getLeistung());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DIENSTGEWICHT, getDienstgewicht());
        addCondition(criteriaBuilder, vorbild, where, DBNames.GESCHWINDIGKEIT, getGeschwindigkeit());
        addCondition(criteriaBuilder, vorbild, where, DBNames.LANGE, getLange());
        addCondition(criteriaBuilder, vorbild, where, DBNames.AUSSERDIENST, getAusserdienst());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DMTREIBRAD, getDmTreibrad());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DMLAUFRADVORN, getDmLaufradVorn());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DMLAUFRADHINTEN, getDmLaufradHinten());
        addCondition(criteriaBuilder, vorbild, where, DBNames.ZYLINDER, getZylinder());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DMZYLINDER, getDmZylinder());
        addCondition(criteriaBuilder, vorbild, where, DBNames.KOLBENHUB, getKolbenhub());
        addCondition(criteriaBuilder, vorbild, where, DBNames.KESSELUBERDRUCK, getKesseluberdruck());
        addCondition(criteriaBuilder, vorbild, where, DBNames.ROSTFLACHE, getRostflache());
        addCondition(criteriaBuilder, vorbild, where, DBNames.UBERHITZERFLACHE, getUberhitzerflache());
        addCondition(criteriaBuilder, vorbild, where, DBNames.WASSERVORRAT, getWasservorrat());
        addCondition(criteriaBuilder, vorbild, where, DBNames.VERDAMPFUNG, getVerdampfung());
        addCondition(criteriaBuilder, vorbild, where, DBNames.FAHRMOTOREN, getFahrmotoren());
        addCondition(criteriaBuilder, vorbild, where, DBNames.MOTORBAUART, getMotorbauart());
        addCondition(criteriaBuilder, vorbild, where, DBNames.LEISTUNGSUBERTRAGUNG, getLeistungsUbertragung());
        addCondition(criteriaBuilder, vorbild, where, DBNames.REICHWEITE, getReichweite());
        addCondition(criteriaBuilder, vorbild, where, DBNames.KAPAZITAT, getKapazitat());
        addCondition(criteriaBuilder, vorbild, where, DBNames.KLASSE, getKlasse());
        addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL1, getSitzplatzeKL1());
        addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL2, getSitzplatzeKL2());
        addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL3, getSitzplatzeKL3());
        addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL4, getSitzplatzeKL4());
        addCondition(criteriaBuilder, vorbild, where, DBNames.AUFBAU, getAufbau());
        addCondition(criteriaBuilder, vorbild, where, DBNames.TRIEBKOPF, getTriebkopf());
        addCondition(criteriaBuilder, vorbild, where, DBNames.MITTELWAGEN, getMittelwagen());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DREHGESTELLBAUART, getDrehgestellBauart());
        addCondition(criteriaBuilder, vorbild, where, DBNames.DELETED, getDeleted());

        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "vorbild.lookup";
    }
}
