package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
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
 * ProduktDto.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.PRODUKT)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.LANGE,
        ApiNames.UNTER_KATEGORIE, ApiNames.MASSSTAB, ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG,
        ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER, ApiNames.BAUZEIT, ApiNames.VORBILD, ApiNames.ACHSFOLG,
        ApiNames.ANMERKUNG, ApiNames.SONDERMODELL, ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG,
        ApiNames.STEUERUNG, ApiNames.DECODER_TYP, ApiNames.MOTOR_TYP, ApiNames.LANGE, ApiNames.ANLEITUNGEN,
        ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.TEILEN, ApiNames.DELETED})
@Schema(name = ApiNames.PRODUKT, description = "Product - template for article.")
public class ProduktModel extends RepresentationModel<ProduktModel> implements ItemModel {
    private static final long serialVersionUID = 2584784787206478907L;

    @JsonProperty(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = HerstellerModel.class, name = "Manufacturer", required = true)
    private HerstellerModel hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Part number", example = "3000", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    private UnterKategorieModel unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = MassstabModel.class, name = "Scale")
    private MassstabModel massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = SpurweiteModel.class, name = "Track gauge")
    private SpurweiteModel spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @JsonView(Views.Public.class)
    @Schema(implementation = EpochModel.class, name = "ERA")
    private EpochModel epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @JsonView(Views.Public.class)
    @Schema(implementation = BahnverwaltungModel.class, name = "Railway company")
    private BahnverwaltungModel bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @JsonView(Views.Public.class)
    @Schema(implementation = GattungModel.class, name = "Vehicle class")
    private GattungModel gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.Public.class)
    @Schema(name = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.BAUZEIT)
    @JsonView(Views.Public.class)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.VORBILD)
    @JsonView(Views.Public.class)
    @Schema(implementation = VorbildModel.class, name = "Prototype")
    private VorbildModel vorbild;

    @JsonProperty(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @Schema(implementation = AchsfolgModel.class, name = "Axle configuration")
    private AchsfolgModel achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @JsonView(Views.Public.class)
    @Schema(implementation = SonderModellModel.class, name = "Special model indicator")
    private SonderModellModel sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    @Schema(implementation = AufbauModel.class, name = "Construction")
    private AufbauModel aufbau;

    @JsonProperty(ApiNames.LICHT)
    @JsonView(Views.Public.class)
    @Schema(implementation = LichtModel.class, name = "Light configuration")
    private LichtModel licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @JsonView(Views.Public.class)
    @Schema(implementation = KupplungModel.class, name = "Coupling configuration")
    private KupplungModel kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @Schema(implementation = SteuerungModel.class, name = "Control method")
    private SteuerungModel steuerung;

    @JsonProperty(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = DecoderTypModel.class, name = "Decoder type")
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @JsonView(Views.Public.class)
    @Schema(implementation = MotorTypModel.class, name = "Motor type")
    private MotorTypModel motorTyp;

    @JsonProperty(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Remarks", example = "Ex set")
    private String anmerkung;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @JsonView(Views.Public.class)
    @Schema(implementation = String.class, name = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/betrieb_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private Path anleitungen;

    @JsonProperty(ApiNames.EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @Schema(implementation = String.class, name = "Parts diagram URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/explo_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private Path explosionszeichnung;

    @JsonProperty(ApiNames.TEILEN)
    @JsonView(Views.Public.class)
    @Schema(implementation = ProduktTeilModel.class, name = "Product components", accessMode = AccessMode.READ_ONLY)
    private Set<ProduktTeilModel> teilen;

    @JsonProperty(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = String.class, name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private Path abbildung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
