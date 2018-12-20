package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.AchsfolgResolver;
import com.linepro.modellbahn.rest.json.resolver.AntriebResolver;
import com.linepro.modellbahn.rest.json.resolver.BahnverwaltungResolver;
import com.linepro.modellbahn.rest.json.resolver.GattungResolver;
import com.linepro.modellbahn.rest.json.serialization.IUnterKategorieRef;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IVorbild.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.VORBILD)
@JsonPropertyOrder({ApiNames.ID, ApiNames.GATTUNG, ApiNames.UNTER_KATEGORIE, ApiNames.BAHNVERWALTUNG, ApiNames.HERSTELLER, ApiNames.BAUZEIT, ApiNames.ANZAHL, ApiNames.BETREIBSNUMMER, ApiNames.ANTRIEB, ApiNames.ACHSFOLG, ApiNames.ANFAHRZUGKRAFT, ApiNames.LEISTUNG, ApiNames.DIENSTGEWICHT, ApiNames.GESCHWINDIGKEIT, ApiNames.LANGE, ApiNames.AUSSERDIENST, ApiNames.DMTREIBRAD, ApiNames.DMLAUFRADVORN, ApiNames.DMLAUFRADHINTEN, ApiNames.ZYLINDER, ApiNames.DMZYLINDER, ApiNames.KOLBENHUB, ApiNames.KESSELUEBERDRUCK, ApiNames.ROSTFLAECHE, ApiNames.UEBERHITZERFLAECHE, ApiNames.WASSERVORRAT, ApiNames.VERDAMPFUNG, ApiNames.STEUERUNG, ApiNames.FAHRMOTOREN, ApiNames.MOTORBAUART, ApiNames.LEISTUNGSUEBERTRAGUNG, ApiNames.REICHWEITE, ApiNames.KAPAZITAT, ApiNames.KLASSE, ApiNames.SITZPLATZEKL1, ApiNames.SITZPLATZEKL2, ApiNames.SITZPLATZEKL3, ApiNames.SITZPLATZEKL4, ApiNames.AUFBAU, ApiNames.TRIEBZUGANZEIGEN, ApiNames.TRIEBKOEPFE, ApiNames.MITTELWAGEN, ApiNames.SITZPLATZETZKL1, ApiNames.SITZPLATZETZKL2, ApiNames.DREHGESTELLBAUART, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS})
public interface IVorbild extends INamedItem<NameKey> {
    
    @JsonGetter(ApiNames.GATTUNG)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= GattungResolver.class)
    IGattung getGattung();
    
    @JsonSetter(ApiNames.GATTUNG)
    @JsonDeserialize(as= Gattung.class)
    void setGattung(IGattung gattung);
    
    @JsonGetter(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IUnterKategorieRef.class, using= UnterKategorieSerializer.class)
    IUnterKategorie getUnterKategorie();
    
    @JsonSetter(ApiNames.UNTER_KATEGORIE)
    @JsonDeserialize(as=UnterKategorie.class)
    void setUnterKategorie(IUnterKategorie unterKategorie);

    @JsonGetter(ApiNames.BAHNVERWALTUNG)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= BahnverwaltungResolver.class)
    IBahnverwaltung getBahnverwaltung();

    @JsonSetter(ApiNames.BAHNVERWALTUNG)
    @JsonDeserialize(as=Bahnverwaltung.class)
    void setBahnverwaltung(IBahnverwaltung bahnverwaltung);
    
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    String getHersteller();
    
    @JsonSetter(ApiNames.HERSTELLER)
    void setHersteller(String hersteller);

    @JsonGetter(ApiNames.BAUZEIT)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape=Shape.STRING, pattern= Formats.ISO8601_DATE)
    Date getBauzeit();
    
    @JsonSetter(ApiNames.BAUZEIT)
    void setBauzeit(Date bauzeit);
    
    @JsonGetter(ApiNames.ANZAHL)
    @JsonView(Views.Public.class)
    Integer getAnzahl();
    
    @JsonSetter(ApiNames.ANZAHL)
    void setAnzahl(Integer anzahl);
    
    @JsonGetter(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.DropDown.class)
    String getBetreibsNummer();
    
    @JsonSetter(ApiNames.BETREIBSNUMMER)
    void setBetreibsNummer(String betreibsNummer);
    
    @JsonGetter(ApiNames.ANTRIEB)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= AntriebResolver.class)
    IAntrieb getAntrieb();
    
    @JsonSetter(ApiNames.ANTRIEB)
    @JsonDeserialize(as=Antrieb.class)
    void setAntrieb(IAntrieb antrieb);
    
    @JsonGetter(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= AchsfolgResolver.class)
    IAchsfolg getAchsfolg();
    
    @JsonSetter(ApiNames.ACHSFOLG)
    @JsonDeserialize(as=Achsfolg.class)
    void setAchsfolg(IAchsfolg achsfolg);
    
    @JsonGetter(ApiNames.ANFAHRZUGKRAFT)
    @JsonView(Views.Public.class)
    BigDecimal getAnfahrzugkraft();
    
    @JsonSetter(ApiNames.ANFAHRZUGKRAFT)
    void setAnfahrzugkraft(BigDecimal anfahrzugkraft);
    
    @JsonGetter(ApiNames.LEISTUNG)
    @JsonView(Views.Public.class)
    BigDecimal getLeistung();
    
    @JsonSetter(ApiNames.LEISTUNG)
    void setLeistung(BigDecimal leistung);

    @JsonGetter(ApiNames.DIENSTGEWICHT)
    @JsonView(Views.Public.class)
    BigDecimal getDienstgewicht();

    @JsonSetter(ApiNames.DIENSTGEWICHT)
    void setDienstgewicht(BigDecimal dienstgewicht);

    @JsonGetter(ApiNames.GESCHWINDIGKEIT)
    @JsonView(Views.Public.class)
    Integer getGeschwindigkeit();

    @JsonSetter(ApiNames.GESCHWINDIGKEIT)
    void setGeschwindigkeit(Integer geschwindigkeit);

    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.Public.class)
    BigDecimal getLange();

    @JsonSetter(ApiNames.LANGE)
    void setLange(BigDecimal lange);

    @JsonGetter(ApiNames.AUSSERDIENST)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape=Shape.STRING, pattern=Formats.ISO8601_DATE)
    Date getAusserdienst();

    @JsonSetter(ApiNames.AUSSERDIENST)
    void setAusserdienst(Date ausserdienst);

    @JsonGetter(ApiNames.DMTREIBRAD)
    @JsonView(Views.Public.class)
    BigDecimal getDmTreibrad();

    @JsonSetter(ApiNames.DMTREIBRAD)
    void setDmTreibrad(BigDecimal dmTreibrad);

    @JsonGetter(ApiNames.DMLAUFRADVORN)
    @JsonView(Views.Public.class)
    BigDecimal getDmLaufradVorn();

    @JsonSetter(ApiNames.DMLAUFRADVORN)
    void setDmLaufradVorn(BigDecimal dmLaufradVorn);

    @JsonGetter(ApiNames.DMLAUFRADHINTEN)
    @JsonView(Views.Public.class)
    BigDecimal getDmLaufradHinten();

    @JsonSetter(ApiNames.DMLAUFRADHINTEN)
    void setDmLaufradHinten(BigDecimal dmLaufradHinten);

    @JsonGetter(ApiNames.ZYLINDER)
    @JsonView(Views.Public.class)
    Integer getZylinder();

    @JsonSetter(ApiNames.ZYLINDER)
    void setZylinder(Integer zylinder);

    @JsonGetter(ApiNames.DMZYLINDER)
    @JsonView(Views.Public.class)
    BigDecimal getDmZylinder();

    @JsonSetter(ApiNames.DMZYLINDER)
    void setDmZylinder(BigDecimal dmZylinder);

    @JsonGetter(ApiNames.KOLBENHUB)
    @JsonView(Views.Public.class)
    BigDecimal getKolbenhub();

    @JsonSetter(ApiNames.KOLBENHUB)
    void setKolbenhub(BigDecimal kolbenhub);

    @JsonGetter(ApiNames.KESSELUEBERDRUCK)
    @JsonView(Views.Public.class)
    BigDecimal getKesselueberdruck();

    @JsonSetter(ApiNames.KESSELUEBERDRUCK)
    void setKesselueberdruck(BigDecimal kesselueberdruck);

    @JsonGetter(ApiNames.ROSTFLAECHE)
    @JsonView(Views.Public.class)
    BigDecimal getRostflaeche();

    @JsonSetter(ApiNames.ROSTFLAECHE)
    void setRostflaeche(BigDecimal rostflaeche);

    @JsonGetter(ApiNames.UEBERHITZERFLAECHE)
    @JsonView(Views.Public.class)
    BigDecimal getUeberhitzerflaeche();

    @JsonSetter(ApiNames.UEBERHITZERFLAECHE)
    void setUeberhitzerflaeche(BigDecimal ueberhitzerflaeche);

    @JsonGetter(ApiNames.WASSERVORRAT)
    @JsonView(Views.Public.class)
    BigDecimal getWasservorrat();

    @JsonSetter(ApiNames.WASSERVORRAT)
    void setWasservorrat(BigDecimal wasservorrat);

    @JsonGetter(ApiNames.VERDAMPFUNG)
    @JsonView(Views.Public.class)
    BigDecimal getVerdampfung();

    @JsonSetter(ApiNames.VERDAMPFUNG)
    void setVerdampfung(BigDecimal verdampfung);

    @JsonGetter(ApiNames.FAHRMOTOREN)
    @JsonView(Views.Public.class)
    Integer getFahrmotoren();

    @JsonSetter(ApiNames.FAHRMOTOREN)
    void setFahrmotoren(Integer fahrmotoren);

    @JsonGetter(ApiNames.MOTORBAUART)
    @JsonView(Views.Public.class)
    String getMotorbauart();

    @JsonSetter(ApiNames.MOTORBAUART)
    void setMotorbauart(String motorbauart);

    @JsonGetter(ApiNames.LEISTUNGSUEBERTRAGUNG)
    @JsonView(Views.Public.class)
    BigDecimal getLeistungsuebertragung();

    @JsonSetter(ApiNames.LEISTUNGSUEBERTRAGUNG)
    void setLeistungsuebertragung(BigDecimal leistungsuebertragung);

    @JsonGetter(ApiNames.REICHWEITE)
    @JsonView(Views.Public.class)
    BigDecimal getReichweite();

    @JsonSetter(ApiNames.REICHWEITE)
    void setReichweite(BigDecimal reichweite);

    @JsonGetter(ApiNames.KAPAZITAT)
    @JsonView(Views.Public.class)
    BigDecimal getKapazitat();

    @JsonSetter(ApiNames.KAPAZITAT)
    void setKapazitat(BigDecimal kapazitat);

    @JsonGetter(ApiNames.KLASSE)
    @JsonView(Views.Public.class)
    Integer getKlasse();

    @JsonSetter(ApiNames.KLASSE)
    void setKlasse(Integer klasse);

    @JsonGetter(ApiNames.SITZPLATZEKL1)
    @JsonView(Views.Public.class)
    Integer getSitzPlatzeKL1();

    @JsonSetter(ApiNames.SITZPLATZEKL1)
    void setSitzPlatzeKL1(Integer sitzPlatzeKL1);

    @JsonGetter(ApiNames.SITZPLATZEKL2)
    @JsonView(Views.Public.class)
    Integer getSitzPlatzeKL2();

    @JsonSetter(ApiNames.SITZPLATZEKL2)
    void setSitzPlatzeKL2(Integer sitzPlatzeKL2);

    @JsonGetter(ApiNames.SITZPLATZEKL3)
    @JsonView(Views.Public.class)
    Integer getSitzPlatzeKL3();

    @JsonSetter(ApiNames.SITZPLATZEKL3)
    void setSitzPlatzeKL3(Integer sitzPlatzeKL3);

    @JsonGetter(ApiNames.SITZPLATZEKL4)
    @JsonView(Views.Public.class)
    Integer getSitzPlatzeKL4();

    @JsonSetter(ApiNames.SITZPLATZEKL4)
    void setSitzPlatzeKL4(Integer sitzPlatzeKL4);

    @JsonGetter(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    String getAufbau();

    @JsonSetter(ApiNames.AUFBAU)
    void setAufbau(String aufbau);

    @JsonGetter(ApiNames.TRIEBZUGANZEIGEN)
    @JsonView(Views.Public.class)
    Boolean getTriebzugAnzeigen();

    @JsonSetter(ApiNames.TRIEBZUGANZEIGEN)
    void setTriebzugAnzeigen(Boolean triebzugAnzeigen);

    @JsonGetter(ApiNames.TRIEBKOEPFE)
    @JsonView(Views.Public.class)
    Integer getTriebkoepfe();

    @JsonSetter(ApiNames.TRIEBKOEPFE)
    void setTriebkoepfe(Integer triebkoepfe);

    @JsonGetter(ApiNames.MITTELWAGEN)
    @JsonView(Views.Public.class)
    Integer getMittelwagen();

    @JsonSetter(ApiNames.MITTELWAGEN)
    void setMittelwagen(Integer mittelwagen);

    @JsonGetter(ApiNames.SITZPLATZETZKL1)
    @JsonView(Views.Public.class)
    Integer getSitzPlatzeTZKL1();

    @JsonSetter(ApiNames.SITZPLATZETZKL1)
    void setSitzPlatzeTZKL1(Integer sitzPlatzeTZKL1);

    @JsonGetter(ApiNames.SITZPLATZETZKL2)
    @JsonView(Views.Public.class)
    Integer getSitzPlatzeTzKL2();

    @JsonSetter(ApiNames.SITZPLATZETZKL2)
    void setSitzPlatzeTzKL2(Integer sitzPlatzeTzKL2);

    @JsonGetter(ApiNames.DREHGESTELLBAUART)
    @JsonView(Views.Public.class)
    String getDrehgestellBauart();

    @JsonSetter(ApiNames.DREHGESTELLBAUART)
    void setDrehgestellBauart(String drehgestellbauart);

    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.Public.class)
    @JsonSerialize(as=String.class, using = PathSerializer.class)
    @ApiModelProperty(name= ApiNames.ABBILDUNG, dataType = "String", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Path getAbbildung();

    @JsonIgnore
    void setAbbildung(Path abbildung);
}