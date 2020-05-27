package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.rest.json.Formats;

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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.GATTUNG, ApiNames.BEZEICHNUNG, ApiNames.UNTER_KATEGORIE,
        ApiNames.BAHNVERWALTUNG, ApiNames.HERSTELLER, ApiNames.BAUZEIT, ApiNames.ANZAHL, ApiNames.BETREIBSNUMMER,
        ApiNames.ANTRIEB, ApiNames.ACHSFOLG, ApiNames.ANFAHRZUGKRAFT, ApiNames.LEISTUNG, ApiNames.DIENSTGEWICHT,
        ApiNames.GESCHWINDIGKEIT, ApiNames.LANGE, ApiNames.AUSSERDIENST, ApiNames.DMTREIBRAD, ApiNames.DMLAUFRADVORN,
        ApiNames.DMLAUFRADHINTEN, ApiNames.ZYLINDER, ApiNames.DMZYLINDER, ApiNames.KOLBENHUB, ApiNames.KESSELUBERDRUCK,
        ApiNames.ROSTFLACHE, ApiNames.UBERHITZERFLACHE, ApiNames.WASSERVORRAT, ApiNames.VERDAMPFUNG, ApiNames.STEUERUNG,
        ApiNames.FAHRMOTOREN, ApiNames.MOTORBAUART, ApiNames.LEISTUNGSUBERTRAGUNG, ApiNames.REICHWEITE,
        ApiNames.KAPAZITAT, ApiNames.KLASSE, ApiNames.SITZPLATZEKL1, ApiNames.SITZPLATZEKL2, ApiNames.SITZPLATZEKL3,
        ApiNames.SITZPLATZEKL4, ApiNames.AUFBAU, ApiNames.TRIEBKOPF, ApiNames.MITTELWAGEN, ApiNames.DREHGESTELLBAUART,
        ApiNames.ABBILDUNG, ApiNames.DELETED})
@Schema(name = ApiNames.VORBILD, description = "A real world prototype.")
public class VorbildModel extends RepresentationModel<VorbildModel> implements ItemModel, Comparable<VorbildModel> {

    private static final long serialVersionUID = 4657238952018125793L;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(implementation = GattungModel.class, name = "Rolling stock class", example = "BR 89.0", required = true)
    private String gattung;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(implementation = UnterKategorieModel.class, name = "Category and subcategory", required = true)
    private UnterKategorieModel unterKategorie;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(implementation = BahnverwaltungModel.class, name = "Railway company", required = true)
    private BahnverwaltungModel bahnverwaltung;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(name = "Manunfacturer", example = "Henschel")
    private String hersteller;

    @JsonProperty(ApiNames.BAUZEIT)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.ANZAHL)
    @Schema(name = "Number built", example = "10")
    private Integer Anzahl;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(name = "Service number", example = "89 006")
    private String betreibsNummer;

    @JsonProperty(ApiNames.ANTRIEB)
    @Schema(implementation = AntriebModel.class, name = "Drive method")
    private AntriebModel antrieb;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(implementation = AchsfolgModel.class, name = "Axle configuration")
    private AchsfolgModel achsfolg;

    @JsonProperty(ApiNames.ANFAHRZUGKRAFT)
    @Schema(name = "Tractive Effort in kN", example = "300")
    private BigDecimal anfahrzugkraft;

    @JsonProperty(ApiNames.LEISTUNG)
    @Schema(name = "Power in kW", example = "385")
    private BigDecimal leistung;

    @JsonProperty(ApiNames.DIENSTGEWICHT)
    @Schema(name = "Service weight in metric tons", example = "46")
    private BigDecimal dienstgewicht;

    @JsonProperty(ApiNames.GESCHWINDIGKEIT)
    @Schema(name = "Maximum speed in km/h", example = "45")
    private Integer geschwindigkeit;

    @JsonProperty(ApiNames.LANGE)
    @Schema(name = "Length over puffers in mm", example = "9600")
    private BigDecimal lange;

    @JsonProperty(ApiNames.AUSSERDIENST)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Out of service date", example = "1962")
    private LocalDate ausserdienst;

    @JsonProperty(ApiNames.DMTREIBRAD)
    @Schema(name = "Drive wheel diamerter in mm", example = "1100")
    private BigDecimal dmTreibrad;

    @JsonProperty(ApiNames.DMLAUFRADVORN)
    @Schema(name = "Running wheel diameter front", example = "0")
    private BigDecimal dmLaufradVorn;

    @JsonProperty(ApiNames.DMLAUFRADHINTEN)
    @Schema(name = "Running wheel diameter rear", example = "0")
    private BigDecimal dmLaufradHinten;

    @JsonProperty(ApiNames.ZYLINDER)
    @Schema(name = "Number of cylinders", example = "2")
    private Integer Zylinder;

    @JsonProperty(ApiNames.DMZYLINDER)
    @Schema(name = "Cylinder diameter", example = "500")
    private BigDecimal dmZylinder;

    @JsonProperty(ApiNames.KOLBENHUB)
    @Schema(name = "Cylinder stroke", example = "550")
    private BigDecimal kolbenhub;

    @JsonProperty(ApiNames.KESSELUBERDRUCK)
    @Schema(name = "Boiler pressure in bar", example = "14")
    private BigDecimal kesseluberdruck;

    @JsonProperty(ApiNames.ROSTFLACHE)
    @Schema(name = "Grate area in m²", example = "1.42")
    private BigDecimal rostflache;

    @JsonProperty(ApiNames.UBERHITZERFLACHE)
    @Schema(name = "Super heater area in m²", example = "11.9")
    private BigDecimal uberhitzerflache;

    @JsonProperty(ApiNames.WASSERVORRAT)
    @Schema(name = "Water capactity in m³", example = "5.5")
    private BigDecimal wasservorrat;

    @JsonProperty(ApiNames.VERDAMPFUNG)
    @Schema(name = "Evaporative heater area in m²", example = "118.4")
    private BigDecimal verdampfung;

    @JsonProperty(ApiNames.FAHRMOTOREN)
    @Schema(name = "Number of drive motors", example = "1")
    private Integer fahrmotoren;

    @JsonProperty(ApiNames.MOTORBAUART)
    @Schema(name = "Engine manufacturer and model (IC engines)", example = "Henschel 12V1516A")
    private String motorbauart;

    @JsonProperty(ApiNames.LEISTUNGSUBERTRAGUNG)
    @Schema(name = "Power transfer method (IC engines)", example = "MECHANISH")
    private LeistungsUbertragung leistungsUbertragung;

    @JsonProperty(ApiNames.REICHWEITE)
    @Schema(name = "Range (fueled vehicles) km", example = "500")
    private BigDecimal reichweite;

    @JsonProperty(ApiNames.KAPAZITAT)
    @Schema(name = "Battery capacity in kwH", example = "190")
    private BigDecimal kapazitat;

    @JsonProperty(ApiNames.KLASSE)
    @Schema(name = "Number of classes (passenger wagens / multiple units)", example = "2")
    private Integer klasse;

    @JsonProperty(ApiNames.SITZPLATZEKL1)
    @Schema(name = "First class seating", example = "20")
    private Integer sitzplatzeKL1;

    @JsonProperty(ApiNames.SITZPLATZEKL2)
    @Schema(name = "Second class seating", example = "80")
    private Integer sitzplatzeKL2;

    @JsonProperty(ApiNames.SITZPLATZEKL3)
    @Schema(name = "Third class seating", example = "90")
    private Integer sitzplatzeKL3;

    @JsonProperty(ApiNames.SITZPLATZEKL4)
    @Schema(name = "Fourth class seating", example = "150")
    private Integer sitzplatzeKL4;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(name = "Construction", example = "Holz")
    private String aufbau;

    @JsonProperty(ApiNames.TRIEBKOPF)
    @Schema(name = "Number of drive wagons (multiple units)", example = "2")
    private Integer triebkopf;

    @JsonProperty(ApiNames.MITTELWAGEN)
    @Schema(name = "Number of middle wagons (multiple units)", example = "6")
    private Integer mittelwagen;

    @JsonProperty(ApiNames.DREHGESTELLBAUART)
    @Schema(name = "Bogie Manufacturer and type", example = "Y 25")
    private String drehgestellBauart;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(implementation = String.class, name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private Path abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
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
}
