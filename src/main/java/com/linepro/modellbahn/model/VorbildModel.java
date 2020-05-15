package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;

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
@JsonIgnoreProperties(ignoreUnknown = true)
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
public class VorbildModel extends RepresentationModel<VorbildModel> implements ItemModel {

    private static final long serialVersionUID = 4657238952018125793L;

    @JsonProperty(ApiNames.GATTUNG)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = GattungModel.class, name = "Rolling stock class", example = "BR 89.0", required = true)
    private String gattung;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = UnterKategorieModel.class, name = "Category and subcategory", required = true)
    private UnterKategorieModel unterKategorie;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = BahnverwaltungModel.class, name = "Railway company", required = true)
    private BahnverwaltungModel bahnverwaltung;

    @JsonProperty(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Manunfacturer", example = "Henschel")
    private String hersteller;

    @JsonProperty(ApiNames.BAUZEIT)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.ANZAHL)
    @JsonView(Views.Public.class)
    @Schema(name = "Number built", example = "10")
    private Integer Anzahl;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Service number", example = "89 006")
    private String betreibsNummer;

    @JsonProperty(ApiNames.ANTRIEB)
    @JsonView(Views.Public.class)
    @Schema(implementation = AntriebModel.class, name = "Drive method")
    private AntriebModel antrieb;

    @JsonProperty(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @Schema(implementation = AchsfolgModel.class, name = "Axle configuration")
    private AchsfolgModel achsfolg;

    @JsonProperty(ApiNames.ANFAHRZUGKRAFT)
    @JsonView(Views.Public.class)
    @Schema(name = "Tractive Effort in kN", example = "300")
    private BigDecimal anfahrzugkraft;

    @JsonProperty(ApiNames.LEISTUNG)
    @JsonView(Views.Public.class)
    @Schema(name = "Power in kW", example = "385")
    private BigDecimal leistung;

    @JsonProperty(ApiNames.DIENSTGEWICHT)
    @JsonView(Views.Public.class)
    @Schema(name = "Service weight in metric tons", example = "46")
    private BigDecimal dienstgewicht;

    @JsonProperty(ApiNames.GESCHWINDIGKEIT)
    @JsonView(Views.Public.class)
    @Schema(name = "Maximum speed in km/h", example = "45")
    private Integer geschwindigkeit;

    @JsonProperty(ApiNames.LANGE)
    @JsonView(Views.Public.class)
    @Schema(name = "Length over puffers in mm", example = "9600")
    private BigDecimal lange;

    @JsonProperty(ApiNames.AUSSERDIENST)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Out of service date", example = "1962")
    private LocalDate ausserdienst;

    @JsonProperty(ApiNames.DMTREIBRAD)
    @JsonView(Views.Public.class)
    @Schema(name = "Drive wheel diamerter in mm", example = "1100")
    private BigDecimal dmTreibrad;

    @JsonProperty(ApiNames.DMLAUFRADVORN)
    @JsonView(Views.Public.class)
    @Schema(name = "Running wheel diameter front", example = "0")
    private BigDecimal dmLaufradVorn;

    @JsonProperty(ApiNames.DMLAUFRADHINTEN)
    @JsonView(Views.Public.class)
    @Schema(name = "Running wheel diameter rear", example = "0")
    private BigDecimal dmLaufradHinten;

    @JsonProperty(ApiNames.ZYLINDER)
    @JsonView(Views.Public.class)
    @Schema(name = "Number of cylinders", example = "2")
    private Integer Zylinder;

    @JsonProperty(ApiNames.DMZYLINDER)
    @JsonView(Views.Public.class)
    @Schema(name = "Cylinder diameter", example = "500")
    private BigDecimal dmZylinder;

    @JsonProperty(ApiNames.KOLBENHUB)
    @JsonView(Views.Public.class)
    @Schema(name = "Cylinder stroke", example = "550")
    private BigDecimal kolbenhub;

    @JsonProperty(ApiNames.KESSELUBERDRUCK)
    @JsonView(Views.Public.class)
    @Schema(name = "Boiler pressure in bar", example = "14")
    private BigDecimal kesseluberdruck;

    @JsonProperty(ApiNames.ROSTFLACHE)
    @JsonView(Views.Public.class)
    @Schema(name = "Grate area in m²", example = "1.42")
    private BigDecimal rostflache;

    @JsonProperty(ApiNames.UBERHITZERFLACHE)
    @JsonView(Views.Public.class)
    @Schema(name = "Super heater area in m²", example = "11.9")
    private BigDecimal uberhitzerflache;

    @JsonProperty(ApiNames.WASSERVORRAT)
    @JsonView(Views.Public.class)
    @Schema(name = "Water capactity in m³", example = "5.5")
    private BigDecimal wasservorrat;

    @JsonProperty(ApiNames.VERDAMPFUNG)
    @JsonView(Views.Public.class)
    @Schema(name = "Evaporative heater area in m²", example = "118.4")
    private BigDecimal verdampfung;

    @JsonProperty(ApiNames.FAHRMOTOREN)
    @JsonView(Views.Public.class)
    @Schema(name = "Number of drive motors", example = "1")
    private Integer fahrmotoren;

    @JsonProperty(ApiNames.MOTORBAUART)
    @JsonView(Views.Public.class)
    @Schema(name = "Engine manufacturer and model (IC engines)", example = "Henschel 12V1516A")
    private String motorbauart;

    @JsonProperty(ApiNames.LEISTUNGSUBERTRAGUNG)
    @JsonView(Views.Public.class)
    @Schema(name = "Power transfer method (IC engines)", example = "MECHANISH")
    private LeistungsUbertragung leistungsUbertragung;

    @JsonProperty(ApiNames.REICHWEITE)
    @JsonView(Views.Public.class)
    @Schema(name = "Range (fueled vehicles) km", example = "500")
    private BigDecimal reichweite;

    @JsonProperty(ApiNames.KAPAZITAT)
    @JsonView(Views.Public.class)
    @Schema(name = "Battery capacity in kwH", example = "190")
    private BigDecimal kapazitat;

    @JsonProperty(ApiNames.KLASSE)
    @JsonView(Views.Public.class)
    @Schema(name = "Number of classes (passenger wagens / multiple units)", example = "2")
    private Integer klasse;

    @JsonProperty(ApiNames.SITZPLATZEKL1)
    @JsonView(Views.Public.class)
    @Schema(name = "First class seating", example = "20")
    private Integer sitzPlatzeKL1;

    @JsonProperty(ApiNames.SITZPLATZEKL2)
    @JsonView(Views.Public.class)
    @Schema(name = "Second class seating", example = "80")
    private Integer sitzPlatzeKL2;

    @JsonProperty(ApiNames.SITZPLATZEKL3)
    @JsonView(Views.Public.class)
    @Schema(name = "Third class seating", example = "90")
    private Integer sitzPlatzeKL3;

    @JsonProperty(ApiNames.SITZPLATZEKL4)
    @JsonView(Views.Public.class)
    @Schema(name = "Fourth class seating", example = "150")
    private Integer sitzPlatzeKL4;

    @JsonProperty(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    @Schema(name = "Construction", example = "Holz")
    private String aufbau;

    @JsonProperty(ApiNames.TRIEBKOPF)
    @JsonView(Views.Public.class)
    @Schema(name = "Number of drive wagons (multiple units)", example = "2")
    private Integer triebkopf;

    @JsonProperty(ApiNames.MITTELWAGEN)
    @JsonView(Views.Public.class)
    @Schema(name = "Number of middle wagons (multiple units)", example = "6")
    private Integer mittelwagen;

    @JsonProperty(ApiNames.DREHGESTELLBAUART)
    @JsonView(Views.Public.class)
    @Schema(name = "Bogie Manufacturer and type", example = "Y 25")
    private String drehgestellBauart;

    @JsonProperty(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = String.class, name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private Path abbildung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}
