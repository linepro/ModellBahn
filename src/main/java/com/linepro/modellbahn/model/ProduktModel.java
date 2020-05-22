package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.LANGE,
        ApiNames.UNTER_KATEGORIE, ApiNames.MASSSTAB, ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG,
        ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER, ApiNames.BAUZEIT, ApiNames.VORBILD, ApiNames.ACHSFOLG,
        ApiNames.ANMERKUNG, ApiNames.SONDERMODELL, ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG,
        ApiNames.STEUERUNG, ApiNames.DECODER_TYP, ApiNames.MOTOR_TYP, ApiNames.LANGE, ApiNames.ANLEITUNGEN,
        ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.TEILEN, ApiNames.DELETED})
@Schema(name = ApiNames.PRODUKT, description = "Product - template for article.")
public class ProduktModel extends RepresentationModel<ProduktModel> implements ItemModel, Comparable<ProduktModel> {
    private static final long serialVersionUID = 2584784787206478907L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(implementation = HerstellerModel.class, name = "Manufacturer", required = true)
    private HerstellerModel hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(name = "Part number", example = "3000", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    private UnterKategorieModel unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(name = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(implementation = MassstabModel.class, name = "Scale")
    private MassstabModel massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(implementation = SpurweiteModel.class, name = "Track gauge")
    private SpurweiteModel spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(implementation = EpochModel.class, name = "ERA")
    private EpochModel epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(implementation = BahnverwaltungModel.class, name = "Railway company")
    private BahnverwaltungModel bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(implementation = GattungModel.class, name = "Vehicle class")
    private GattungModel gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(name = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.BAUZEIT)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @Schema(implementation = LocalDate.class, name = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.VORBILD)
    @Schema(implementation = VorbildModel.class, name = "Prototype")
    private VorbildModel vorbild;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(implementation = AchsfolgModel.class, name = "Axle configuration")
    private AchsfolgModel achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(implementation = SondermodellModel.class, name = "Special model indicator")
    private SondermodellModel sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(implementation = AufbauModel.class, name = "Construction")
    private AufbauModel aufbau;

    @JsonProperty(ApiNames.LICHT)
    @Schema(implementation = LichtModel.class, name = "Light configuration")
    private LichtModel licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(implementation = KupplungModel.class, name = "Coupling configuration")
    private KupplungModel kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(implementation = SteuerungModel.class, name = "Control method")
    private SteuerungModel steuerung;

    @JsonProperty(ApiNames.DECODER_TYP)
    @Schema(implementation = DecoderTypModel.class, name = "Decoder type")
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(implementation = MotorTypModel.class, name = "Motor type")
    private MotorTypModel motorTyp;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(name = "Remarks", example = "Ex set")
    private String anmerkung;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(implementation = String.class, name = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/betrieb_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private Path anleitungen;

    @JsonProperty(ApiNames.EXPLOSIONSZEICHNUNG)
    @Schema(implementation = String.class, name = "Parts diagram URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/explo_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private Path explosionszeichnung;

    @JsonProperty(ApiNames.TEILEN)
    @Schema(implementation = ProduktTeilModel.class, name = "Product components", accessMode = AccessMode.READ_ONLY)
    private List<ProduktTeilModel> teilen;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(implementation = String.class, name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private Path abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(ProduktModel other) {
        return new CompareToBuilder()
            .append(bestellNr, other.bestellNr)
            .append(hersteller, other.hersteller)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(hersteller)
            .append(bestellNr)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProduktModel)) {
            return false; 
        }

        ProduktModel other = (ProduktModel) obj;
        
        return new EqualsBuilder()
                .append(bestellNr, other.bestellNr)
                .append(hersteller, other.hersteller)
                .isEquals();
    }
}
