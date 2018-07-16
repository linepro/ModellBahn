package com.linepro.modellbahn.util;

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
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.IPersisterFactory;

public class DBPopulator {

    private final IPersisterFactory persisterFactory;

    private final Logger logger;

    @Inject
    public DBPopulator(IPersisterFactory persisterFactory, ILoggerFactory logManger) {
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
        IPersister<Achsfolg> persister = persisterFactory.createNamedItemPersister(Achsfolg.class);
    
        persister.add(new Achsfolg( null, "(1'C)D 3'2'T35", "(1'C)D 3'2'T35", false));
        persister.add(new Achsfolg( null, "1'C 1' h2t", "1'C 1' h2t", false));
        persister.add(new Achsfolg( null, "1'C h2 3T16", "1'C h2 3T16", false));
        persister.add(new Achsfolg( null, "1'D 1' h2t", "1'D 1' h2t", false));
        persister.add(new Achsfolg( null, "1'E 1' h3 2'3'T28", "1'E 1' h3 2'3'T28", false));
        persister.add(new Achsfolg( null, "1'E h2 2'2T26 KAB5", "1'E h2 2'2T26 KAB5", false));
        persister.add(new Achsfolg( null, "1'E1' h2t", "1'E1' h2t", false));
        persister.add(new Achsfolg( null, "2'C 1' h3 2'2'T26", "2'C 1' h3 2'2'T26", false));
        persister.add(new Achsfolg( null, "2'C1' 2'2'T26", "2'C1' 2'2'T26", false));
        persister.add(new Achsfolg( null, "2'C1' h3 2'2'T40", "2'C1' h3 2'2'T40", false));
        persister.add(new Achsfolg( null, "A1 dm", "A1 dm", false));
        persister.add(new Achsfolg( null, "AA dm", "AA dm", false));
        persister.add(new Achsfolg( null, "B drn", "B drn", false));
        persister.add(new Achsfolg( null, "B h2t", "B h2t", false));
        persister.add(new Achsfolg( null, "B'2' dh 2'2' 2'2' 2'B' dh", "B'2' dh 2'2' 2'2' 2'B' dh", false));
        persister.add(new Achsfolg( null, "B'B' de", "B'B' de", false));
        persister.add(new Achsfolg( null, "B'B' dh", "B'B' dh", false));
        persister.add(new Achsfolg( null, "B'B'", "B'B'", false));
        persister.add(new Achsfolg( null, "Bo'2' e", "Bo'2' e", false));
        persister.add(new Achsfolg( null, "Bo'Bo'+2'2'+Bo'Bo'", "Bo'Bo'+2'2'+Bo'Bo'", false));
        persister.add(new Achsfolg( null, "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", false));
        persister.add(new Achsfolg( null, "C dh", "C dh", false));
        persister.add(new Achsfolg( null, "C h2t", "C h2t", false));
        persister.add(new Achsfolg( null, "C'C' dh", "C'C' dh", false));
        persister.add(new Achsfolg( null, "Co'Co' w6gf", "Co'Co' w6gf", false));
        persister.add(new Achsfolg( null, "Co'Co'", "Co'Co'", false));
        persister.add(new Achsfolg( null, "D h2t", "D h2t", false));
        persister.add(new Achsfolg( null, "D'D h4vt", "D'D h4vt", false));
    }
    
    protected void populateAdress() throws Exception {
    }

    protected void populateAdressTyp() throws Exception {
        IPersister<AdressTyp> persister = persisterFactory.createNamedItemPersister(AdressTyp.class);
        
        

    }

    protected void populateAntrieb() throws Exception {
        IPersister<Antrieb> persister = persisterFactory.createNamedItemPersister(Antrieb.class);

        

        persister.add(new Antrieb( null, "Akku", "Akku", false));
        persister.add(new Antrieb( null, "Dampf", "Dampf", false));
        persister.add(new Antrieb( null, "Diesel", "Diesel", false));
        persister.add(new Antrieb( null, "Elektro", "Elektro", false));
        persister.add(new Antrieb( null, "Druckluft", "Druckluft", false));
    }

    protected void populateArtikel() throws Exception {
    }
    
    protected void populateAufbau() throws Exception {
        IPersister<Aufbau> persister = persisterFactory.createNamedItemPersister(Aufbau.class);

        

        persister.add(new Aufbau( null, "Lok Kunststoff", "Fahrgestell der Lok aus Metall", false));
        persister.add(new Aufbau( null, "Lok Metall / Kunststoff", "Fahrgestell und vorwiegender Aufbau der Loks aus Metall", false));
        persister.add(new Aufbau( null, "Lok Metall", "Fahrgestell und Aufbau der Lokomotive aus Metall", false));
        persister.add(new Aufbau( null, "Wagen Kunststoff", "Fahrgestell des Wagens aus Metall", false));
        persister.add(new Aufbau( null, "Wagen Metall / Kunststoff", "Überwiegender Teil des Wagenaufbaus aus Metall", false));
        persister.add(new Aufbau( null, "Wagen Metall", "Fahrgestell und Aufbau des Wagens aus Metall", false));
        persister.add(new Aufbau( null, "Lok Kunststoff / Metall", "Überwiegender Teil des Lokaufbaues aus Metall", false));
    }

    protected void populateBahnverwaltung() throws Exception {
        IPersister<Bahnverwaltung> persister = persisterFactory.createNamedItemPersister(Bahnverwaltung.class);
        
        

    }
    
    protected void populateDecoder() throws Exception {
    }
    
    protected void populateDecoderCV() throws Exception {
    }
    
    protected void populateDecoderFunktion() throws Exception {
    }

    protected void populateDecoderTyp() throws Exception {
        IPersister<DecoderTyp> persister = persisterFactory.createNamedItemPersister(DecoderTyp.class);
    
        
    }

    protected void populateDecoderTypCV() throws Exception {
        IPersister<DecoderTypCV> persister = persisterFactory.createItemPersister(DecoderTypCV.class);
        
        
    }

    protected void populateDecoderTypFunktion() throws Exception {
        IPersister<DecoderTypFunktion> persister = persisterFactory.createItemPersister(DecoderTypFunktion.class);
        
        

    }
    
    protected void populateEpoch() throws Exception {
        IPersister<Epoch> persister = persisterFactory.createNamedItemPersister(Epoch.class);
        
        

    }

    protected void populateGattung() throws Exception {
        IPersister<Gattung> persister = persisterFactory.createNamedItemPersister(Gattung.class);
        
        

        persister.add(new Gattung( null, "AB3yge", "AB3yge", false));
        persister.add(new Gattung( null, "ADümh 101", "ADümh 101", false));
        persister.add(new Gattung( null, "Apümh 121", "Apümh 121", false));
        persister.add(new Gattung( null, "ARDümz 106", "ARDümz 106", false));
        persister.add(new Gattung( null, "Aüm 203", "Aüm 203", false));
        persister.add(new Gattung( null, "Avmz 206", "Avmz 206", false));
        persister.add(new Gattung( null, "Avümh 111", "Avümh 111", false));
        persister.add(new Gattung( null, "Avümz 111", "Avümz 111", false));
        persister.add(new Gattung( null, "B3yge", "B3yge", false));
        persister.add(new Gattung( null, "B4yge", "B4yge", false));
        persister.add(new Gattung( null, "BA 115", "BA 115", false));
        persister.add(new Gattung( null, "BD3yge", "BD3yge", false));
        persister.add(new Gattung( null, "Bi 18t", "Bi 18t", false));
        persister.add(new Gattung( null, "Bi", "Bi", false));
        persister.add(new Gattung( null, "Bockkrän", "Bockkrän", false));
        persister.add(new Gattung( null, "BR 03", "BR 03", false));
        persister.add(new Gattung( null, "BR 03.10", "BR 03.10", false));
        persister.add(new Gattung( null, "BR 10.1", "BR 10.1", false));
        persister.add(new Gattung( null, "BR 103", "BR 103", false));
        persister.add(new Gattung( null, "BR 111", "BR 111", false));
        persister.add(new Gattung( null, "BR 151", "BR 151", false));
        persister.add(new Gattung( null, "BR 211", "BR 211", false));
        persister.add(new Gattung( null, "BR 216", "BR 216", false));
        persister.add(new Gattung( null, "BR 220", "BR 220", false));
        persister.add(new Gattung( null, "BR 230", "BR 230", false));
        persister.add(new Gattung( null, "BR 24", "BR 24", false));
        persister.add(new Gattung( null, "BR 260", "BR 260", false));
        persister.add(new Gattung( null, "BR 321", "BR 321", false));
        persister.add(new Gattung( null, "BR 45", "BR 45", false));
        persister.add(new Gattung( null, "BR 50", "BR 50", false));
        persister.add(new Gattung( null, "BR 53", "BR 53", false));
        persister.add(new Gattung( null, "BR 601", "BR 601", false));
        persister.add(new Gattung( null, "BR 64", "BR 64", false));
        persister.add(new Gattung( null, "BR 701", "BR 701", false));
        persister.add(new Gattung( null, "BR 81", "BR 81", false));
        persister.add(new Gattung( null, "BR 85", "BR 85", false));
        persister.add(new Gattung( null, "BR 86", "BR 86", false));
        persister.add(new Gattung( null, "BR 89.0", "BR 89.0", false));
        persister.add(new Gattung( null, "BR 96", "BR 96", false));
        persister.add(new Gattung( null, "BR 98.3", "BR 98.3", false));
        persister.add(new Gattung( null, "BT 10", "BT 10", false));
        persister.add(new Gattung( null, "BTmm 51", "BTmm 51", false));
        persister.add(new Gattung( null, "BW Nürnberg-Gostenhof", "BW Nürnberg-Gostenhof", false));
        persister.add(new Gattung( null, "Chemnitz-Hilbersdorf ", "Chemnitz-Hilbersdorf ", false));
        persister.add(new Gattung( null, "DByg 546", "DByg 546", false));
        persister.add(new Gattung( null, "DByg 547", "DByg 547", false));
        persister.add(new Gattung( null, "DByg 548", "DByg 548", false));
        persister.add(new Gattung( null, "DHG 700C", "DHG 700C", false));
        persister.add(new Gattung( null, "ELD4", "ELD4", false));
        persister.add(new Gattung( null, "El-u 061", "El-u 061", false));
        persister.add(new Gattung( null, "ET 403", "ET 403", false));
        persister.add(new Gattung( null, "ET 91", "ET 91", false));
        persister.add(new Gattung( null, "F7", "F7", false));
        persister.add(new Gattung( null, "G 10", "G 10", false));
        persister.add(new Gattung( null, "Gl", "Gl", false));
        persister.add(new Gattung( null, "Gmhs 53", "Gmhs 53", false));
        persister.add(new Gattung( null, "Gr 20", "Gr 20", false));
        persister.add(new Gattung( null, "Gs 210", "Gs 210", false));
        persister.add(new Gattung( null, "H 10", "H 10", false));
        persister.add(new Gattung( null, "Habbiins", "Habbiins", false));
        persister.add(new Gattung( null, "Ibdlps 383", "Ibdlps 383", false));
        persister.add(new Gattung( null, "Ichqrs 377", "Ichqrs 377", false));
        persister.add(new Gattung( null, "ICR-A10", "ICR-A10", false));
        persister.add(new Gattung( null, "ICR-B10", "ICR-B10", false));
        persister.add(new Gattung( null, "Kbs 443", "Kbs 443", false));
        persister.add(new Gattung( null, "Kklm 505", "Kklm 505", false));
        persister.add(new Gattung( null, "Kolonialwarenwagen", "Kolonialwarenwagen", false));
        persister.add(new Gattung( null, "Laae 540", "Laae 540", false));
        persister.add(new Gattung( null, "Mannschaftswagen 376", "Mannschaftswagen 376", false));
        persister.add(new Gattung( null, "NS 6400", "NS 6400", false));
        persister.add(new Gattung( null, "Om „Breslau“", "Om „Breslau“", false));
        persister.add(new Gattung( null, "Om 12", "Om 12", false));
        persister.add(new Gattung( null, "OOtz 50", "OOtz 50", false));
        persister.add(new Gattung( null, "Otmm 70", "Otmm 70", false));
        persister.add(new Gattung( null, "Pw 90 HzL", "Pw 90 HzL", false));
        persister.add(new Gattung( null, "Pwg Pr 14", "Pwg Pr 14", false));
        persister.add(new Gattung( null, "Pwgs 41", "Pwgs 41", false));
        persister.add(new Gattung( null, "Pwi 28", "Pwi 28", false));
        persister.add(new Gattung( null, "Pwi Wü 13", "Pwi Wü 13", false));
        persister.add(new Gattung( null, "R 02", "R 02", false));
        persister.add(new Gattung( null, "Rlmmps 651", "Rlmmps 651", false));
        persister.add(new Gattung( null, "Rlmms 58", "Rlmms 58", false));
        persister.add(new Gattung( null, "Rlmms", "Rlmms", false));
        persister.add(new Gattung( null, "Rlmmso 56", "Rlmmso 56", false));
        persister.add(new Gattung( null, "Rs 684", "Rs 684", false));
        persister.add(new Gattung( null, "Samms 709", "Samms 709", false));
        persister.add(new Gattung( null, "Schotterwagen 166", "Schotterwagen 166", false));
        persister.add(new Gattung( null, "Sm 24", "Sm 24", false));
        persister.add(new Gattung( null, "SSH 71", "SSH 71", false));
        persister.add(new Gattung( null, "SSym „Köln“", "SSym „Köln“", false));
        persister.add(new Gattung( null, "Tehs 50", "Tehs 50", false));
        persister.add(new Gattung( null, "Tko 02", "Tko 02", false));
        persister.add(new Gattung( null, "Ucs", "Ucs", false));
        persister.add(new Gattung( null, "üm 312", "üm 312", false));
        persister.add(new Gattung( null, "üm 313", "üm 313", false));
        persister.add(new Gattung( null, "V 200", "V 200", false));
        persister.add(new Gattung( null, "V 80", "V 80", false));
        persister.add(new Gattung( null, "Viehtransport", "Viehtransport", false));
        persister.add(new Gattung( null, "VS 98", "VS 98", false));
        persister.add(new Gattung( null, "VT 75", "VT 75", false));
        persister.add(new Gattung( null, "VT 95", "VT 95", false));
        persister.add(new Gattung( null, "VT 95.9", "VT 95.9", false));
        persister.add(new Gattung( null, "VT 98", "VT 98", false));
        persister.add(new Gattung( null, "WGmh 824", "WGmh 824", false));
        persister.add(new Gattung( null, "WRmz 135", "WRmz 135", false));
        persister.add(new Gattung( null, "WRümh 131", "WRümh 131", false));
        persister.add(new Gattung( null, "X05 „Erfurt“", "X05 „Erfurt„“", false));
        persister.add(new Gattung( null, "Zces", "Zces", false));
    }

    protected void populateHersteller() throws Exception {
        IPersister<Hersteller> persister = persisterFactory.createNamedItemPersister(Hersteller.class);
        
        

        persister.add(new Hersteller(null, " Avago Technologies", null, null, null, false));
        persister.add(new Hersteller(null, "4MFOR", null, null, null, false));
        persister.add(new Hersteller(null, "Artitec", null, null, null, false));
        persister.add(new Hersteller(null, "Auhagen", null, null, null, false));
        persister.add(new Hersteller(null, "B&K", null, null, null, false));
        persister.add(new Hersteller(null, "Brawa", null, null, null, false));
        persister.add(new Hersteller(null, "Busch", null, null, null, false));
        persister.add(new Hersteller(null, "DCC Supplies", null, null, null, false));
        persister.add(new Hersteller(null, "Deluxe Materials", null, null, null, false));
        persister.add(new Hersteller(null, "Digital-Bahn", null, null, null, false));
        persister.add(new Hersteller(null, "DigiTrain", null, null, null, false));
        persister.add(new Hersteller(null, "Diotec", null, null, null, false));
        persister.add(new Hersteller(null, "Erbert", null, null, null, false));
        persister.add(new Hersteller(null, "ESU", null, null, null, false));
        persister.add(new Hersteller(null, "Evergreen", null, null, null, false));
        persister.add(new Hersteller(null, "Fairchild", null, null, null, false));
        persister.add(new Hersteller(null, "Faller", null, null, null, false));
        persister.add(new Hersteller(null, "Fleischmann", null, null, null, false));
        persister.add(new Hersteller(null, "Gassner", null, null, null, false));
        persister.add(new Hersteller(null, "GaugeMaster", null, null, null, false));
        persister.add(new Hersteller(null, "Heico", null, null, null, false));
        persister.add(new Hersteller(null, "Herkat", null, null, null, false));
        persister.add(new Hersteller(null, "Herpa", null, null, null, false));
        persister.add(new Hersteller(null, "Humbrol", null, null, null, false));
        persister.add(new Hersteller(null, "Jordan", null, null, null, false));
        persister.add(new Hersteller(null, "Kibri", null, null, null, false));
        persister.add(new Hersteller(null, "Kingbright", null, null, null, false));
        persister.add(new Hersteller(null, "KKPMO", null, null, null, false));
        persister.add(new Hersteller(null, "Knipex", null, null, null, false));
        persister.add(new Hersteller(null, "Kühn", null, null, null, false));
        persister.add(new Hersteller(null, "LDT", null, null, null, false));
        persister.add(new Hersteller(null, "Liliput", null, null, null, false));
        persister.add(new Hersteller(null, "Lima", null, null, null, false));
        persister.add(new Hersteller(null, "Littfinski", null, null, null, false));
        persister.add(new Hersteller(null, "LUX-Modellbau", null, null, null, false));
        persister.add(new Hersteller(null, "Maquett", null, null, null, false));
        persister.add(new Hersteller(null, "Märklin", null, null, null, false));
        persister.add(new Hersteller(null, "Mehano", null, null, null, false));
        persister.add(new Hersteller(null, "Merten", null, null, null, false));
        persister.add(new Hersteller(null, "Noch", null, null, null, false));
        persister.add(new Hersteller(null, "Omron", null, null, null, false));
        persister.add(new Hersteller(null, "Preiser", null, null, null, false));
        persister.add(new Hersteller(null, "Proses", null, null, null, false));
        persister.add(new Hersteller(null, "Ratio", null, null, null, false));
        persister.add(new Hersteller(null, "Red Line", null, null, null, false));
        persister.add(new Hersteller(null, "Revell", null, null, null, false));
        persister.add(new Hersteller(null, "Ricko", null, null, null, false));
        persister.add(new Hersteller(null, "Rivarossi", null, null, null, false));
        persister.add(new Hersteller(null, "Roco", null, null, null, false));
        persister.add(new Hersteller(null, "RTS", null, null, null, false));
        persister.add(new Hersteller(null, "Schuco", null, null, null, false));
        persister.add(new Hersteller(null, "Seuthe", null, null, null, false));
        persister.add(new Hersteller(null, "Taiwan Semiconductor", null, null, null, false));
        persister.add(new Hersteller(null, "Tamiya", null, null, null, false));
        persister.add(new Hersteller(null, "Tams", null, null, null, false));
        persister.add(new Hersteller(null, "Tower Pro", null, null, null, false));
        persister.add(new Hersteller(null, "Trix", null, null, null, false));
        persister.add(new Hersteller(null, "TruOpto", null, null, null, false));
        persister.add(new Hersteller(null, "Uhlenbrock", null, null, null, false));
        persister.add(new Hersteller(null, "Unbekant", null, null, null, false));
        persister.add(new Hersteller(null, "Viessmann", null, null, null, false));
        persister.add(new Hersteller(null, "Walthers", null, null, null, false));
        persister.add(new Hersteller(null, "Weinert", null, null, null, false));
        persister.add(new Hersteller(null, "Wiking", null, null, null, false));
        persister.add(new Hersteller(null, "Woodland Scenics", null, null, null, false));
        persister.add(new Hersteller(null, "Zemo", null, null, null, false));
   }

    protected void populateKategorie() throws Exception {
        IPersister<Kategorie> persister = persisterFactory.createNamedItemPersister(Kategorie.class);
        
        

        persister.add(new Kategorie(null, "Abteilwagen", "", false));
        persister.add(new Kategorie(null, "Akku", "", false));
        persister.add(new Kategorie(null, "Anker", "", false));
        persister.add(new Kategorie(null, "Ausgestaltung", "", false));
        persister.add(new Kategorie(null, "Aussichtswagen", "", false));
        persister.add(new Kategorie(null, "Autotransportwagen", "", false));
        persister.add(new Kategorie(null, "Bahndienstwagen", "", false));
        persister.add(new Kategorie(null, "Bananenwagen", "", false));
        persister.add(new Kategorie(null, "Barwagen", "", false));
        persister.add(new Kategorie(null, "Bäume", "", false));
        persister.add(new Kategorie(null, "Behältertragwagen", "", false));
        persister.add(new Kategorie(null, "Beiwagen", "", false));
        persister.add(new Kategorie(null, "Bekohlung", "Sondermodell", false));
        persister.add(new Kategorie(null, "Beleuchtung", "", false));
        persister.add(new Kategorie(null, "Beschriftigung", "", false));
        persister.add(new Kategorie(null, "Beschwerung", "", false));
        persister.add(new Kategorie(null, "Bierwagen", "", false));
        persister.add(new Kategorie(null, "Blühmen", "", false));
        persister.add(new Kategorie(null, "Bockkrän", "Sondermodell", false));
        persister.add(new Kategorie(null, "Bücher", "", false));
        persister.add(new Kategorie(null, "Büsche", "", false));
        persister.add(new Kategorie(null, "Carbid-Flaschenwagen", "", false));
        persister.add(new Kategorie(null, "Containertragwagen", "", false));
        persister.add(new Kategorie(null, "Dampf", "", false));
        persister.add(new Kategorie(null, "Decoder", "", false));
        persister.add(new Kategorie(null, "Diesel", "", false));
        persister.add(new Kategorie(null, "Doppelstockwagen", "Personenwagen", false));
        persister.add(new Kategorie(null, "Drehgestell", "", false));
        persister.add(new Kategorie(null, "Drehgestellrahmen", "", false));
        persister.add(new Kategorie(null, "Drehscheibe", "Sondermodell", false));
        persister.add(new Kategorie(null, "Drehschemelwagen", "Güterwagen", false));
        persister.add(new Kategorie(null, "Elektro", "", false));
        persister.add(new Kategorie(null, "Entstördrossel", "", false));
        persister.add(new Kategorie(null, "Ersatzteil", "", false));
        persister.add(new Kategorie(null, "Fahrradtransportwagen", "Sondermodell", false));
        persister.add(new Kategorie(null, "Fahrzeug", "", false));
        persister.add(new Kategorie(null, "Farbe", "", false));
        persister.add(new Kategorie(null, "Feder", "", false));
        persister.add(new Kategorie(null, "Feldmagnet", "", false));
        persister.add(new Kategorie(null, "Fenster", "", false));
        persister.add(new Kategorie(null, "Fenstersatz", "", false));
        persister.add(new Kategorie(null, "Figuren", "", false));
        persister.add(new Kategorie(null, "Flachwagen", "", false));
        persister.add(new Kategorie(null, "Gaswagen", "", false));
        persister.add(new Kategorie(null, "Gebaüde", "", false));
        persister.add(new Kategorie(null, "Gedeckter Güterwagen", "", false));
        persister.add(new Kategorie(null, "Gepäckwagen", "", false));
        persister.add(new Kategorie(null, "Gesellschaftswagen", "Sondermodell", false));
        persister.add(new Kategorie(null, "Gleismateriel", "", false));
        persister.add(new Kategorie(null, "Gluehlampe", "", false));
        persister.add(new Kategorie(null, "Großraumwagen", "", false));
        persister.add(new Kategorie(null, "Grundplatte ", "", false));
        persister.add(new Kategorie(null, "Güterwagen", "", false));
        persister.add(new Kategorie(null, "Güterzugbegleitwagen", "", false));
        persister.add(new Kategorie(null, "Haftreifen", "", false));
        persister.add(new Kategorie(null, "Halteplatte ", "", false));
        persister.add(new Kategorie(null, "Hecken", "", false));
        persister.add(new Kategorie(null, "Hochbordwagen", "", false));
        persister.add(new Kategorie(null, "Innenbeleuchtung", "", false));
        persister.add(new Kategorie(null, "Inneneinrichtung", "", false));
        persister.add(new Kategorie(null, "Isolierung", "", false));
        persister.add(new Kategorie(null, "Isolierung-Spritzling", "", false));
        persister.add(new Kategorie(null, "Kabel", "", false));
        persister.add(new Kategorie(null, "Kabelklemmen", "", false));
        persister.add(new Kategorie(null, "Kesselwagen", "", false));
        persister.add(new Kategorie(null, "Klappe Links", "", false));
        persister.add(new Kategorie(null, "Klappe Rechts", "", false));
        persister.add(new Kategorie(null, "Kleb", "", false));
        persister.add(new Kategorie(null, "Kohlbursten", "", false));
        persister.add(new Kategorie(null, "Kränwagen", "", false));
        persister.add(new Kategorie(null, "Kühlwagen", "", false));
        persister.add(new Kategorie(null, "Kuppelstange Links", "", false));
        persister.add(new Kategorie(null, "Kupplung", "", false));
        persister.add(new Kategorie(null, "Kupplungsdeichsel", "", false));
        persister.add(new Kategorie(null, "Kupplungshaken", "", false));
        persister.add(new Kategorie(null, "Kupplungskinematik", "", false));
        persister.add(new Kategorie(null, "Kupplungsschacht", "", false));
        persister.add(new Kategorie(null, "Kurzkupplung", "", false));
        persister.add(new Kategorie(null, "Ladegut", "", false));
        persister.add(new Kategorie(null, "Landschaftsbau", "", false));
        persister.add(new Kategorie(null, "Lautsprecher", "", false));
        persister.add(new Kategorie(null, "Leitschaufel ", "", false));
        persister.add(new Kategorie(null, "Leuchteinsatz Oben", "", false));
        persister.add(new Kategorie(null, "Lokumbausätze", "", false));
        persister.add(new Kategorie(null, "Mannschaftswagen", "", false));
        persister.add(new Kategorie(null, "Massefeder", "", false));
        persister.add(new Kategorie(null, "Messewagen", "Insider", false));
        persister.add(new Kategorie(null, "Messingblech", "", false));
        persister.add(new Kategorie(null, "Motorschild", "", false));
        persister.add(new Kategorie(null, "Mutter", "", false));
        persister.add(new Kategorie(null, "Nahverkehrswagen", "", false));
        persister.add(new Kategorie(null, "Niederbordwagen", "", false));
        persister.add(new Kategorie(null, "Niederflurwagen", "", false));
        persister.add(new Kategorie(null, "Oberleitung", "", false));
        persister.add(new Kategorie(null, "Pantograph", "", false));
        persister.add(new Kategorie(null, "Personenwagen", "", false));
        persister.add(new Kategorie(null, "Pflanzen", "", false));
        persister.add(new Kategorie(null, "Prallplatte", "", false));
        persister.add(new Kategorie(null, "Puffer", "", false));
        persister.add(new Kategorie(null, "Rad", "", false));
        persister.add(new Kategorie(null, "Relais", "", false));
        persister.add(new Kategorie(null, "Relexkupplung", "", false));
        persister.add(new Kategorie(null, "Rolldachwagen", "", false));
        persister.add(new Kategorie(null, "Rungenwagen", "", false));
        persister.add(new Kategorie(null, "Schaltsfeder", "", false));
        persister.add(new Kategorie(null, "Schiebewandwagen", "", false));
        persister.add(new Kategorie(null, "Schleifer", "", false));
        persister.add(new Kategorie(null, "Schneepflug", "", false));
        persister.add(new Kategorie(null, "Schotterwagen", "", false));
        persister.add(new Kategorie(null, "Schraube", "", false));
        persister.add(new Kategorie(null, "Schraubenkupplung", "", false));
        persister.add(new Kategorie(null, "Schüttgut-Kippwagen", "", false));
        persister.add(new Kategorie(null, "Schwerlastwagen", "", false));
        persister.add(new Kategorie(null, "Seitenentladewagen", "", false));
        persister.add(new Kategorie(null, "Senkschraube", "", false));
        persister.add(new Kategorie(null, "Set", "", false));
        persister.add(new Kategorie(null, "Signalbirne", "", false));
        persister.add(new Kategorie(null, "Signaltechnik", "", false));
        persister.add(new Kategorie(null, "Silowagen", "", false));
        persister.add(new Kategorie(null, "Sonderfahrzeug", "", false));
        persister.add(new Kategorie(null, "Sonstiges", "", false));
        persister.add(new Kategorie(null, "Speisewagen", "", false));
        persister.add(new Kategorie(null, "Stange", "", false));
        persister.add(new Kategorie(null, "Steuerungstechnik", "", false));
        persister.add(new Kategorie(null, "Steurwagen", "", false));
        persister.add(new Kategorie(null, "Stromführendekupplungen", "", false));
        persister.add(new Kategorie(null, "Stromversorgung", "", false));
        persister.add(new Kategorie(null, "Stromzuführung", "", false));
        persister.add(new Kategorie(null, "Taschenwagen", "", false));
        persister.add(new Kategorie(null, "Telex Anker", "", false));
        persister.add(new Kategorie(null, "Telex Magnet", "", false));
        persister.add(new Kategorie(null, "Telexkupplung", "", false));
        persister.add(new Kategorie(null, "Tiefladewagen", "", false));
        persister.add(new Kategorie(null, "Torpedopfannenwagen", "", false));
        persister.add(new Kategorie(null, "Traeger", "", false));
        persister.add(new Kategorie(null, "Triebwagen", "", false));
        persister.add(new Kategorie(null, "Umbauwagen", "", false));
        persister.add(new Kategorie(null, "Verschlagwagen", "", false));
        persister.add(new Kategorie(null, "Viehwagen", "", false));
        persister.add(new Kategorie(null, "Wagen", "", false));
        persister.add(new Kategorie(null, "Wagenboden", "", false));
        persister.add(new Kategorie(null, "Weichenfeder", "", false));
        persister.add(new Kategorie(null, "Weihnachtswagen", "", false));
        persister.add(new Kategorie(null, "Weinwagen", "", false));
        persister.add(new Kategorie(null, "Werkzeug", "", false));
        persister.add(new Kategorie(null, "Y Schliefer", "", false));
        persister.add(new Kategorie(null, "Zäune", "", false));
        persister.add(new Kategorie(null, "Zeichen", "", false));
        persister.add(new Kategorie(null, "Zubehör", "", false));
        persister.add(new Kategorie(null, "Zugfeder ", "", false));
        persister.add(new Kategorie(null, "Zugschlussbeleuchtung", "", false));
        persister.add(new Kategorie(null, "Zylinderschraube ", "", false));
    }

    protected void populateKupplung() throws Exception {
        IPersister<Kupplung> persister = persisterFactory.createNamedItemPersister(Kupplung.class);
    
        

    }
    
    protected void populateLand() throws Exception {
        IPersister<Land> persister = persisterFactory.createNamedItemPersister(Land.class);
        
        

    }
    
    protected void populateLicht() throws Exception {
        IPersister<Licht> persister = persisterFactory.createNamedItemPersister(Licht.class);
        
        

    }
    
    protected void populateMassstab() throws Exception {
        IPersister<Massstab> persister = persisterFactory.createNamedItemPersister(Massstab.class);
        
        

        persister.add(new Massstab(null, "0", "1:45 32 mm", false));
        persister.add(new Massstab(null, "0e", "1:45 16.5 mm", false));
        persister.add(new Massstab(null, "0i", "1:45 12 mm", false));
        persister.add(new Massstab(null, "0m", "1:45 22.5 mm", false));
        persister.add(new Massstab(null, "0p", "1:45 9 mm", false));
        persister.add(new Massstab(null, "1\"", "1:12 121 mm", false));
        persister.add(new Massstab(null, "1", "1:32 45 mm", false));
        persister.add(new Massstab(null, "1e", "1:32 22.5 mm", false));
        persister.add(new Massstab(null, "1i", "1:32 16.5 mm", false));
        persister.add(new Massstab(null, "1m", "1:32 32 mm", false));
        persister.add(new Massstab(null, "1n3", "1:32 28.6 mm", false));
        persister.add(new Massstab(null, "1p", "1:32 12 mm", false));
        persister.add(new Massstab(null, "F", "1:20.32 70.64 mm", false));
        persister.add(new Massstab(null, "Fn3", "1:20.32 45 mm", false));
        persister.add(new Massstab(null, "H0", "1:87 16.5 mm", false));
        persister.add(new Massstab(null, "H0e", "1:87 9 mm", false));
        persister.add(new Massstab(null, "H0i", "1:87 6.5 mm (H0f)", false));
        persister.add(new Massstab(null, "H0m", "1:87 12 mm", false));
        persister.add(new Massstab(null, "H0p", "1:87 4.5 mm", false));
        persister.add(new Massstab(null, "HOn2", "1:87.1 7 mm", false));
        persister.add(new Massstab(null, "II", "1:22.5 63.5 mm", false));
        persister.add(new Massstab(null, "IIe", "1:22.5 32 mm", false));
        persister.add(new Massstab(null, "Iii (NEM)", "1:22.5 22.5 mm", false));
        persister.add(new Massstab(null, "III", "1:16 89 mm", false));
        persister.add(new Massstab(null, "IIIe", "1:16 45 mm", false));
        persister.add(new Massstab(null, "IIIi (NMRA)", "1:16 32 mm (3/4\")", false));
        persister.add(new Massstab(null, "IIIm", "1:16 63.5 mm", false));
        persister.add(new Massstab(null, "IIIp", "1:16 22.5 mm", false));
        persister.add(new Massstab(null, "IIm", "1:22.5 45 mm", false));
        persister.add(new Massstab(null, "IIp", "1:22.5 16.5 mm", false));
        persister.add(new Massstab(null, "N", "1:160 9 mm", false));
        persister.add(new Massstab(null, "Ne", "1:160 4.5 mm (Nn2)", false));
        persister.add(new Massstab(null, "Nm", "1:160 6.5 mm (Nn3)", false));
        persister.add(new Massstab(null, "O", "1:48 31.75 mm", false));
        persister.add(new Massstab(null, "On2", "1:48 12.7 mm", false));
        persister.add(new Massstab(null, "On3", "1:48 19.4 mm ", false));
        persister.add(new Massstab(null, "On30", "1:48 16.5 mm", false));
        persister.add(new Massstab(null, "OO", "1:76.2 19.05 mm", false));
        persister.add(new Massstab(null, "S", "1:64 22.5 mm", false));
        persister.add(new Massstab(null, "Se", "1:64 12 mm", false));
        persister.add(new Massstab(null, "Si", "1:64 9 mm", false));
        persister.add(new Massstab(null, "Sm", "1:64 16.5 mm", false));
        persister.add(new Massstab(null, "Sn3", "1:64 14.3 mm", false));
        persister.add(new Massstab(null, "Sp", "1:64 6.5 mm", false));
        persister.add(new Massstab(null, "TT", "1:120 12 mm", false));
        persister.add(new Massstab(null, "TTe", "1:120 6.5 mm", false));
        persister.add(new Massstab(null, "TTi", "1:120 4.5 mm", false));
        persister.add(new Massstab(null, "TTm", "1:120 9 mm", false));
        persister.add(new Massstab(null, "V", "1:11 127 mm", false));
        persister.add(new Massstab(null, "Ve", "1:11 63.5 mm", false));
        persister.add(new Massstab(null, "Vi", "1:11 45 mm", false));
        persister.add(new Massstab(null, "VII", "1:8 184 mm", false));
        persister.add(new Massstab(null, "VIIe", "1:8 89 mm", false));
        persister.add(new Massstab(null, "VIIi", "1:8 63.5 mm", false));
        persister.add(new Massstab(null, "VIIm", "1:8 127 mm", false));
        persister.add(new Massstab(null, "VIIp", "1:8 45 mm", false));
        persister.add(new Massstab(null, "Vm", "1:11 89 mm", false));
        persister.add(new Massstab(null, "Vp", "1:11 32 mm", false));
        persister.add(new Massstab(null, "X", "1:5.5 260 mm", false));
        persister.add(new Massstab(null, "Xe", "1:5.5 127 mm", false));
        persister.add(new Massstab(null, "Xi", "1:5.5 89 mm", false));
        persister.add(new Massstab(null, "Xm", "1:5.5 184 mm", false));
        persister.add(new Massstab(null, "Xp", "1:5.5 63.5 mm", false));
        persister.add(new Massstab(null, "Z", "1:220 6.5 mm", false));
        persister.add(new Massstab(null, "Zm", "1:220 4.5 mm", false));
    }
    
    protected void populateMotorTyp() throws Exception {
        IPersister<MotorTyp> persister = persisterFactory.createNamedItemPersister(MotorTyp.class);
        
        

        persister.add(new MotorTyp(null, "C-Sinus", "", false));
        persister.add(new MotorTyp(null, "DCM", "", false));
        persister.add(new MotorTyp(null, "Glockenanker", "", false));
        persister.add(new MotorTyp(null, "HLM MS1-7", "", false));
        persister.add(new MotorTyp(null, "HLM MS1-8", "", false));
        persister.add(new MotorTyp(null, "HLM MS2-7", "", false));
        persister.add(new MotorTyp(null, "HLM MS2-8", "", false));
        persister.add(new MotorTyp(null, "HLM S", "", false));
        persister.add(new MotorTyp(null, "HLM", "", false));
        persister.add(new MotorTyp(null, "LFCM MS1-7", "", false));
        persister.add(new MotorTyp(null, "LFCM MS1-8", "", false));
        persister.add(new MotorTyp(null, "LFCM MS2-7", "", false));
        persister.add(new MotorTyp(null, "LFCM MS2-8", "", false));
        persister.add(new MotorTyp(null, "SFCM", "", false));
    }
    
    protected void populateProdukt() throws Exception {
    }
    
    protected void populateProduktTeil() throws Exception {
    }
    
    protected void populateProtokoll() throws Exception {
        IPersister<Protokoll> persister = persisterFactory.createNamedItemPersister(Protokoll.class);
        
        


    }
    
    protected void populateSonderModell() throws Exception {
    }
    
    protected void populateSpurweite() throws Exception {
        IPersister<Spurweite> persister = persisterFactory.createNamedItemPersister(Spurweite.class);
        
        

    }
    
    protected void populateSteuerung() throws Exception {
        IPersister<Steuerung> persister = persisterFactory.createNamedItemPersister(Steuerung.class);
    
        
    }

    protected void populateUnterKategorie() throws Exception {
        IPersister<UnterKategorie> persister = persisterFactory.createNamedItemPersister(UnterKategorie.class);
        
        

    }
    protected void populateVorbild() throws Exception {
    }
    
    protected void populateWahrung() throws Exception {
        IPersister<Wahrung> persister = persisterFactory.createNamedItemPersister(Wahrung.class);
        
        

    }

    protected void populateZug() throws Exception {
    }
    
    protected void populateZugConsist() throws Exception {
    }
    
    protected void populateZugTyp() throws Exception {
        IPersister<ZugTyp> persister = persisterFactory.createNamedItemPersister(ZugTyp.class);
        
        

    }
}