package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.ItemModelImpl;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ArtikelModel.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ARTIKEL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.ARTIKEL_ID, ApiNames.BEZEICHNUNG, ApiNames.PRODUKT, ApiNames.KAUFDATUM,
        ApiNames.WAHRUNG, ApiNames.PREIS, ApiNames.STUCK, ApiNames.STEUERUNG, ApiNames.MOTOR_TYP, ApiNames.LICHT,
        ApiNames.KUPPLUNG, ApiNames.DECODER, ApiNames.ANMERKUNG, ApiNames.BELADUNG, ApiNames.ABBILDUNG, ApiNames.STATUS,
        ApiNames.DELETED})
@ApiModel(value = ApiNames.ARTIKEL, description = "An article - may differ from product because of modificiations")
public class ArtikelModel extends ItemModelImpl<ArtikelModel> {

    private static final long serialVersionUID = 3146760791932382500L;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel id", example = "00001", required = true)
    private String artikelId;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel description", example = "BR 89.0 Dampftenderlokomotive")
    private String bezeichnung;

    @JsonProperty(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Manufacturer", example = "Marklin", required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Product id", example = "3000", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.KAUFDATUM)
    @JsonView(Views.Public.class)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    @ApiModelProperty(dataType = "java.time.LocalDate", value = "Purchase date", example = "1967-08-10")
    private LocalDate kaufdatum;

    @JsonProperty(ApiNames.WAHRUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Purchase currency", example = "EUR")
    private String wahrung;

    @JsonProperty(ApiNames.PREIS)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Purchase price", example = "115.95")
    private BigDecimal preis;

    @JsonProperty(ApiNames.STUCK)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Purchase Quantity", example = "1", required = true)
    private Integer stuck;

    @JsonProperty(ApiNames.VERBLEIBENDE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Remaining Quantity", example = "1", required = true)
    private Integer verbleibende;

    @JsonProperty(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Control method", example = "")
    private String steuerung;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Motor type", example = "")
    private MotorTypModel motorTyp;

    @JsonProperty(ApiNames.LICHT)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.LichtModel", value = "Light Configuration", example = "")
    private LichtModel licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.KupplungModel", value = "Coupling configuration", example = "")
    private KupplungModel kupplung;

    @JsonProperty(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderModel", value = "Decoder", example = "")
    private String decoder;

    @JsonProperty(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Remarks", example = "5* Motor and decoder")
    private String anmerkung;

    @JsonProperty(ApiNames.BELADUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Wagon load", example = "holz")
    private String beladung;

    @JsonProperty(ApiNames.STATUS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Status", example = "GEKAUFT", required = true)
    private Status status;

    @JsonProperty(ApiNames.ANDERUNGEN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.IAnderungRef;", value = "Modifications", access = "READ_ONLY")
    private Set<AnderungModel> anderungen;

    @JsonProperty(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "String", value = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", access = "READ_ONLY")
    private Path abbildung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
