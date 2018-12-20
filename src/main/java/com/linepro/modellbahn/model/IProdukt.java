package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Date;
import java.util.Set;

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
import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.impl.Vorbild;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.AchsfolgResolver;
import com.linepro.modellbahn.rest.json.resolver.AufbauResolver;
import com.linepro.modellbahn.rest.json.resolver.BahnverwaltungResolver;
import com.linepro.modellbahn.rest.json.resolver.EpochResolver;
import com.linepro.modellbahn.rest.json.resolver.GattungResolver;
import com.linepro.modellbahn.rest.json.resolver.HerstellerResolver;
import com.linepro.modellbahn.rest.json.resolver.KupplungResolver;
import com.linepro.modellbahn.rest.json.resolver.LichtResolver;
import com.linepro.modellbahn.rest.json.resolver.MassstabResolver;
import com.linepro.modellbahn.rest.json.resolver.MotorTypResolver;
import com.linepro.modellbahn.rest.json.resolver.SonderModellResolver;
import com.linepro.modellbahn.rest.json.resolver.SpurweiteResolver;
import com.linepro.modellbahn.rest.json.resolver.SteuerungResolver;
import com.linepro.modellbahn.rest.json.resolver.VorbildResolver;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypSerializer;
import com.linepro.modellbahn.rest.json.serialization.IDecoderTypRef;
import com.linepro.modellbahn.rest.json.serialization.IProduktTeilRef;
import com.linepro.modellbahn.rest.json.serialization.IUnterKategorieRef;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.json.serialization.ProduktTeilSerializer;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import io.swagger.annotations.ApiModelProperty;

/**
 * IProdukt.
 * 
 * @author $Author$
 * @version $Id$
 */
@JsonRootName(value = ApiNames.PRODUKT)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.UNTER_KATEGORIE, ApiNames.MASSSTAB,
        ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG, ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER,
        ApiNames.BAUZEIT, ApiNames.VORBILD, ApiNames.ACHSFOLG, ApiNames.ANMERKUNG, ApiNames.SONDERMODELL,
        ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.STEUERUNG, ApiNames.DECODER_TYP,
        ApiNames.MOTOR_TYP, ApiNames.LANGE, ApiNames.ANLEITUNGEN, ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.ABBILDUNG,
        ApiNames.TEILEN, ApiNames.DELETED, ApiNames.LINKS })
public interface IProdukt extends INamedItem<ProduktKey> {

    /**
     * Gets the hersteller.
     *
     * @return the hersteller
     */
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = HerstellerResolver.class)
    IHersteller getHersteller();

    /**
     * Sets the hersteller.
     *
     * @param hersteller
     *            the new hersteller
     */
    @JsonSetter(ApiNames.HERSTELLER)
    @JsonDeserialize(as = Hersteller.class)
    void setHersteller(IHersteller hersteller);

    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    String getName();

    @JsonSetter(ApiNames.BESTELL_NR)
    void setName(String bestellNr);

    /**
     * Gets the unter kategorie.
     *
     * @return the unter kategorie
     */
    @JsonGetter(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IUnterKategorieRef.class, using = UnterKategorieSerializer.class)
    IUnterKategorie getUnterKategorie();

    /**
     * Sets the unter kategorie.
     *
     * @param unterKategorie
     *            the new unter kategorie
     */
    @JsonSetter(ApiNames.UNTER_KATEGORIE)
    @JsonDeserialize(as = UnterKategorie.class)
    void setUnterKategorie(IUnterKategorie unterKategorie);

    /**
     * Gets the massstab.
     *
     * @return the massstab
     */
    @JsonGetter(ApiNames.MASSSTAB)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = MassstabResolver.class)
    IMassstab getMassstab();

    /**
     * Sets the massstab.
     *
     * @param massstab
     *            the new massstab
     */
    @JsonSetter(ApiNames.MASSSTAB)
    @JsonDeserialize(as = Massstab.class)
    void setMassstab(IMassstab massstab);

    /**
     * Gets the spurweite.
     *
     * @return the spurweite
     */
    @JsonGetter(ApiNames.SPURWEITE)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = SpurweiteResolver.class)
    ISpurweite getSpurweite();

    /**
     * Sets the spurweite.
     *
     * @param spurweite
     *            the new spurweite
     */
    @JsonSetter(ApiNames.SPURWEITE)
    @JsonDeserialize(as = Spurweite.class)
    void setSpurweite(ISpurweite spurweite);
    /**
     * Gets the epoch.
     *
     * @return the epoch
     */
    @JsonGetter(ApiNames.EPOCH)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = EpochResolver.class)
    IEpoch getEpoch();

    /**
     * Sets the epoch.
     *
     * @param epoch
     *            the new epoch
     */
    @JsonSetter(ApiNames.EPOCH)
    @JsonDeserialize(as = Epoch.class)
    void setEpoch(IEpoch epoch);

    /**
     * Gets the bahnverwaltung.
     *
     * @return the bahnverwaltung
     */
    @JsonGetter(ApiNames.BAHNVERWALTUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = BahnverwaltungResolver.class)
    IBahnverwaltung getBahnverwaltung();

    /**
     * Sets the bahnverwaltung.
     *
     * @param bahnverwaltung
     *            the new bahnverwaltung
     */
    @JsonSetter(ApiNames.BAHNVERWALTUNG)
    @JsonDeserialize(as = Bahnverwaltung.class)
    void setBahnverwaltung(IBahnverwaltung bahnverwaltung);

    /**
     * Gets the gattung.
     *
     * @return the gattung
     */
    @JsonGetter(ApiNames.GATTUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = GattungResolver.class)
    IGattung getGattung();

    /**
     * Sets the gattung.
     *
     * @param gattung
     *            the new gattung
     */
    @JsonSetter(ApiNames.GATTUNG)
    @JsonDeserialize(as = Gattung.class)
    void setGattung(IGattung gattung);

    /**
     * Gets the betreibsnummer.
     *
     * @return the betreibsnummer
     */
    @JsonGetter(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.Public.class)
    String getBetreibsnummer();

    /**
     * Sets the betreibsnummer.
     *
     * @param betreibsnummer
     *            the new betreibsnummer
     */
    @JsonSetter(ApiNames.BETREIBSNUMMER)
    void setBetreibsnummer(String betreibsnummer);

    /**
     * Gets the bauzeit.
     *
     * @return the bauzeit
     */
    @JsonGetter(ApiNames.BAUZEIT)
    @JsonView(Views.Public.class)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    Date getBauzeit();

    /**
     * Gets the vorbild.
     *
     * @return the vorbild
     */
    @JsonGetter(ApiNames.VORBILD)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = VorbildResolver.class)
    IVorbild getVorbild();

    /**
     * Sets the vorbild.
     *
     * @param vorbild
     *            the new vorbild
     */
    @JsonSetter(ApiNames.VORBILD)
    @JsonDeserialize(as = Vorbild.class)
    void setVorbild(IVorbild vorbild);

    /**
     * Sets the bauzeit.
     *
     * @param bauzeit
     *            the new bauzeit
     */
    @JsonSetter(ApiNames.BAUZEIT)
    void setBauzeit(Date bauzeit);

    /**
     * Gets the achsfolg.
     *
     * @return the achsfolg
     */
    @JsonGetter(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = AchsfolgResolver.class)
    IAchsfolg getAchsfolg();

    /**
     * Sets the achsfolg.
     *
     * @param achsfolg
     *            the new achsfolg
     */
    @JsonSetter(ApiNames.ACHSFOLG)
    @JsonDeserialize(as = Achsfolg.class)
    void setAchsfolg(IAchsfolg achsfolg);

    /**
     * Gets the sondermodell.
     *
     * @return the sondermodell
     */
    @JsonGetter(ApiNames.SONDERMODELL)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = SonderModellResolver.class)
    ISonderModell getSondermodell();

    /**
     * Sets the sondermodell.
     *
     * @param sondermodell
     *            the new sondermodell
     */
    @JsonSetter(ApiNames.SONDERMODELL)
    @JsonDeserialize(as = SonderModell.class)
    void setSondermodell(ISonderModell sondermodell);

    /**
     * Gets the aufbau.
     *
     * @return the aufbau
     */
    @JsonGetter(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = AufbauResolver.class)
    IAufbau getAufbau();

    /**
     * Sets the aufbau.
     *
     * @param aufbau
     *            the new aufbau
     */
    @JsonSetter(ApiNames.AUFBAU)
    @JsonDeserialize(as = Aufbau.class)
    void setAufbau(IAufbau aufbau);

    /**
     * Gets the licht.
     *
     * @return the licht
     */
    @JsonGetter(ApiNames.LICHT)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = LichtResolver.class)
    ILicht getLicht();

    /**
     * Sets the licht.
     *
     * @param licht
     *            the new licht
     */
    @JsonSetter(ApiNames.LICHT)
    @JsonDeserialize(as = Licht.class)
    void setLicht(ILicht licht);

    /**
     * Gets the kupplung.
     *
     * @return the kupplung
     */
    @JsonGetter(ApiNames.KUPPLUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = KupplungResolver.class)
    IKupplung getKupplung();

    /**
     * Sets the kupplung.
     *
     * @param kupplung
     *            the new kupplung
     */
    @JsonSetter(ApiNames.KUPPLUNG)
    @JsonDeserialize(as = Kupplung.class)
    void setKupplung(IKupplung kupplung);

    /**
     * Gets the steuerung.
     *
     * @return the steuerung
     */
    @JsonGetter(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = SteuerungResolver.class)
    ISteuerung getSteuerung();

    /**
     * Sets the steuerung.
     *
     * @param steuerung
     *            the new steuerung
     */
    @JsonSetter(ApiNames.STEUERUNG)
    @JsonDeserialize(as = Steuerung.class)
    void setSteuerung(ISteuerung steuerung);

    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypRef.class, using = DecoderTypSerializer.class)
    IDecoderTyp getDecoderTyp();

    /**
     * Sets the decoder typ.
     *
     * @param decoderTyp
     *            the new decoder typ
     */
    @JsonSetter(ApiNames.DECODER_TYP)
    @JsonDeserialize(as = DecoderTyp.class)
    void setDecoderTyp(IDecoderTyp decoderTyp);

    /**
     * Gets the motor typ.
     *
     * @return the motor typ
     */
    @JsonGetter(ApiNames.MOTOR_TYP)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = MotorTypResolver.class)
    IMotorTyp getMotorTyp();

    /**
     * Sets the motor typ.
     *
     * @param motorTyp
     *            the new motor typ
     */
    @JsonSetter(ApiNames.MOTOR_TYP)
    @JsonDeserialize(as = MotorTyp.class)
    void setMotorTyp(IMotorTyp motorTyp);

    /**
     * Gets the lange.
     *
     * @return the lange
     */
    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.Public.class)
    BigDecimal getLange();

    /**
     * Sets the lange.
     *
     * @param lange
     *            the new lange
     */
    @JsonSetter(ApiNames.LANGE)
    void setLange(BigDecimal lange);

    /**
     * Gets the anmerkung.
     *
     * @return the anmerkung
     */
    @JsonGetter(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    String getAnmerkung();

    /**
     * Sets the anmerkung.
     *
     * @param anmerkung
     *            the new anmerkung
     */
    @JsonSetter(ApiNames.ANMERKUNG)
    void setAnmerkung(String anmerkung);

    /**
     * Gets the anleitungen.
     *
     * @return the anleitungen
     */
    @JsonGetter(ApiNames.ANLEITUNGEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(as=String.class, using = PathSerializer.class)
    @ApiModelProperty(name=ApiNames.ANLEITUNGEN, dataType = "String", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Path getAnleitungen();

    /**
     * Sets the anleitungen.
     *
     * @param anleitungen
     *            the new anleitungen
     */
    @JsonIgnore
    void setAnleitungen(Path anleitungen);

    /**
     * Gets the explosionszeichnung.
     *
     * @return the explosionszeichnung
     */
    @JsonGetter(ApiNames.EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @JsonSerialize(as=String.class, using = PathSerializer.class)
    @ApiModelProperty(name=ApiNames.EXPLOSIONSZEICHNUNG, dataType = "String", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Path getExplosionszeichnung();

    /**
     * Sets the explosionszeichnung.
     *
     * @param explosionszeichnung
     *            the new explosionszeichnung
     */
    @JsonIgnore
    void setExplosionszeichnung(Path explosionszeichnung);

    /**
     * Gets the abbildung.
     *
     * @return the abbildung
     */
    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.Public.class)
    @JsonSerialize(as=String.class, using = PathSerializer.class)
    @ApiModelProperty(name=ApiNames.ABBILDUNG, dataType = "String", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Path getAbbildung();

    /**
     * Sets the abbildung.
     *
     * @param abbildung
     *            the new abbildung
     */
    @JsonIgnore
    void setAbbildung(Path abbildung);

    /**
     * Gets the teilen.
     *
     * @return the teilen
     */
    @JsonGetter(ApiNames.TEILEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentAs= IProduktTeilRef.class, contentUsing = ProduktTeilSerializer.class)
    Set<IProduktTeil> getTeilen();

    /**
     * Sets the teilen.
     *
     * @param teilen
     *            the new teilen
     */
    @JsonIgnore
    void setTeilen(Set<IProduktTeil> teilen);

    void addTeil(IProduktTeil funktion);

    void removeTeil(IProduktTeil funktion);
}