package com.linepro.modellbahn.model.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.FileConverter;
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
import com.linepro.modellbahn.rest.json.serialization.ProduktTeilSerializer;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Produkt. A product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "Produkt")
@Table(name = "Produkt", indexes = {
        @Index(columnList = DBNames.HERSTELLER_ID ),
        @Index(columnList = DBNames.EPOCH_ID),
        @Index(columnList = DBNames.GATTUNG_ID),
        @Index(columnList = DBNames.BAHNVERWALTUNG_ID),
        @Index(columnList = DBNames.ACHSFOLG_ID),
        @Index(columnList = DBNames.MASSSTAB_ID),
        @Index(columnList = DBNames.SPURWEITE_ID),
        @Index(columnList = DBNames.UNTER_KATEGORIE_ID),
        @Index(columnList = DBNames.SONDERMODEL_ID),
        @Index(columnList = DBNames.AUFBAU_ID),
        @Index(columnList = DBNames.LICHT_ID),
        @Index(columnList = DBNames.KUPPLUNG_ID),
        @Index(columnList = DBNames.VORBILD_ID),
        @Index(columnList = DBNames.STEUERUNG_ID),
        @Index(columnList = DBNames.DECODER_TYP_ID),
        @Index(columnList = DBNames.MOTOR_TYP_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.HERSTELLER_ID, DBNames.NAME }) })
@JsonRootName(value = ApiNames.PRODUKT)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.UNTER_KATEGORIE, ApiNames.MASSSTAB,
        ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG, ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER,
        ApiNames.BAUZEIT, ApiNames.VORBILD, ApiNames.ACHSFOLG, ApiNames.ANMERKUNG, ApiNames.SONDERMODEL,
        ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.STEUERUNG, ApiNames.DECODER_TYP,
        ApiNames.MOTOR_TYP, ApiNames.LANGE, ApiNames.ANLEITUNGEN, ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.ABBILDUNG,
        ApiNames.TEILEN, ApiNames.DELETED, ApiNames.LINKS })
public class Produkt extends AbstractNamedItem<ProduktKey> implements IProdukt {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8098838727023710484L;

    /** The hersteller. */
    private IHersteller hersteller;

    /** The unter kategorie. */
    private IUnterKategorie unterKategorie;

    /** The massstab. */
    private IMassstab massstab;

    /** The spurweite. */
    private ISpurweite spurweite;

    /** The bahnverwaltung. */
    private IBahnverwaltung bahnverwaltung;

    /** The gattung. */
    private IGattung gattung;

    /** The epoch. */
    private IEpoch epoch;

    /** The achsfolge. */
    private IAchsfolg achsfolge;

    /** The Sondermodel. */
    private ISonderModell sondermodel;

    /** The aufbau. */
    private IAufbau aufbau;

    /** The licht. */
    private ILicht licht;

    /** The kupplung. */
    private IKupplung kupplung;

    /** The vorbild. */
    private IVorbild vorbild;

    /** The steuerung. */
    private ISteuerung steuerung;

    /** The decoder typ. */
    private IDecoderTyp decoderTyp;

    /** The motor typ. */
    private IMotorTyp motorTyp;

    /** The anmerkung. */
    private String anmerkung;

    /** The betreibsnummer. */
    private String betreibsnummer;

    /** The bauzeit. */
    private Date bauzeit;

    /** The anleitungen. */
    private File anleitungen;

    /** The explosionszeichnung. */
    private File explosionszeichnung;

    /** The lange. */
    private BigDecimal lange;

    /** The abbildung. */
    private File abbildung;

    /** The teilen. */
    private List<IProduktTeil> teilen = new ArrayList<>();

    public Produkt() {
        super();
    }

    public Produkt(Long id, IHersteller hersteller, String bestellNr, String bezeichnung, IUnterKategorie unterKategorie,
            IMassstab massstab, ISpurweite spurweite, IEpoch epoch, IBahnverwaltung bahnverwaltung, IGattung gattung,
            String betreibsnummer, Date bauzeit, IVorbild vorbild, IAchsfolg achsfolge, String anmerkung,
            ISonderModell sondermodel, IAufbau aufbau, ILicht licht, IKupplung kupplung, ISteuerung steuerung,
            IDecoderTyp decoderTyp, IMotorTyp motorTyp, BigDecimal lange, File anleitungen, File explosionszeichnung,
            File abbildung, Boolean deleted) {
        super(id, bestellNr, bezeichnung, deleted);
        setHersteller(hersteller);
        setUnterKategorie(unterKategorie);
        setMassstab(massstab);
        setSpurweite(spurweite);
        setEpoch(epoch);
        setBahnverwaltung(bahnverwaltung);
        setGattung(gattung);
        setBetreibsnummer(betreibsnummer);
        setBauzeit(bauzeit);
        setVorbild(vorbild);
        setAchsfolg(achsfolge);
        setAnmerkung(anmerkung);
        setSondermodel(sondermodel);
        setAufbau(aufbau);
        setLicht(licht);
        setKupplung(kupplung);
        setSteuerung(steuerung);
        setDecoderTyp(decoderTyp);
        setMotorTyp(motorTyp);
        setLange(lange);
        setAnleitungen(anleitungen);
        setExplosionszeichnung(explosionszeichnung);
        setAbbildung(abbildung);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk16"))
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = HerstellerResolver.class)
    public IHersteller getHersteller() {
        return hersteller;
    }

    @Override
    @JsonSetter(ApiNames.HERSTELLER)
    @JsonDeserialize(as = Hersteller.class)
    public void setHersteller(IHersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonSetter(ApiNames.BESTELL_NR)
    public void setName(String bestellNr) {
        super.setName(bestellNr);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk7"))
    @JsonGetter(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using = UnterKategorieSerializer.class)
    public IUnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    @Override
    @JsonSetter(ApiNames.UNTER_KATEGORIE)
    @JsonDeserialize(as = UnterKategorie.class)
    public void setUnterKategorie(IUnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Massstab.class)
    @JoinColumn(name = DBNames.MASSSTAB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk5"))
    @JsonGetter(ApiNames.MASSSTAB)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = MassstabResolver.class)
    public IMassstab getMassstab() {
        return massstab;
    }

    @Override
    @JsonSetter(ApiNames.MASSSTAB)
    @JsonDeserialize(as = Massstab.class)
    public void setMassstab(IMassstab massstab) {
        this.massstab = massstab;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Spurweite.class)
    @JoinColumn(name = DBNames.SPURWEITE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk6"))
    @JsonGetter(ApiNames.SPURWEITE)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = SpurweiteResolver.class)
    public ISpurweite getSpurweite() {
        return spurweite;
    }

    @Override
    @JsonSetter(ApiNames.SPURWEITE)
    @JsonDeserialize(as = Spurweite.class)
    public void setSpurweite(ISpurweite spurweite) {
        this.spurweite = spurweite;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Epoch.class)
    @JoinColumn(name = DBNames.EPOCH_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk1"))
    @JsonGetter(ApiNames.EPOCH)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = EpochResolver.class)
    public IEpoch getEpoch() {
        return epoch;
    }

    @Override
    @JsonSetter(ApiNames.EPOCH)
    @JsonDeserialize(as = Epoch.class)
    public void setEpoch(IEpoch epoch) {
        this.epoch = epoch;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk3"))
    @JsonGetter(ApiNames.BAHNVERWALTUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = BahnverwaltungResolver.class)
    public IBahnverwaltung getBahnverwaltung() {
        return bahnverwaltung;
    }

    @Override
    @JsonSetter(ApiNames.BAHNVERWALTUNG)
    @JsonDeserialize(as = Bahnverwaltung.class)
    public void setBahnverwaltung(IBahnverwaltung bahnverwaltung) {
        this.bahnverwaltung = bahnverwaltung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk2"))
    @JsonGetter(ApiNames.GATTUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = GattungResolver.class)
    public IGattung getGattung() {
        return gattung;
    }

    @Override
    @JsonSetter(ApiNames.GATTUNG)
    @JsonDeserialize(as = Gattung.class)
    public void setGattung(IGattung gattung) {
        this.gattung = gattung;
    }

    @Override
    @Column(name = DBNames.BETREIBSNUMMER, nullable = true, length = 100)
    @JsonGetter(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.Public.class)
    public String getBetreibsnummer() {
        return betreibsnummer;
    }

    @Override
    @JsonSetter(ApiNames.BETREIBSNUMMER)
    public void setBetreibsnummer(String betreibsnummer) {
        this.betreibsnummer = betreibsnummer;
    }

    @Override
    @Column(name = DBNames.BAUZEIT, nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter(ApiNames.BAUZEIT)
    @JsonView(Views.Public.class)
    @JsonFormat(shape = Shape.STRING, pattern = Formats.ISO8601_DATE)
    public Date getBauzeit() {
        return bauzeit;
    }

    @Override
    @JsonSetter(ApiNames.BAUZEIT)
    public void setBauzeit(Date bauzeit) {
        this.bauzeit = bauzeit;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Vorbild.class)
    @JoinColumn(name = DBNames.VORBILD_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk12"))
    @JsonGetter(ApiNames.VORBILD)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = VorbildResolver.class)
    @JsonSerialize(as = Vorbild.class)
    public IVorbild getVorbild() {
        return vorbild;
    }

    @Override
    @JsonSetter(ApiNames.VORBILD)
    @JsonDeserialize(as = Vorbild.class)
    public void setVorbild(IVorbild vorbild) {
        this.vorbild = vorbild;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk4"))
    @JsonGetter(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = AchsfolgResolver.class)
    @JsonSerialize(as = Achsfolg.class)
    public IAchsfolg getAchsfolg() {
        return achsfolge;
    }

    @Override
    @JsonSetter(ApiNames.ACHSFOLG)
    @JsonDeserialize(as = Achsfolg.class)
    public void setAchsfolg(IAchsfolg achsfolge) {
        this.achsfolge = achsfolge;
    }

    @Override
    @Column(name = DBNames.ANMERKUNG, nullable = true, length = 100)
    @JsonGetter(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    public String getAnmerkung() {
        return anmerkung;
    }

    @Override
    @JsonSetter(ApiNames.ANMERKUNG)
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SonderModell.class)
    @JoinColumn(name = DBNames.SONDERMODEL_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk8"))
    @JsonGetter(ApiNames.SONDERMODEL)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = SonderModellResolver.class)
    @JsonSerialize(as = SonderModell.class)
    public ISonderModell getSondermodel() {
        return sondermodel;
    }

    @Override
    @JsonSetter(ApiNames.SONDERMODEL)
    @JsonDeserialize(as = SonderModell.class)
    public void setSondermodel(ISonderModell sondermodel) {
        this.sondermodel = sondermodel;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Aufbau.class)
    @JoinColumn(name = DBNames.AUFBAU_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk9"))
    @JsonGetter(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = AufbauResolver.class)
    @JsonSerialize(as = Aufbau.class)
    public IAufbau getAufbau() {
        return aufbau;
    }

    @Override
    @JsonSetter(ApiNames.AUFBAU)
    @JsonDeserialize(as = Aufbau.class)
    public void setAufbau(IAufbau aufbau) {
        this.aufbau = aufbau;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = DBNames.LICHT_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk10"))
    @JsonGetter(ApiNames.LICHT)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = LichtResolver.class)
    @JsonSerialize(as = Licht.class)
    public ILicht getLicht() {
        return licht;
    }

    @Override
    @JsonSetter(ApiNames.LICHT)
    @JsonDeserialize(as = Licht.class)
    public void setLicht(ILicht licht) {
        this.licht = licht;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = DBNames.KUPPLUNG_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk11"))
    @JsonGetter(ApiNames.KUPPLUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = KupplungResolver.class)
    @JsonSerialize(as = Kupplung.class)
    public IKupplung getKupplung() {
        return kupplung;
    }

    @Override
    @JsonSetter(ApiNames.KUPPLUNG)
    @JsonDeserialize(as = Kupplung.class)
    public void setKupplung(IKupplung kupplung) {
        this.kupplung = kupplung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = DBNames.STEUERUNG_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk13"))
    @JsonGetter(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = SteuerungResolver.class)
    @JsonSerialize(as = Steuerung.class)
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    @JsonSetter(ApiNames.STEUERUNG)
    @JsonDeserialize(as = Steuerung.class)
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk14"))
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using = DecoderTypSerializer.class)
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    @Override
    @JsonSetter(ApiNames.DECODER_TYP)
    @JsonDeserialize(as = DecoderTyp.class)
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = DBNames.MOTOR_TYP_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "product_fk15"))
    @JsonGetter(ApiNames.MOTOR_TYP)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver = MotorTypResolver.class)
    public IMotorTyp getMotorTyp() {
        return motorTyp;
    }

    @Override
    @JsonSetter(ApiNames.MOTOR_TYP)
    @JsonDeserialize(as = MotorTyp.class)
    public void setMotorTyp(IMotorTyp motorTyp) {
        this.motorTyp = motorTyp;
    }

    @Override
    @Column(name = DBNames.LANGE, nullable = true, precision = 6, scale = 2)
    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.Public.class)
    public BigDecimal getLange() {
        return lange;
    }

    @Override
    @JsonSetter(ApiNames.LANGE)
    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    @Override
    @Column(name = DBNames.ANLEITUNGEN, nullable = true, length = 512)
    @JsonGetter(ApiNames.ANLEITUNGEN)
    @JsonView(Views.Public.class)
    public File getAnleitungen() {
        return anleitungen;
    }

    @Override
    @JsonSetter(ApiNames.ANLEITUNGEN)
    public void setAnleitungen(File anleitungen) {
        this.anleitungen = anleitungen;
    }

    @Override
    @Column(name = DBNames.EXPLOSIONSZEICHNUNG, nullable = true, length = 512)
    @JsonGetter(ApiNames.EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    public File getExplosionszeichnung() {
        return explosionszeichnung;
    }

    @Override
    @JsonSetter(ApiNames.EXPLOSIONSZEICHNUNG)
    public void setExplosionszeichnung(File explosionszeichnung) {
        this.explosionszeichnung = explosionszeichnung;
    }

    @Override
    @Column(name = DBNames.ABBILDUNG, nullable = true)
    @Convert(converter = FileConverter.class)
    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    public File getAbbildung() {
        return abbildung;
    }

    @Override
    @JsonSetter(ApiNames.ABBILDUNG)
    public void setAbbildung(File abbildung) {
        this.abbildung = abbildung;
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.PRODUKT, targetEntity = ProduktTeil.class, orphanRemoval = true)
    @JsonGetter(ApiNames.TEILEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = ProduktTeilSerializer.class)
    public List<IProduktTeil> getTeilen() {
        return teilen;
    }

    @Override
    @JsonSetter(ApiNames.TEILEN)
    @JsonDeserialize(contentAs = ProduktTeil.class)
    public void setTeilen(List<IProduktTeil> teilen) {
        this.teilen = teilen;
    }

    @Override
    public void addTeil(IProduktTeil funktion) {
        funktion.setProdukt(this);
        getTeilen().add(funktion);
    }

    @Override
    public void removeTeil(IProduktTeil funktion) {
        getTeilen().remove(funktion);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append(ApiNames.HERSTELLER, getHersteller())
                .append(ApiNames.BESTELL_NR, getName())
                .append(ApiNames.UNTER_KATEGORIE, getUnterKategorie())
                .append(ApiNames.MASSSTAB, getMassstab())
                .append(ApiNames.SPURWEITE, getSpurweite())
                .append(ApiNames.BETREIBSNUMMER, getBetreibsnummer())
                .append(ApiNames.EPOCH, getEpoch())
                .append(ApiNames.BAHNVERWALTUNG, getBahnverwaltung())
                .append(ApiNames.GATTUNG, getGattung())
                .append(ApiNames.BAUZEIT, getBauzeit())
                .append(ApiNames.ACHSFOLG, getAchsfolg())
                .append(ApiNames.VORBILD, getVorbild())
                .append(ApiNames.ANMERKUNG, getAnmerkung())
                .append(ApiNames.SONDERMODEL, getSondermodel())
                .append(ApiNames.AUFBAU, getAufbau())
                .append(ApiNames.LICHT, getLicht())
                .append(ApiNames.KUPPLUNG, getKupplung())
                .append(ApiNames.STEUERUNG, getSteuerung())
                .append(ApiNames.DECODER_TYP, getDecoderTyp())
                .append(ApiNames.MOTOR_TYP, getMotorTyp())
                .append(ApiNames.LANGE, getLange())
                .append(ApiNames.ANLEITUNGEN, getAnleitungen())
                .append(ApiNames.EXPLOSIONSZEICHNUNG, getExplosionszeichnung())
                .append(ApiNames.ABBILDUNG, getAbbildung())
                .append(ApiNames.TEILEN, getTeilen())
                .toString();
    }
}