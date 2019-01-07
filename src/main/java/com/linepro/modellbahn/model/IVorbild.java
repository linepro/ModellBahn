package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.keys.VorbildKey;
import com.linepro.modellbahn.model.refs.INamedItemRef;
import com.linepro.modellbahn.model.refs.IVorbildRef;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.LocalDateDeserializer;
import com.linepro.modellbahn.rest.json.serialization.LocalDateSerializer;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * IVorbild.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.VORBILD)
@JsonPropertyOrder({ApiNames.ID, ApiNames.GATTUNG, ApiNames.UNTER_KATEGORIE, ApiNames.BAHNVERWALTUNG, ApiNames.HERSTELLER, ApiNames.BAUZEIT, ApiNames.ANZAHL, ApiNames.BETREIBSNUMMER, ApiNames.ANTRIEB, ApiNames.ACHSFOLG, ApiNames.ANFAHRZUGKRAFT, ApiNames.LEISTUNG, ApiNames.DIENSTGEWICHT, ApiNames.GESCHWINDIGKEIT, ApiNames.LANGE, ApiNames.AUSSERDIENST, ApiNames.DMTREIBRAD, ApiNames.DMLAUFRADVORN, ApiNames.DMLAUFRADHINTEN, ApiNames.ZYLINDER, ApiNames.DMZYLINDER, ApiNames.KOLBENHUB, ApiNames.KESSELUEBERDRUCK, ApiNames.ROSTFLAECHE, ApiNames.UEBERHITZERFLAECHE, ApiNames.WASSERVORRAT, ApiNames.VERDAMPFUNG, ApiNames.STEUERUNG, ApiNames.FAHRMOTOREN, ApiNames.MOTORBAUART, ApiNames.LEISTUNGSUEBERTRAGUNG, ApiNames.REICHWEITE, ApiNames.KAPAZITAT, ApiNames.KLASSE, ApiNames.SITZPLATZEKL1, ApiNames.SITZPLATZEKL2, ApiNames.SITZPLATZEKL3, ApiNames.SITZPLATZEKL4, ApiNames.AUFBAU, ApiNames.TRIEBZUGANZEIGEN, ApiNames.TRIEBKOEPFE, ApiNames.MITTELWAGEN, ApiNames.SitzplatzeTzKL1, ApiNames.SitzplatzeTzKL2, ApiNames.DREHGESTELLBAUART, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.VORBILD, description = "Prototype.")
public interface IVorbild extends IItem<VorbildKey>, IVorbildRef {
    
    @JsonSetter(ApiNames.GATTUNG)
    @JsonDeserialize(as= Gattung.class)
    @ApiModelProperty(value = "", required = true)
    void setGattung(IGattung gattung);

    @JsonSetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "")
    void setBezeichnung(String bezeichnung);
    
    @JsonGetter(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as = IUnterKategorie.class)
    @ApiModelProperty(value = "", required = true)
    IUnterKategorie getUnterKategorie();
    
    @JsonSetter(ApiNames.UNTER_KATEGORIE)
    @JsonDeserialize(as=UnterKategorie.class)
    void setUnterKategorie(IUnterKategorie unterKategorie);

    @JsonGetter(ApiNames.BAHNVERWALTUNG)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(value = "", required = true)
    IBahnverwaltung getBahnverwaltung();

    @JsonSetter(ApiNames.BAHNVERWALTUNG)
    @JsonDeserialize(as=Bahnverwaltung.class)
    void setBahnverwaltung(IBahnverwaltung bahnverwaltung);
    
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "")
    String getHersteller();
    
    @JsonSetter(ApiNames.HERSTELLER)
    void setHersteller(String hersteller);

    @JsonGetter(ApiNames.BAUZEIT)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape=Shape.STRING, pattern= Formats.ISO8601_DATE)
    @ApiModelProperty(dataType = "java.time.LocalDate", value = "")
    LocalDate getBauzeit();
    
    @JsonSetter(ApiNames.BAUZEIT)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    void setBauzeit(LocalDate bauzeit);
    
    @JsonGetter(ApiNames.ANZAHL)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getAnzahl();
    
    @JsonSetter(ApiNames.ANZAHL)
    void setAnzahl(Integer anzahl);
    
    @JsonGetter(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "")
    String getBetreibsNummer();
    
    @JsonSetter(ApiNames.BETREIBSNUMMER)
    void setBetreibsNummer(String betreibsNummer);
    
    @JsonGetter(ApiNames.ANTRIEB)
    @JsonView(Views.Public.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(dataType = "String", value = "", required = true)
    IAntrieb getAntrieb();
    
    @JsonSetter(ApiNames.ANTRIEB)
    @JsonDeserialize(as=Antrieb.class)
    void setAntrieb(IAntrieb antrieb);
    
    @JsonGetter(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(value = "", required = true)
    IAchsfolg getAchsfolg();
    
    @JsonSetter(ApiNames.ACHSFOLG)
    @JsonDeserialize(as=Achsfolg.class)
    void setAchsfolg(IAchsfolg achsfolg);
    
    @JsonGetter(ApiNames.ANFAHRZUGKRAFT)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getAnfahrzugkraft();
    
    @JsonSetter(ApiNames.ANFAHRZUGKRAFT)
    void setAnfahrzugkraft(BigDecimal anfahrzugkraft);
    
    @JsonGetter(ApiNames.LEISTUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getLeistung();
    
    @JsonSetter(ApiNames.LEISTUNG)
    void setLeistung(BigDecimal leistung);

    @JsonGetter(ApiNames.DIENSTGEWICHT)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getDienstgewicht();

    @JsonSetter(ApiNames.DIENSTGEWICHT)
    void setDienstgewicht(BigDecimal dienstgewicht);

    @JsonGetter(ApiNames.GESCHWINDIGKEIT)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getGeschwindigkeit();

    @JsonSetter(ApiNames.GESCHWINDIGKEIT)
    void setGeschwindigkeit(Integer geschwindigkeit);

    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getLange();

    @JsonSetter(ApiNames.LANGE)
    void setLange(BigDecimal lange);

    @JsonGetter(ApiNames.AUSSERDIENST)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape=Shape.STRING, pattern=Formats.ISO8601_DATE)
    @ApiModelProperty(dataType = "java.time.LocalDate", value = "")
    LocalDate getAusserdienst();

    @JsonSetter(ApiNames.AUSSERDIENST)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    void setAusserdienst(LocalDate ausserdienst);

    @JsonGetter(ApiNames.DMTREIBRAD)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getDmTreibrad();

    @JsonSetter(ApiNames.DMTREIBRAD)
    void setDmTreibrad(BigDecimal dmTreibrad);

    @JsonGetter(ApiNames.DMLAUFRADVORN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getDmLaufradVorn();

    @JsonSetter(ApiNames.DMLAUFRADVORN)
    void setDmLaufradVorn(BigDecimal dmLaufradVorn);

    @JsonGetter(ApiNames.DMLAUFRADHINTEN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getDmLaufradHinten();

    @JsonSetter(ApiNames.DMLAUFRADHINTEN)
    void setDmLaufradHinten(BigDecimal dmLaufradHinten);

    @JsonGetter(ApiNames.ZYLINDER)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getZylinder();

    @JsonSetter(ApiNames.ZYLINDER)
    void setZylinder(Integer zylinder);

    @JsonGetter(ApiNames.DMZYLINDER)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getDmZylinder();

    @JsonSetter(ApiNames.DMZYLINDER)
    void setDmZylinder(BigDecimal dmZylinder);

    @JsonGetter(ApiNames.KOLBENHUB)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getKolbenhub();

    @JsonSetter(ApiNames.KOLBENHUB)
    void setKolbenhub(BigDecimal kolbenhub);

    @JsonGetter(ApiNames.KESSELUEBERDRUCK)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getKesselueberdruck();

    @JsonSetter(ApiNames.KESSELUEBERDRUCK)
    void setKesselueberdruck(BigDecimal kesselueberdruck);

    @JsonGetter(ApiNames.ROSTFLAECHE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getRostflaeche();

    @JsonSetter(ApiNames.ROSTFLAECHE)
    void setRostflaeche(BigDecimal rostflaeche);

    @JsonGetter(ApiNames.UEBERHITZERFLAECHE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getUeberhitzerflaeche();

    @JsonSetter(ApiNames.UEBERHITZERFLAECHE)
    void setUeberhitzerflaeche(BigDecimal ueberhitzerflaeche);

    @JsonGetter(ApiNames.WASSERVORRAT)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getWasservorrat();

    @JsonSetter(ApiNames.WASSERVORRAT)
    void setWasservorrat(BigDecimal wasservorrat);

    @JsonGetter(ApiNames.VERDAMPFUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getVerdampfung();

    @JsonSetter(ApiNames.VERDAMPFUNG)
    void setVerdampfung(BigDecimal verdampfung);

    @JsonGetter(ApiNames.FAHRMOTOREN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getFahrmotoren();

    @JsonSetter(ApiNames.FAHRMOTOREN)
    void setFahrmotoren(Integer fahrmotoren);

    @JsonGetter(ApiNames.MOTORBAUART)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    String getMotorbauart();

    @JsonSetter(ApiNames.MOTORBAUART)
    void setMotorbauart(String motorbauart);

    @JsonGetter(ApiNames.LEISTUNGSUEBERTRAGUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getLeistungsuebertragung();

    @JsonSetter(ApiNames.LEISTUNGSUEBERTRAGUNG)
    void setLeistungsuebertragung(BigDecimal leistungsuebertragung);

    @JsonGetter(ApiNames.REICHWEITE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getReichweite();

    @JsonSetter(ApiNames.REICHWEITE)
    void setReichweite(BigDecimal reichweite);

    @JsonGetter(ApiNames.KAPAZITAT)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    BigDecimal getKapazitat();

    @JsonSetter(ApiNames.KAPAZITAT)
    void setKapazitat(BigDecimal kapazitat);

    @JsonGetter(ApiNames.KLASSE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getKlasse();

    @JsonSetter(ApiNames.KLASSE)
    void setKlasse(Integer klasse);

    @JsonGetter(ApiNames.SITZPLATZEKL1)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getSitzPlatzeKL1();

    @JsonSetter(ApiNames.SITZPLATZEKL1)
    void setSitzPlatzeKL1(Integer sitzPlatzeKL1);

    @JsonGetter(ApiNames.SITZPLATZEKL2)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getSitzPlatzeKL2();

    @JsonSetter(ApiNames.SITZPLATZEKL2)
    void setSitzPlatzeKL2(Integer sitzPlatzeKL2);

    @JsonGetter(ApiNames.SITZPLATZEKL3)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getSitzPlatzeKL3();

    @JsonSetter(ApiNames.SITZPLATZEKL3)
    void setSitzPlatzeKL3(Integer sitzPlatzeKL3);

    @JsonGetter(ApiNames.SITZPLATZEKL4)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getSitzPlatzeKL4();

    @JsonSetter(ApiNames.SITZPLATZEKL4)
    void setSitzPlatzeKL4(Integer sitzPlatzeKL4);

    @JsonGetter(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    String getAufbau();

    @JsonSetter(ApiNames.AUFBAU)
    void setAufbau(String aufbau);

    @JsonGetter(ApiNames.TRIEBZUGANZEIGEN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Boolean getTriebzugAnzeigen();

    @JsonSetter(ApiNames.TRIEBZUGANZEIGEN)
    void setTriebzugAnzeigen(Boolean triebzugAnzeigen);

    @JsonGetter(ApiNames.TRIEBKOEPFE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getTriebkoepfe();

    @JsonSetter(ApiNames.TRIEBKOEPFE)
    void setTriebkoepfe(Integer triebkoepfe);

    @JsonGetter(ApiNames.MITTELWAGEN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getMittelwagen();

    @JsonSetter(ApiNames.MITTELWAGEN)
    void setMittelwagen(Integer mittelwagen);

    @JsonGetter(ApiNames.SitzplatzeTzKL1)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getSitzplatzeTzKL1();

    @JsonSetter(ApiNames.SitzplatzeTzKL1)
    void setSitzplatzeTzKL1(Integer sitzplatzeTzKL1);

    @JsonGetter(ApiNames.SitzplatzeTzKL2)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    Integer getSitzplatzeTzKL2();

    @JsonSetter(ApiNames.SitzplatzeTzKL2)
    void setSitzplatzeTzKL2(Integer sitzplatzeTzKL2);

    @JsonGetter(ApiNames.DREHGESTELLBAUART)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    String getDrehgestellBauart();

    @JsonSetter(ApiNames.DREHGESTELLBAUART)
    void setDrehgestellBauart(String drehgestellbauart);

    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.Public.class)
    @JsonSerialize(using = PathSerializer.class)
    @ApiModelProperty(dataType = "String", value = "Image URL", accessMode = AccessMode.READ_ONLY)
    Path getAbbildung();

    @JsonIgnore
    void setAbbildung(Path abbildung);
}