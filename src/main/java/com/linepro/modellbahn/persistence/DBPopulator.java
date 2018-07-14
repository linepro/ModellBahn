package com.linepro.modellbahn.persistence;

import javax.inject.Inject;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.AdressTyp;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.model.impl.Land;
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.impl.ZugTyp;

public class DBPopulator {

    private final IItemPersisterFactory persisterFactory;

    private final Logger logger;

    @Inject
    public DBPopulator(IItemPersisterFactory persisterFactory, ILoggerFactory logManger) {
        this.persisterFactory = persisterFactory;
        this.logger = logManger.getLogger(DBPopulator.class.getName());
    }

    public void populate() throws Exception {
        logger.info("Start population");

        populateAchsfolg();
        populateAdressTyp();
        populateAntrieb();
        populateAufbau();
        populateBahnverwaltung();
        populateDecoderTyp();
        populateDecoderTypCV();
        populateDecoderTypFunktion();
        populateEpoch();
        populateGattung();
        populateHersteller();
        populateKategorie();
        populateKupplung();
        populateLand();
        populateLicht();
        populateMassstab();
        populateMotorTyp();
        populateProtokoll();
        populateSonderModell();
        populateSpurweite();
        populateSteuerung();
        populateUnterKategorie();
        populateVorbild();
        populateWahrung();
        populateZugTyp();

        populateAdress();
        populateProdukt();
        populateProduktTeil();
        populateDecoder();
        populateDecoderCV();
        populateDecoderFunktion();
        populateArtikel();
        populateZug();
        populateZugConsist();
        
        logger.info("Population complete");
    }

    protected void populateAchsfolg() throws Exception {
        IItemPersister<Achsfolg> persister = persisterFactory.create(Achsfolg.class);
    
        long id = 1L;

        persister.add(new Achsfolg( id++, "(1'C)D 3'2'T35", "(1'C)D 3'2'T35", false));
        persister.add(new Achsfolg( id++, "1'C 1' h2t", "1'C 1' h2t", false));
        persister.add(new Achsfolg( id++, "1'C h2 3T16", "1'C h2 3T16", false));
        persister.add(new Achsfolg( id++, "1'D 1' h2t", "1'D 1' h2t", false));
        persister.add(new Achsfolg( id++, "1'E 1' h3 2'3'T28", "1'E 1' h3 2'3'T28", false));
        persister.add(new Achsfolg( id++, "1'E h2 2'2T26 KAB5", "1'E h2 2'2T26 KAB5", false));
        persister.add(new Achsfolg( id++, "1'E1' h2t", "1'E1' h2t", false));
        persister.add(new Achsfolg( id++, "2'C 1' h3 2'2'T26", "2'C 1' h3 2'2'T26", false));
        persister.add(new Achsfolg( id++, "2'C1' 2'2'T26", "2'C1' 2'2'T26", false));
        persister.add(new Achsfolg( id++, "2'C1' h3 2'2'T40", "2'C1' h3 2'2'T40", false));
        persister.add(new Achsfolg( id++, "A1 dm", "A1 dm", false));
        persister.add(new Achsfolg( id++, "AA dm", "AA dm", false));
        persister.add(new Achsfolg( id++, "B drn", "B drn", false));
        persister.add(new Achsfolg( id++, "B h2t", "B h2t", false));
        persister.add(new Achsfolg( id++, "B'2' dh 2'2' 2'2' 2'B' dh", "B'2' dh 2'2' 2'2' 2'B' dh", false));
        persister.add(new Achsfolg( id++, "B'B' de", "B'B' de", false));
        persister.add(new Achsfolg( id++, "B'B' dh", "B'B' dh", false));
        persister.add(new Achsfolg( id++, "B'B'", "B'B'", false));
        persister.add(new Achsfolg( id++, "Bo'2' e", "Bo'2' e", false));
        persister.add(new Achsfolg( id++, "Bo'Bo'+2'2'+Bo'Bo'", "Bo'Bo'+2'2'+Bo'Bo'", false));
        persister.add(new Achsfolg( id++, "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", false));
        persister.add(new Achsfolg( id++, "C dh", "C dh", false));
        persister.add(new Achsfolg( id++, "C h2t", "C h2t", false));
        persister.add(new Achsfolg( id++, "C'C' dh", "C'C' dh", false));
        persister.add(new Achsfolg( id++, "Co'Co' w6gf", "Co'Co' w6gf", false));
        persister.add(new Achsfolg( id++, "Co'Co'", "Co'Co'", false));
        persister.add(new Achsfolg( id++, "D h2t", "D h2t", false));
        persister.add(new Achsfolg( id++, "D'D h4vt", "D'D h4vt", false));
    }
    
    protected void populateAdress() throws Exception {
    }

    protected void populateAdressTyp() throws Exception {
        IItemPersister<AdressTyp> persister = persisterFactory.create(AdressTyp.class);
        
        long id = 1L;

    }

    protected void populateAntrieb() throws Exception {
        IItemPersister<Antrieb> persister = persisterFactory.create(Antrieb.class);

        long id = 1L;

        persister.add(new Antrieb( id++, "Akku", "Akku", false));
        persister.add(new Antrieb( id++, "Dampf", "Dampf", false));
        persister.add(new Antrieb( id++, "Diesel", "Diesel", false));
        persister.add(new Antrieb( id++, "Elektro", "Elektro", false));
        persister.add(new Antrieb( id++, "Druckluft", "Druckluft", false));
    }

    protected void populateArtikel() throws Exception {
    }
    
    protected void populateAufbau() throws Exception {
        IItemPersister<Aufbau> persister = persisterFactory.create(Aufbau.class);

        long id = 1L;

        persister.add(new Aufbau( id++, "Lok Kunststoff", "Fahrgestell der Lok aus Metall", false));
        persister.add(new Aufbau( id++, "Lok Metall / Kunststoff", "Fahrgestell und vorwiegender Aufbau der Loks aus Metall", false));
        persister.add(new Aufbau( id++, "Lok Metall", "Fahrgestell und Aufbau der Lokomotive aus Metall", false));
        persister.add(new Aufbau( id++, "Wagen Kunststoff", "Fahrgestell des Wagens aus Metall", false));
        persister.add(new Aufbau( id++, "Wagen Metall / Kunststoff", "Überwiegender Teil des Wagenaufbaus aus Metall", false));
        persister.add(new Aufbau( id++, "Wagen Metall", "Fahrgestell und Aufbau des Wagens aus Metall", false));
        persister.add(new Aufbau( id++, "Lok Kunststoff / Metall", "Überwiegender Teil des Lokaufbaues aus Metall", false));
    }

    protected void populateBahnverwaltung() throws Exception {
        IItemPersister<Bahnverwaltung> persister = persisterFactory.create(Bahnverwaltung.class);
        
        long id = 1L;

    }
    
    protected void populateDecoder() throws Exception {
    }
    
    protected void populateDecoderCV() throws Exception {
    }
    
    protected void populateDecoderFunktion() throws Exception {
    }

    protected void populateDecoderTyp() throws Exception {
        IItemPersister<DecoderTyp> persister = persisterFactory.create(DecoderTyp.class);
    
        long id = 1L;
    }

    protected void populateDecoderTypCV() throws Exception {
        IItemPersister<DecoderTypCV> persister = persisterFactory.create(DecoderTypCV.class);
        
        long id = 1L;
    }

    protected void populateDecoderTypFunktion() throws Exception {
        IItemPersister<DecoderTypFunktion> persister = persisterFactory.create(DecoderTypFunktion.class);
        
        long id = 1L;

    }
    
    protected void populateEpoch() throws Exception {
        IItemPersister<Epoch> persister = persisterFactory.create(Epoch.class);
        
        long id = 1L;

    }

    protected void populateGattung() throws Exception {
        IItemPersister<Gattung> persister = persisterFactory.create(Gattung.class);
        
        long id = 1L;

        persister.add(new Gattung( id++, "AB3yge", "AB3yge", false));
        persister.add(new Gattung( id++, "ADümh 101", "ADümh 101", false));
        persister.add(new Gattung( id++, "Apümh 121", "Apümh 121", false));
        persister.add(new Gattung( id++, "ARDümz 106", "ARDümz 106", false));
        persister.add(new Gattung( id++, "Aüm 203", "Aüm 203", false));
        persister.add(new Gattung( id++, "Avmz 206", "Avmz 206", false));
        persister.add(new Gattung( id++, "Avümh 111", "Avümh 111", false));
        persister.add(new Gattung( id++, "Avümz 111", "Avümz 111", false));
        persister.add(new Gattung( id++, "B3yge", "B3yge", false));
        persister.add(new Gattung( id++, "B4yge", "B4yge", false));
        persister.add(new Gattung( id++, "BA 115", "BA 115", false));
        persister.add(new Gattung( id++, "BD3yge", "BD3yge", false));
        persister.add(new Gattung( id++, "Bi 18t", "Bi 18t", false));
        persister.add(new Gattung( id++, "Bi", "Bi", false));
        persister.add(new Gattung( id++, "Bockkrän", "Bockkrän", false));
        persister.add(new Gattung( id++, "BR 03", "BR 03", false));
        persister.add(new Gattung( id++, "BR 03.10", "BR 03.10", false));
        persister.add(new Gattung( id++, "BR 10.1", "BR 10.1", false));
        persister.add(new Gattung( id++, "BR 103", "BR 103", false));
        persister.add(new Gattung( id++, "BR 111", "BR 111", false));
        persister.add(new Gattung( id++, "BR 151", "BR 151", false));
        persister.add(new Gattung( id++, "BR 211", "BR 211", false));
        persister.add(new Gattung( id++, "BR 216", "BR 216", false));
        persister.add(new Gattung( id++, "BR 220", "BR 220", false));
        persister.add(new Gattung( id++, "BR 230", "BR 230", false));
        persister.add(new Gattung( id++, "BR 24", "BR 24", false));
        persister.add(new Gattung( id++, "BR 260", "BR 260", false));
        persister.add(new Gattung( id++, "BR 321", "BR 321", false));
        persister.add(new Gattung( id++, "BR 45", "BR 45", false));
        persister.add(new Gattung( id++, "BR 50", "BR 50", false));
        persister.add(new Gattung( id++, "BR 53", "BR 53", false));
        persister.add(new Gattung( id++, "BR 601", "BR 601", false));
        persister.add(new Gattung( id++, "BR 64", "BR 64", false));
        persister.add(new Gattung( id++, "BR 701", "BR 701", false));
        persister.add(new Gattung( id++, "BR 81", "BR 81", false));
        persister.add(new Gattung( id++, "BR 85", "BR 85", false));
        persister.add(new Gattung( id++, "BR 86", "BR 86", false));
        persister.add(new Gattung( id++, "BR 89.0", "BR 89.0", false));
        persister.add(new Gattung( id++, "BR 96", "BR 96", false));
        persister.add(new Gattung( id++, "BR 98.3", "BR 98.3", false));
        persister.add(new Gattung( id++, "BT 10", "BT 10", false));
        persister.add(new Gattung( id++, "BTmm 51", "BTmm 51", false));
        persister.add(new Gattung( id++, "BW Nürnberg-Gostenhof", "BW Nürnberg-Gostenhof", false));
        persister.add(new Gattung( id++, "Chemnitz-Hilbersdorf ", "Chemnitz-Hilbersdorf ", false));
        persister.add(new Gattung( id++, "DByg 546", "DByg 546", false));
        persister.add(new Gattung( id++, "DByg 547", "DByg 547", false));
        persister.add(new Gattung( id++, "DByg 548", "DByg 548", false));
        persister.add(new Gattung( id++, "DHG 700C", "DHG 700C", false));
        persister.add(new Gattung( id++, "ELD4", "ELD4", false));
        persister.add(new Gattung( id++, "El-u 061", "El-u 061", false));
        persister.add(new Gattung( id++, "ET 403", "ET 403", false));
        persister.add(new Gattung( id++, "ET 91", "ET 91", false));
        persister.add(new Gattung( id++, "F7", "F7", false));
        persister.add(new Gattung( id++, "G 10", "G 10", false));
        persister.add(new Gattung( id++, "Gl", "Gl", false));
        persister.add(new Gattung( id++, "Gmhs 53", "Gmhs 53", false));
        persister.add(new Gattung( id++, "Gr 20", "Gr 20", false));
        persister.add(new Gattung( id++, "Gs 210", "Gs 210", false));
        persister.add(new Gattung( id++, "H 10", "H 10", false));
        persister.add(new Gattung( id++, "Habbiins", "Habbiins", false));
        persister.add(new Gattung( id++, "Ibdlps 383", "Ibdlps 383", false));
        persister.add(new Gattung( id++, "Ichqrs 377", "Ichqrs 377", false));
        persister.add(new Gattung( id++, "ICR-A10", "ICR-A10", false));
        persister.add(new Gattung( id++, "ICR-B10", "ICR-B10", false));
        persister.add(new Gattung( id++, "Kbs 443", "Kbs 443", false));
        persister.add(new Gattung( id++, "Kklm 505", "Kklm 505", false));
        persister.add(new Gattung( id++, "Kolonialwarenwagen", "Kolonialwarenwagen", false));
        persister.add(new Gattung( id++, "Laae 540", "Laae 540", false));
        persister.add(new Gattung( id++, "Mannschaftswagen 376", "Mannschaftswagen 376", false));
        persister.add(new Gattung( id++, "NS 6400", "NS 6400", false));
        persister.add(new Gattung( id++, "Om „Breslau“", "Om „Breslau“", false));
        persister.add(new Gattung( id++, "Om 12", "Om 12", false));
        persister.add(new Gattung( id++, "OOtz 50", "OOtz 50", false));
        persister.add(new Gattung( id++, "Otmm 70", "Otmm 70", false));
        persister.add(new Gattung( id++, "Pw 90 HzL", "Pw 90 HzL", false));
        persister.add(new Gattung( id++, "Pwg Pr 14", "Pwg Pr 14", false));
        persister.add(new Gattung( id++, "Pwgs 41", "Pwgs 41", false));
        persister.add(new Gattung( id++, "Pwi 28", "Pwi 28", false));
        persister.add(new Gattung( id++, "Pwi Wü 13", "Pwi Wü 13", false));
        persister.add(new Gattung( id++, "R 02", "R 02", false));
        persister.add(new Gattung( id++, "Rlmmps 651", "Rlmmps 651", false));
        persister.add(new Gattung( id++, "Rlmms 58", "Rlmms 58", false));
        persister.add(new Gattung( id++, "Rlmms", "Rlmms", false));
        persister.add(new Gattung( id++, "Rlmmso 56", "Rlmmso 56", false));
        persister.add(new Gattung( id++, "Rs 684", "Rs 684", false));
        persister.add(new Gattung( id++, "Samms 709", "Samms 709", false));
        persister.add(new Gattung( id++, "Schotterwagen 166", "Schotterwagen 166", false));
        persister.add(new Gattung( id++, "Sm 24", "Sm 24", false));
        persister.add(new Gattung( id++, "SSH 71", "SSH 71", false));
        persister.add(new Gattung( id++, "SSym „Köln“", "SSym „Köln“", false));
        persister.add(new Gattung( id++, "Tehs 50", "Tehs 50", false));
        persister.add(new Gattung( id++, "Tko 02", "Tko 02", false));
        persister.add(new Gattung( id++, "Ucs", "Ucs", false));
        persister.add(new Gattung( id++, "üm 312", "üm 312", false));
        persister.add(new Gattung( id++, "üm 313", "üm 313", false));
        persister.add(new Gattung( id++, "V 200", "V 200", false));
        persister.add(new Gattung( id++, "V 80", "V 80", false));
        persister.add(new Gattung( id++, "Viehtransport", "Viehtransport", false));
        persister.add(new Gattung( id++, "VS 98", "VS 98", false));
        persister.add(new Gattung( id++, "VT 75", "VT 75", false));
        persister.add(new Gattung( id++, "VT 95", "VT 95", false));
        persister.add(new Gattung( id++, "VT 95.9", "VT 95.9", false));
        persister.add(new Gattung( id++, "VT 98", "VT 98", false));
        persister.add(new Gattung( id++, "WGmh 824", "WGmh 824", false));
        persister.add(new Gattung( id++, "WRmz 135", "WRmz 135", false));
        persister.add(new Gattung( id++, "WRümh 131", "WRümh 131", false));
        persister.add(new Gattung( id++, "X05 „Erfurt“", "X05 „Erfurt„“", false));
        persister.add(new Gattung( id++, "Zces", "Zces", false));
    }

    protected void populateHersteller() throws Exception {
        IItemPersister<Hersteller> persister = persisterFactory.create(Hersteller.class);
        
        long id = 1L;

        persister.add(new Hersteller(id++, " Avago Technologies", null, null, null, false));
        persister.add(new Hersteller(id++, "4MFOR", null, null, null, false));
        persister.add(new Hersteller(id++, "Artitec", null, null, null, false));
        persister.add(new Hersteller(id++, "Auhagen", null, null, null, false));
        persister.add(new Hersteller(id++, "B&K", null, null, null, false));
        persister.add(new Hersteller(id++, "Brawa", null, null, null, false));
        persister.add(new Hersteller(id++, "Busch", null, null, null, false));
        persister.add(new Hersteller(id++, "DCC Supplies", null, null, null, false));
        persister.add(new Hersteller(id++, "Deluxe Materials", null, null, null, false));
        persister.add(new Hersteller(id++, "Digital-Bahn", null, null, null, false));
        persister.add(new Hersteller(id++, "DigiTrain", null, null, null, false));
        persister.add(new Hersteller(id++, "Diotec", null, null, null, false));
        persister.add(new Hersteller(id++, "Erbert", null, null, null, false));
        persister.add(new Hersteller(id++, "ESU", null, null, null, false));
        persister.add(new Hersteller(id++, "Evergreen", null, null, null, false));
        persister.add(new Hersteller(id++, "Fairchild", null, null, null, false));
        persister.add(new Hersteller(id++, "Faller", null, null, null, false));
        persister.add(new Hersteller(id++, "Fleischmann", null, null, null, false));
        persister.add(new Hersteller(id++, "Gassner", null, null, null, false));
        persister.add(new Hersteller(id++, "GaugeMaster", null, null, null, false));
        persister.add(new Hersteller(id++, "Heico", null, null, null, false));
        persister.add(new Hersteller(id++, "Herkat", null, null, null, false));
        persister.add(new Hersteller(id++, "Herpa", null, null, null, false));
        persister.add(new Hersteller(id++, "Humbrol", null, null, null, false));
        persister.add(new Hersteller(id++, "Jordan", null, null, null, false));
        persister.add(new Hersteller(id++, "Kibri", null, null, null, false));
        persister.add(new Hersteller(id++, "Kingbright", null, null, null, false));
        persister.add(new Hersteller(id++, "KKPMO", null, null, null, false));
        persister.add(new Hersteller(id++, "Knipex", null, null, null, false));
        persister.add(new Hersteller(id++, "Kühn", null, null, null, false));
        persister.add(new Hersteller(id++, "LDT", null, null, null, false));
        persister.add(new Hersteller(id++, "Liliput", null, null, null, false));
        persister.add(new Hersteller(id++, "Lima", null, null, null, false));
        persister.add(new Hersteller(id++, "Littfinski", null, null, null, false));
        persister.add(new Hersteller(id++, "LUX-Modellbau", null, null, null, false));
        persister.add(new Hersteller(id++, "Maquett", null, null, null, false));
        persister.add(new Hersteller(id++, "Märklin", null, null, null, false));
        persister.add(new Hersteller(id++, "Mehano", null, null, null, false));
        persister.add(new Hersteller(id++, "Merten", null, null, null, false));
        persister.add(new Hersteller(id++, "Noch", null, null, null, false));
        persister.add(new Hersteller(id++, "Omron", null, null, null, false));
        persister.add(new Hersteller(id++, "Preiser", null, null, null, false));
        persister.add(new Hersteller(id++, "Proses", null, null, null, false));
        persister.add(new Hersteller(id++, "Ratio", null, null, null, false));
        persister.add(new Hersteller(id++, "Red Line", null, null, null, false));
        persister.add(new Hersteller(id++, "Revell", null, null, null, false));
        persister.add(new Hersteller(id++, "Ricko", null, null, null, false));
        persister.add(new Hersteller(id++, "Rivarossi", null, null, null, false));
        persister.add(new Hersteller(id++, "Roco", null, null, null, false));
        persister.add(new Hersteller(id++, "RTS", null, null, null, false));
        persister.add(new Hersteller(id++, "Schuco", null, null, null, false));
        persister.add(new Hersteller(id++, "Seuthe", null, null, null, false));
        persister.add(new Hersteller(id++, "Taiwan Semiconductor", null, null, null, false));
        persister.add(new Hersteller(id++, "Tamiya", null, null, null, false));
        persister.add(new Hersteller(id++, "Tams", null, null, null, false));
        persister.add(new Hersteller(id++, "Tower Pro", null, null, null, false));
        persister.add(new Hersteller(id++, "Trix", null, null, null, false));
        persister.add(new Hersteller(id++, "TruOpto", null, null, null, false));
        persister.add(new Hersteller(id++, "Uhlenbrock", null, null, null, false));
        persister.add(new Hersteller(id++, "Unbekant", null, null, null, false));
        persister.add(new Hersteller(id++, "Viessmann", null, null, null, false));
        persister.add(new Hersteller(id++, "Walthers", null, null, null, false));
        persister.add(new Hersteller(id++, "Weinert", null, null, null, false));
        persister.add(new Hersteller(id++, "Wiking", null, null, null, false));
        persister.add(new Hersteller(id++, "Woodland Scenics", null, null, null, false));
        persister.add(new Hersteller(id++, "Zemo", null, null, null, false));
   }

    protected void populateKategorie() throws Exception {
        IItemPersister<Kategorie> persister = persisterFactory.create(Kategorie.class);
        
        long id = 1L;

        persister.add(new Kategorie(id++, "Abteilwagen", "", false));
        persister.add(new Kategorie(id++, "Akku", "", false));
        persister.add(new Kategorie(id++, "Anker", "", false));
        persister.add(new Kategorie(id++, "Ausgestaltung", "", false));
        persister.add(new Kategorie(id++, "Aussichtswagen", "", false));
        persister.add(new Kategorie(id++, "Autotransportwagen", "", false));
        persister.add(new Kategorie(id++, "Bahndienstwagen", "", false));
        persister.add(new Kategorie(id++, "Bananenwagen", "", false));
        persister.add(new Kategorie(id++, "Barwagen", "", false));
        persister.add(new Kategorie(id++, "Bäume", "", false));
        persister.add(new Kategorie(id++, "Behältertragwagen", "", false));
        persister.add(new Kategorie(id++, "Beiwagen", "", false));
        persister.add(new Kategorie(id++, "Bekohlung", "Sondermodell", false));
        persister.add(new Kategorie(id++, "Beleuchtung", "", false));
        persister.add(new Kategorie(id++, "Beschriftigung", "", false));
        persister.add(new Kategorie(id++, "Beschwerung", "", false));
        persister.add(new Kategorie(id++, "Bierwagen", "", false));
        persister.add(new Kategorie(id++, "Blühmen", "", false));
        persister.add(new Kategorie(id++, "Bockkrän", "Sondermodell", false));
        persister.add(new Kategorie(id++, "Bücher", "", false));
        persister.add(new Kategorie(id++, "Büsche", "", false));
        persister.add(new Kategorie(id++, "Carbid-Flaschenwagen", "", false));
        persister.add(new Kategorie(id++, "Containertragwagen", "", false));
        persister.add(new Kategorie(id++, "Dampf", "", false));
        persister.add(new Kategorie(id++, "Decoder", "", false));
        persister.add(new Kategorie(id++, "Diesel", "", false));
        persister.add(new Kategorie(id++, "Doppelstockwagen", "Personenwagen", false));
        persister.add(new Kategorie(id++, "Drehgestell", "", false));
        persister.add(new Kategorie(id++, "Drehgestellrahmen", "", false));
        persister.add(new Kategorie(id++, "Drehscheibe", "Sondermodell", false));
        persister.add(new Kategorie(id++, "Drehschemelwagen", "Güterwagen", false));
        persister.add(new Kategorie(id++, "Elektro", "", false));
        persister.add(new Kategorie(id++, "Entstördrossel", "", false));
        persister.add(new Kategorie(id++, "Ersatzteil", "", false));
        persister.add(new Kategorie(id++, "Fahrradtransportwagen", "Sondermodell", false));
        persister.add(new Kategorie(id++, "Fahrzeug", "", false));
        persister.add(new Kategorie(id++, "Farbe", "", false));
        persister.add(new Kategorie(id++, "Feder", "", false));
        persister.add(new Kategorie(id++, "Feldmagnet", "", false));
        persister.add(new Kategorie(id++, "Fenster", "", false));
        persister.add(new Kategorie(id++, "Fenstersatz", "", false));
        persister.add(new Kategorie(id++, "Figuren", "", false));
        persister.add(new Kategorie(id++, "Flachwagen", "", false));
        persister.add(new Kategorie(id++, "Gaswagen", "", false));
        persister.add(new Kategorie(id++, "Gebaüde", "", false));
        persister.add(new Kategorie(id++, "Gedeckter Güterwagen", "", false));
        persister.add(new Kategorie(id++, "Gepäckwagen", "", false));
        persister.add(new Kategorie(id++, "Gesellschaftswagen", "Sondermodell", false));
        persister.add(new Kategorie(id++, "Gleismateriel", "", false));
        persister.add(new Kategorie(id++, "Gluehlampe", "", false));
        persister.add(new Kategorie(id++, "Großraumwagen", "", false));
        persister.add(new Kategorie(id++, "Grundplatte ", "", false));
        persister.add(new Kategorie(id++, "Güterwagen", "", false));
        persister.add(new Kategorie(id++, "Güterzugbegleitwagen", "", false));
        persister.add(new Kategorie(id++, "Haftreifen", "", false));
        persister.add(new Kategorie(id++, "Halteplatte ", "", false));
        persister.add(new Kategorie(id++, "Hecken", "", false));
        persister.add(new Kategorie(id++, "Hochbordwagen", "", false));
        persister.add(new Kategorie(id++, "Innenbeleuchtung", "", false));
        persister.add(new Kategorie(id++, "Inneneinrichtung", "", false));
        persister.add(new Kategorie(id++, "Isolierung", "", false));
        persister.add(new Kategorie(id++, "Isolierung-Spritzling", "", false));
        persister.add(new Kategorie(id++, "Kabel", "", false));
        persister.add(new Kategorie(id++, "Kabelklemmen", "", false));
        persister.add(new Kategorie(id++, "Kesselwagen", "", false));
        persister.add(new Kategorie(id++, "Klappe Links", "", false));
        persister.add(new Kategorie(id++, "Klappe Rechts", "", false));
        persister.add(new Kategorie(id++, "Kleb", "", false));
        persister.add(new Kategorie(id++, "Kohlbursten", "", false));
        persister.add(new Kategorie(id++, "Kränwagen", "", false));
        persister.add(new Kategorie(id++, "Kühlwagen", "", false));
        persister.add(new Kategorie(id++, "Kuppelstange Links", "", false));
        persister.add(new Kategorie(id++, "Kupplung", "", false));
        persister.add(new Kategorie(id++, "Kupplungsdeichsel", "", false));
        persister.add(new Kategorie(id++, "Kupplungshaken", "", false));
        persister.add(new Kategorie(id++, "Kupplungskinematik", "", false));
        persister.add(new Kategorie(id++, "Kupplungsschacht", "", false));
        persister.add(new Kategorie(id++, "Kurzkupplung", "", false));
        persister.add(new Kategorie(id++, "Ladegut", "", false));
        persister.add(new Kategorie(id++, "Landschaftsbau", "", false));
        persister.add(new Kategorie(id++, "Lautsprecher", "", false));
        persister.add(new Kategorie(id++, "Leitschaufel ", "", false));
        persister.add(new Kategorie(id++, "Leuchteinsatz Oben", "", false));
        persister.add(new Kategorie(id++, "Lokumbausätze", "", false));
        persister.add(new Kategorie(id++, "Mannschaftswagen", "", false));
        persister.add(new Kategorie(id++, "Massefeder", "", false));
        persister.add(new Kategorie(id++, "Messewagen", "Insider", false));
        persister.add(new Kategorie(id++, "Messingblech", "", false));
        persister.add(new Kategorie(id++, "Motorschild", "", false));
        persister.add(new Kategorie(id++, "Mutter", "", false));
        persister.add(new Kategorie(id++, "Nahverkehrswagen", "", false));
        persister.add(new Kategorie(id++, "Niederbordwagen", "", false));
        persister.add(new Kategorie(id++, "Niederflurwagen", "", false));
        persister.add(new Kategorie(id++, "Oberleitung", "", false));
        persister.add(new Kategorie(id++, "Pantograph", "", false));
        persister.add(new Kategorie(id++, "Personenwagen", "", false));
        persister.add(new Kategorie(id++, "Pflanzen", "", false));
        persister.add(new Kategorie(id++, "Prallplatte", "", false));
        persister.add(new Kategorie(id++, "Puffer", "", false));
        persister.add(new Kategorie(id++, "Rad", "", false));
        persister.add(new Kategorie(id++, "Relais", "", false));
        persister.add(new Kategorie(id++, "Relexkupplung", "", false));
        persister.add(new Kategorie(id++, "Rolldachwagen", "", false));
        persister.add(new Kategorie(id++, "Rungenwagen", "", false));
        persister.add(new Kategorie(id++, "Schaltsfeder", "", false));
        persister.add(new Kategorie(id++, "Schiebewandwagen", "", false));
        persister.add(new Kategorie(id++, "Schleifer", "", false));
        persister.add(new Kategorie(id++, "Schneepflug", "", false));
        persister.add(new Kategorie(id++, "Schotterwagen", "", false));
        persister.add(new Kategorie(id++, "Schraube", "", false));
        persister.add(new Kategorie(id++, "Schraubenkupplung", "", false));
        persister.add(new Kategorie(id++, "Schüttgut-Kippwagen", "", false));
        persister.add(new Kategorie(id++, "Schwerlastwagen", "", false));
        persister.add(new Kategorie(id++, "Seitenentladewagen", "", false));
        persister.add(new Kategorie(id++, "Senkschraube", "", false));
        persister.add(new Kategorie(id++, "Set", "", false));
        persister.add(new Kategorie(id++, "Signalbirne", "", false));
        persister.add(new Kategorie(id++, "Signaltechnik", "", false));
        persister.add(new Kategorie(id++, "Silowagen", "", false));
        persister.add(new Kategorie(id++, "Sonderfahrzeug", "", false));
        persister.add(new Kategorie(id++, "Sonstiges", "", false));
        persister.add(new Kategorie(id++, "Speisewagen", "", false));
        persister.add(new Kategorie(id++, "Stange", "", false));
        persister.add(new Kategorie(id++, "Steuerungstechnik", "", false));
        persister.add(new Kategorie(id++, "Steurwagen", "", false));
        persister.add(new Kategorie(id++, "Stromführendekupplungen", "", false));
        persister.add(new Kategorie(id++, "Stromversorgung", "", false));
        persister.add(new Kategorie(id++, "Stromzuführung", "", false));
        persister.add(new Kategorie(id++, "Taschenwagen", "", false));
        persister.add(new Kategorie(id++, "Telex Anker", "", false));
        persister.add(new Kategorie(id++, "Telex Magnet", "", false));
        persister.add(new Kategorie(id++, "Telexkupplung", "", false));
        persister.add(new Kategorie(id++, "Tiefladewagen", "", false));
        persister.add(new Kategorie(id++, "Torpedopfannenwagen", "", false));
        persister.add(new Kategorie(id++, "Traeger", "", false));
        persister.add(new Kategorie(id++, "Triebwagen", "", false));
        persister.add(new Kategorie(id++, "Umbauwagen", "", false));
        persister.add(new Kategorie(id++, "Verschlagwagen", "", false));
        persister.add(new Kategorie(id++, "Viehwagen", "", false));
        persister.add(new Kategorie(id++, "Wagen", "", false));
        persister.add(new Kategorie(id++, "Wagenboden", "", false));
        persister.add(new Kategorie(id++, "Weichenfeder", "", false));
        persister.add(new Kategorie(id++, "Weihnachtswagen", "", false));
        persister.add(new Kategorie(id++, "Weinwagen", "", false));
        persister.add(new Kategorie(id++, "Werkzeug", "", false));
        persister.add(new Kategorie(id++, "Y Schliefer", "", false));
        persister.add(new Kategorie(id++, "Zäune", "", false));
        persister.add(new Kategorie(id++, "Zeichen", "", false));
        persister.add(new Kategorie(id++, "Zubehör", "", false));
        persister.add(new Kategorie(id++, "Zugfeder ", "", false));
        persister.add(new Kategorie(id++, "Zugschlussbeleuchtung", "", false));
        persister.add(new Kategorie(id++, "Zylinderschraube ", "", false));
    }

    protected void populateKupplung() throws Exception {
        IItemPersister<Kupplung> persister = persisterFactory.create(Kupplung.class);
    
        long id = 1L;

    }
    
    protected void populateLand() throws Exception {
        IItemPersister<Land> persister = persisterFactory.create(Land.class);
        
        long id = 1L;

    }
    
    protected void populateLicht() throws Exception {
        IItemPersister<Licht> persister = persisterFactory.create(Licht.class);
        
        long id = 1L;

    }
    
    protected void populateMassstab() throws Exception {
        IItemPersister<Massstab> persister = persisterFactory.create(Massstab.class);
        
        long id = 1L;

        persister.add(new Massstab(id++, "0", "1:45 32 mm", false));
        persister.add(new Massstab(id++, "0e", "1:45 16.5 mm", false));
        persister.add(new Massstab(id++, "0i", "1:45 12 mm", false));
        persister.add(new Massstab(id++, "0m", "1:45 22.5 mm", false));
        persister.add(new Massstab(id++, "0p", "1:45 9 mm", false));
        persister.add(new Massstab(id++, "1\"", "1:12 121 mm", false));
        persister.add(new Massstab(id++, "1", "1:32 45 mm", false));
        persister.add(new Massstab(id++, "1e", "1:32 22.5 mm", false));
        persister.add(new Massstab(id++, "1i", "1:32 16.5 mm", false));
        persister.add(new Massstab(id++, "1m", "1:32 32 mm", false));
        persister.add(new Massstab(id++, "1n3", "1:32 28.6 mm", false));
        persister.add(new Massstab(id++, "1p", "1:32 12 mm", false));
        persister.add(new Massstab(id++, "F", "1:20.32 70.64 mm", false));
        persister.add(new Massstab(id++, "Fn3", "1:20.32 45 mm", false));
        persister.add(new Massstab(id++, "H0", "1:87 16.5 mm", false));
        persister.add(new Massstab(id++, "H0e", "1:87 9 mm", false));
        persister.add(new Massstab(id++, "H0i", "1:87 6.5 mm (H0f)", false));
        persister.add(new Massstab(id++, "H0m", "1:87 12 mm", false));
        persister.add(new Massstab(id++, "H0p", "1:87 4.5 mm", false));
        persister.add(new Massstab(id++, "HOn2", "1:87.1 7 mm", false));
        persister.add(new Massstab(id++, "II", "1:22.5 63.5 mm", false));
        persister.add(new Massstab(id++, "IIe", "1:22.5 32 mm", false));
        persister.add(new Massstab(id++, "Iii (NEM)", "1:22.5 22.5 mm", false));
        persister.add(new Massstab(id++, "III", "1:16 89 mm", false));
        persister.add(new Massstab(id++, "IIIe", "1:16 45 mm", false));
        persister.add(new Massstab(id++, "IIIi (NMRA)", "1:16 32 mm (3/4\")", false));
        persister.add(new Massstab(id++, "IIIm", "1:16 63.5 mm", false));
        persister.add(new Massstab(id++, "IIIp", "1:16 22.5 mm", false));
        persister.add(new Massstab(id++, "IIm", "1:22.5 45 mm", false));
        persister.add(new Massstab(id++, "IIp", "1:22.5 16.5 mm", false));
        persister.add(new Massstab(id++, "N", "1:160 9 mm", false));
        persister.add(new Massstab(id++, "Ne", "1:160 4.5 mm (Nn2)", false));
        persister.add(new Massstab(id++, "Nm", "1:160 6.5 mm (Nn3)", false));
        persister.add(new Massstab(id++, "O", "1:48 31.75 mm", false));
        persister.add(new Massstab(id++, "On2", "1:48 12.7 mm", false));
        persister.add(new Massstab(id++, "On3", "1:48 19.4 mm ", false));
        persister.add(new Massstab(id++, "On30", "1:48 16.5 mm", false));
        persister.add(new Massstab(id++, "OO", "1:76.2 19.05 mm", false));
        persister.add(new Massstab(id++, "S", "1:64 22.5 mm", false));
        persister.add(new Massstab(id++, "Se", "1:64 12 mm", false));
        persister.add(new Massstab(id++, "Si", "1:64 9 mm", false));
        persister.add(new Massstab(id++, "Sm", "1:64 16.5 mm", false));
        persister.add(new Massstab(id++, "Sn3", "1:64 14.3 mm", false));
        persister.add(new Massstab(id++, "Sp", "1:64 6.5 mm", false));
        persister.add(new Massstab(id++, "TT", "1:120 12 mm", false));
        persister.add(new Massstab(id++, "TTe", "1:120 6.5 mm", false));
        persister.add(new Massstab(id++, "TTi", "1:120 4.5 mm", false));
        persister.add(new Massstab(id++, "TTm", "1:120 9 mm", false));
        persister.add(new Massstab(id++, "V", "1:11 127 mm", false));
        persister.add(new Massstab(id++, "Ve", "1:11 63.5 mm", false));
        persister.add(new Massstab(id++, "Vi", "1:11 45 mm", false));
        persister.add(new Massstab(id++, "VII", "1:8 184 mm", false));
        persister.add(new Massstab(id++, "VIIe", "1:8 89 mm", false));
        persister.add(new Massstab(id++, "VIIi", "1:8 63.5 mm", false));
        persister.add(new Massstab(id++, "VIIm", "1:8 127 mm", false));
        persister.add(new Massstab(id++, "VIIp", "1:8 45 mm", false));
        persister.add(new Massstab(id++, "Vm", "1:11 89 mm", false));
        persister.add(new Massstab(id++, "Vp", "1:11 32 mm", false));
        persister.add(new Massstab(id++, "X", "1:5.5 260 mm", false));
        persister.add(new Massstab(id++, "Xe", "1:5.5 127 mm", false));
        persister.add(new Massstab(id++, "Xi", "1:5.5 89 mm", false));
        persister.add(new Massstab(id++, "Xm", "1:5.5 184 mm", false));
        persister.add(new Massstab(id++, "Xp", "1:5.5 63.5 mm", false));
        persister.add(new Massstab(id++, "Z", "1:220 6.5 mm", false));
        persister.add(new Massstab(id++, "Zm", "1:220 4.5 mm", false));
    }
    
    protected void populateMotorTyp() throws Exception {
        IItemPersister<MotorTyp> persister = persisterFactory.create(MotorTyp.class);
        
        long id = 1L;

        persister.add(new MotorTyp(id++, "C-Sinus", "", false));
        persister.add(new MotorTyp(id++, "DCM", "", false));
        persister.add(new MotorTyp(id++, "Glockenanker", "", false));
        persister.add(new MotorTyp(id++, "HLM MS1-7", "", false));
        persister.add(new MotorTyp(id++, "HLM MS1-8", "", false));
        persister.add(new MotorTyp(id++, "HLM MS2-7", "", false));
        persister.add(new MotorTyp(id++, "HLM MS2-8", "", false));
        persister.add(new MotorTyp(id++, "HLM S", "", false));
        persister.add(new MotorTyp(id++, "HLM", "", false));
        persister.add(new MotorTyp(id++, "LFCM MS1-7", "", false));
        persister.add(new MotorTyp(id++, "LFCM MS1-8", "", false));
        persister.add(new MotorTyp(id++, "LFCM MS2-7", "", false));
        persister.add(new MotorTyp(id++, "LFCM MS2-8", "", false));
        persister.add(new MotorTyp(id++, "SFCM", "", false));
    }
    
    protected void populateProdukt() throws Exception {
    }
    
    protected void populateProduktTeil() throws Exception {
    }
    
    protected void populateProtokoll() throws Exception {
        IItemPersister<Protokoll> persister = persisterFactory.create(Protokoll.class);
        
        long id = 1L;


    }
    
    protected void populateSonderModell() throws Exception {
    }
    
    protected void populateSpurweite() throws Exception {
        IItemPersister<Spurweite> persister = persisterFactory.create(Spurweite.class);
        
        long id = 1L;

    }
    
    protected void populateSteuerung() throws Exception {
        IItemPersister<Steuerung> persister = persisterFactory.create(Steuerung.class);
    
        long id = 1L;
    }

    protected void populateUnterKategorie() throws Exception {
        IItemPersister<UnterKategorie> persister = persisterFactory.create(UnterKategorie.class);
        
        long id = 1L;

    }
    protected void populateVorbild() throws Exception {
    }
    
    protected void populateWahrung() throws Exception {
        IItemPersister<Wahrung> persister = persisterFactory.create(Wahrung.class);
        
        long id = 1L;

    }

    protected void populateZug() throws Exception {
    }
    
    protected void populateZugConsist() throws Exception {
    }
    
    protected void populateZugTyp() throws Exception {
        IItemPersister<ZugTyp> persister = persisterFactory.create(ZugTyp.class);
        
        long id = 1L;

    }
}