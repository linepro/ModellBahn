package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IVorbild.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.VORBILD)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.NAMEN, ApiNames.GATTUNG, ApiNames.BEZEICHNUNG, ApiNames.UNTER_KATEGORIE,
        ApiNames.BAHNVERWALTUNG, ApiNames.HERSTELLER, ApiNames.BAUZEIT, ApiNames.MENGE, ApiNames.BETREIBSNUMMER,
        ApiNames.ANTRIEB, ApiNames.ACHSFOLG, ApiNames.ANFAHRZUGKRAFT, ApiNames.LEISTUNG, ApiNames.DIENSTGEWICHT,
        ApiNames.GESCHWINDIGKEIT, ApiNames.LANGE, ApiNames.AUSSERDIENST, ApiNames.DMTREIBRAD, ApiNames.DMLAUFRADVORN,
        ApiNames.DMLAUFRADHINTEN, ApiNames.ZYLINDER, ApiNames.DMZYLINDER, ApiNames.KOLBENHUB, ApiNames.KESSELUBERDRUCK,
        ApiNames.ROSTFLACHE, ApiNames.UBERHITZERFLACHE, ApiNames.WASSERVORRAT, ApiNames.VERDAMPFUNG, ApiNames.STEUERUNG,
        ApiNames.FAHRMOTOREN, ApiNames.MOTORBAUART, ApiNames.LEISTUNGSUBERTRAGUNG, ApiNames.REICHWEITE,
        ApiNames.KAPAZITAT, ApiNames.KLASSE, ApiNames.SITZPLATZEKL1, ApiNames.SITZPLATZEKL2, ApiNames.SITZPLATZEKL3,
        ApiNames.SITZPLATZEKL4, ApiNames.AUFBAU, ApiNames.TRIEBKOPF, ApiNames.MITTELWAGEN, ApiNames.DREHGESTELLBAUART,
        ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.VORBILD)
@Schema(name = ApiNames.VORBILD, description = "A real world prototype.")
public class VorbildModel extends SpringdocModel<VorbildModel> implements NamedWithAbbildungModel, Comparable<VorbildModel> {

    private static final long serialVersionUID = 4657238952018125793L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Unique name, use gattung, bauhreihe or betreibsnummer as appropriate; potentially more than one per gattung...", example = "BR 89.0", required = true)
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

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(description = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(VorbildModel other) {
        return new CompareToBuilder()
            .append(gattung, other.gattung)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(gattung)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VorbildModel)) {
            return false; 
        }

        VorbildModel other = (VorbildModel) obj;

        return new EqualsBuilder()
                .append(gattung, other.gattung)
                .isEquals();
    }

    @Schema(name = ApiNames.VORBILD + "Page")
    public static class PagedVorbildModel extends PagedSchema<VorbildModel>{}
}