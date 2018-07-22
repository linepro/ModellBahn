package com.linepro.modellbahn.util;

import javax.inject.Inject;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.impl.Achsfolg;
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
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.impl.ZugTyp;
import com.linepro.modellbahn.model.util.Konfiguration;
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

    public void populate() {
        logger.info("Start population");

        try {
            populateAchsfolg();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateAntrieb();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateAufbau();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateBahnverwaltung();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateEpoch();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateGattung();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateHersteller();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateKategorie();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateKupplung();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateLicht();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateMassstab();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateMotorTyp();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateProtokoll();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateSonderModell();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateSpurweite();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateSteuerung();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateWahrung();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateZugTyp();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateLand();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateDecoderTyp();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateDecoderTypCV();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateDecoderTypFunktion();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {

            populateUnterKategorie();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateVorbild();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateProdukt();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateProduktTeil();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateDecoder();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateDecoderCV();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateDecoderFunktion();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateArtikel();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateZug();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            populateZugConsist();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected void populateAchsfolg() {
        IPersister<Achsfolg> persister = persisterFactory.createNamedItemPersister(Achsfolg.class);

        save(persister, new Achsfolg(null, "(1'C)D 3'2'T35", "(1'C)D 3'2'T35", false));
        save(persister, new Achsfolg(null, "1'C 1' h2t", "1'C 1' h2t", false));
        save(persister, new Achsfolg(null, "1'C h2 3T16", "1'C h2 3T16", false));
        save(persister, new Achsfolg(null, "1'D 1' h2t", "1'D 1' h2t", false));
        save(persister, new Achsfolg(null, "1'E 1' h3 2'3'T28", "1'E 1' h3 2'3'T28", false));
        save(persister, new Achsfolg(null, "1'E h2 2'2T26 KAB5", "1'E h2 2'2T26 KAB5", false));
        save(persister, new Achsfolg(null, "1'E1' h2t", "1'E1' h2t", false));
        save(persister, new Achsfolg(null, "2'C 1' h3 2'2'T26", "2'C 1' h3 2'2'T26", false));
        save(persister, new Achsfolg(null, "2'C1' 2'2'T26", "2'C1' 2'2'T26", false));
        save(persister, new Achsfolg(null, "2'C1' h3 2'2'T40", "2'C1' h3 2'2'T40", false));
        save(persister, new Achsfolg(null, "A1 dm", "A1 dm", false));
        save(persister, new Achsfolg(null, "AA dm", "AA dm", false));
        save(persister, new Achsfolg(null, "B drn", "B drn", false));
        save(persister, new Achsfolg(null, "B h2t", "B h2t", false));
        save(persister, new Achsfolg(null, "B'2' dh 2'2' 2'2' 2'B' dh", "B'2' dh 2'2' 2'2' 2'B' dh", false));
        save(persister, new Achsfolg(null, "B'B' de", "B'B' de", false));
        save(persister, new Achsfolg(null, "B'B' dh", "B'B' dh", false));
        save(persister, new Achsfolg(null, "B'B'", "B'B'", false));
        save(persister, new Achsfolg(null, "Bo'2' e", "Bo'2' e", false));
        save(persister, new Achsfolg(null, "Bo'Bo'+2'2'+Bo'Bo'", "Bo'Bo'+2'2'+Bo'Bo'", false));
        save(persister, new Achsfolg(null, "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", false));
        save(persister, new Achsfolg(null, "C dh", "C dh", false));
        save(persister, new Achsfolg(null, "C h2t", "C h2t", false));
        save(persister, new Achsfolg(null, "C'C' dh", "C'C' dh", false));
        save(persister, new Achsfolg(null, "Co'Co' w6gf", "Co'Co' w6gf", false));
        save(persister, new Achsfolg(null, "Co'Co'", "Co'Co'", false));
        save(persister, new Achsfolg(null, "D h2t", "D h2t", false));
        save(persister, new Achsfolg(null, "D'D h4vt", "D'D h4vt", false));
    }

    protected void populateAntrieb() {
        IPersister<Antrieb> persister = persisterFactory.createNamedItemPersister(Antrieb.class);

        save(persister, new Antrieb(null, "Akku", "Akku", false));
        save(persister, new Antrieb(null, "Dampf", "Dampf", false));
        save(persister, new Antrieb(null, "Diesel", "Diesel", false));
        save(persister, new Antrieb(null, "Elektro", "Elektro", false));
        save(persister, new Antrieb(null, "Druckluft", "Druckluft", false));
    }

    protected void populateArtikel() {
    }

    protected void populateAufbau() {
        IPersister<Aufbau> persister = persisterFactory.createNamedItemPersister(Aufbau.class);

        save(persister, new Aufbau(null, "Lok Kunststoff", "Fahrgestell der Lok aus Metall", false));
        save(persister, new Aufbau(null, "Lok Metall / Kunststoff", 
                "Fahrgestell und vorwiegender Aufbau der Loks aus Metall", false));
        save(persister, new Aufbau(null, "Lok Metall", "Fahrgestell und Aufbau der Lokomotive aus Metall", false));
        save(persister, new Aufbau(null, "Wagen Kunststoff", "Fahrgestell des Wagens aus Metall", false));
        save(persister, 
                new Aufbau(null, "Wagen Metall / Kunststoff", "Überwiegender Teil des Wagenaufbaus aus Metall", false));
        save(persister, new Aufbau(null, "Wagen Metall", "Fahrgestell und Aufbau des Wagens aus Metall", false));
        save(persister, 
                new Aufbau(null, "Lok Kunststoff / Metall", "Überwiegender Teil des Lokaufbaues aus Metall", false));
    }

    protected void populateBahnverwaltung() {
        IPersister<Bahnverwaltung> persister = persisterFactory.createNamedItemPersister(Bahnverwaltung.class);

        save(persister, new Bahnverwaltung(null, "AAE", "AAE", false));
        save(persister, new Bahnverwaltung(null, "ADtranz", "ABB Daimler-Benz Transportation (ADtranz)", false));
        save(persister, new Bahnverwaltung(null, "AL", "Anhaltische Leopoldsbahn (AL)", false));
        save(persister, new Bahnverwaltung(null, "ALNO", "ALNO Küchen", false));
        save(persister, new Bahnverwaltung(null, "AMTRAK", "American and Track (AMTRAK)", false));
        save(persister, new Bahnverwaltung(null, "AR", "Alaska Railroad (ARR)", false));
        save(persister, new Bahnverwaltung(null, "AT+SF", "Atchison, Topeka and Santa Fe Railway (AT+SF)", false));
        save(persister, new Bahnverwaltung(null, "AVE", "Alta Velocidad Española (AVE)", false));
        save(persister, new Bahnverwaltung(null, "AVG", "Albtal-Verkehrs-Gesellschaft mbH (AVG)", false));
        save(persister, new Bahnverwaltung(null, "Alaska", "Alaska Railroad (ARR)", false));
        save(persister, new Bahnverwaltung(null, "Alusuisse", "Schweizerische Aluminium AG (Alusuisse)", false));
        save(persister, new Bahnverwaltung(null, "B+A", "Boston and Albany Railway (B+A)", false));
        save(persister, new Bahnverwaltung(null, "B+O", "Baltomore and Ohio Railway (B+0)", false));
        save(persister, new Bahnverwaltung(null, "BBÖ", "Österreichischen Bundesbahnen (ÖBB)", false));
        save(persister, new Bahnverwaltung(null, "BHE", "Bebra-Hanauer Eisenbahn (BHE)", false));
        save(persister, new Bahnverwaltung(null, "BLS", "Bern–Lötschberg–Simplon railway (BLS)", false));
        save(persister, new Bahnverwaltung(null, "BN", "Burlington Northern Railroad (BN)", false));
        save(persister, new Bahnverwaltung(null, "BVG", "Berliner Verkehrsbetriebe (BVG)", false));
        save(persister, new Bahnverwaltung(null, "BuH", "Ruhrkohle AG Bahn und Hafen GmbH (RAG/BuH)", false));
        save(persister, new Bahnverwaltung(null, "C+G", "Columbus and Greenville Railway (C+G)", false));
        save(persister, new Bahnverwaltung(null, "CB+Q", "Chicago, Burlington and Quincy Railroad (CBQ)", false));
        save(persister, 
                new Bahnverwaltung(null, "CFL", "Société Nationale des Chemins de Fer Luxembourgeois (CFL)", false));
        save(persister, new Bahnverwaltung(null, "CFV3V", "Chemin de Fer à Vapeur des 3 Vallées (CFV3V)", false));
        save(persister, new Bahnverwaltung(null, "CIWL", "Compagnie Internationale des Wagons-Lits (CIWL)", false));
        save(persister, new Bahnverwaltung(null, "CN", "Canadian National Railway (CN)", false));
        save(persister, new Bahnverwaltung(null, "CONNEX", "CONNEX GmbH", false));
        save(persister, new Bahnverwaltung(null, "DAB", "Dortmunder Actien-Brauerei (DAB)", false));
        save(persister, new Bahnverwaltung(null, "DB AG", "Deutschen Bahn AG (DB AG)", false));
        save(persister, new Bahnverwaltung(null, "DB AutoZug", "Deutschen Bahn AG AutoZug (DB AutoZug)", false));
        save(persister, new Bahnverwaltung(null, "DB Cargo", "Deutschen Bahn AG Cargo (DB Cargo)", false));
        save(persister, new Bahnverwaltung(null, "DB Museum", "Deutschen Bundesbahn Museum (DB Museum)", false));
        save(persister, new Bahnverwaltung(null, "DB", "Deutschen Bundesbahn (DB)", false));
        save(persister, new Bahnverwaltung(null, "DB (DR)", "Deutschen Reichsbahn (DB/DR)", false));
        save(persister, new Bahnverwaltung(null, "DBP", "Deutschen Bahnpost (DBP)", false));
        save(persister, new Bahnverwaltung(null, "DL", "Dixie Line (DL)", false));
        save(persister, new Bahnverwaltung(null, "DR (DDR)", "Deutschen Reichsbahn (DDR)", false));
        save(persister, new Bahnverwaltung(null, "DR", "Deutschen Reichsbahn (DR)", false));
        save(persister, new Bahnverwaltung(null, "DRB", "Deutschen Reichsbahn (DRB)", false));
        save(persister, new Bahnverwaltung(null, "DRG GV Bay", "Deutschen Reichsbahn Gesellschaft (DRG)", false));
        save(persister, new Bahnverwaltung(null, "DRG", "Deutschen Reichsbahn Gesellschaft (DRG)", false));
        save(persister, new Bahnverwaltung(null, "DRGW", "Denver and Rio Grande Western Railroad (DRGW)", false));
        save(persister, new Bahnverwaltung(null, "DSB", "Danske Statsbaner (DSB)", false));
        save(persister, new Bahnverwaltung(null, "DSG", "Deutsche Schlafwagen und Speisewagengesellschaft (DSG)", false));
        save(persister, new Bahnverwaltung(null, "EBOE", "Elmshorn-Barmstedt-Oldesloer Eisenbahn (EBOE)", false));
        save(persister, new Bahnverwaltung(null, "ESG", "Eisenbahn-Service GmbH (ESG)", false));
        save(persister, new Bahnverwaltung(null, "EST", " Société Nationale des Chemins de Fer Français (SNCF)", false));
        save(persister, new Bahnverwaltung(null, "EVB", "Eisenbahnen und Verkehrsbetriebe Elbe-Weser GmbH (EVB)", false));
        save(persister, new Bahnverwaltung(null, "EMD", "GM Electro Motive Division (EMD)", false));
        save(persister, new Bahnverwaltung(null, "EuH", "„Eisenbahn + Häfen“", false));
        save(persister, new Bahnverwaltung(null, "FS Cargo", "Ferrovie dello Stato Italiane Cargo (FS Cargo)", false));
        save(persister, new Bahnverwaltung(null, "FS", "Ferrovie dello Stato Italiane (FS)", false));
        save(persister, new Bahnverwaltung(null, "GATX", "GATX Corporation (GATX)", false));
        save(persister, new Bahnverwaltung(null, "GBAG", "GB Netz der Deutschen Bahn AG (GBAG)", false));
        save(persister, new Bahnverwaltung(null, "G.Bad.St.E", "Großherzoglich Badische Staatseisenbahn (G.Bad.St.E)", false));
        save(persister, new Bahnverwaltung(null, "GKM", "Grosskraftwerk Mannheim AG (GKM)", false));
        save(persister, new Bahnverwaltung(null, "G.O.St.B", "Großherzoglich Oldenburgische Staatseisenbahn (G.O.St.B)", 
                false));
        save(persister, new Bahnverwaltung(null, "GSW", "Great Southwest Railroad (GSW)", false));
        save(persister, new Bahnverwaltung(null, "GVB", "Gruppenverwaltung Bayern (GVB)", false));
        save(persister, new Bahnverwaltung(null, "GhMFFE", 
                "Großherzoglich Mecklenburgische Friedrich-Franz-Eisenbahn (MFFE)", false));
        save(persister, new Bahnverwaltung(null, "HBS", "Herzoglich Braunschweigische Staatseisenbahn (HBS)", false));
        save(persister, new Bahnverwaltung(null, "HEG", "Hersfelder Eisenbahn Gesellschaft mbH (HEG)", false));
        save(persister, new Bahnverwaltung(null, "HNSt.B", "Herzoglich Nassauische Staatsbahn (HNSt.B)", false));
        save(persister, new Bahnverwaltung(null, "Hansa", "Hansabahn Dortmund", false));
        save(persister, new Bahnverwaltung(null, "Henkel", "Fa Henkel KGaA, Düsseldorf", false));
        save(persister, new Bahnverwaltung(null, "ICG", "Illonois Central Railway (ICG)", false));
        save(persister, 
                new Bahnverwaltung(null, "IGE", "Internationalen Gesellschaft für Eisenbahnverkehr (IGE)", false));
        save(persister, new Bahnverwaltung(null, "Ilmebahn", "Ilmebahn GmbH", false));
        save(persister, 
                new Bahnverwaltung(null, "K.Bay.St.E", "Königlich Bayerischen Staatseisenbahnen (K.Bay.St.E)", false));
        save(persister, 
                new Bahnverwaltung(null, "K.H.St.B", "Königlich Hannöversche Staatseisenbahnen (K.H.St.B)", false));
        save(persister, 
                new Bahnverwaltung(null, "K.P.E.V", "Königlich Preußische Eisenbahn-Verwaltung (K.P.E.V)", false));
        save(persister, new Bahnverwaltung(null, "K.P.St.E", "Königlich Preußische Staatseisenbahnen (K.P.St.E)", false));
        save(persister, new Bahnverwaltung(null, "KB", "Deutschen Bundesbahn (DB)", false));
        save(persister, new Bahnverwaltung(null, "KEG", "Karsdorfer Eisenbahngesellschaft GmbH (KEG)", false));
        save(persister, new Bahnverwaltung(null, "KH", "Kraftwerk Herne", false));
        save(persister, new Bahnverwaltung(null, "KLVM", "KLVM", false));
        save(persister, new Bahnverwaltung(null, "K.Pu.G.H.St.E", 
                "Königlich Preußische und Großherzoglich Hessischen Staatseisenbahnen (K.Pu.G.H.St.E", false));
        save(persister, new Bahnverwaltung(null, "K.Sächs.St.E", "Königlich Sächsische Staatseisenbahnen (K.Sächs.St.E)", 
                false));
        save(persister, new Bahnverwaltung(null, "K.W.St.E", "Königlich Württembergischen Staatseisenbahnen (K.W.St.E)", 
                false));
        save(persister, new Bahnverwaltung(null, "LAG", "Lokalbahn Aktien-Gesellschaft (LAG)", false));
        save(persister, new Bahnverwaltung(null, "LMS", "London, Midland and Scottish Railway (LMS)", false));
        save(persister, new Bahnverwaltung(null, "LNER", "London Northeast Railway (LNER)", false));
        save(persister, new Bahnverwaltung(null, "LSE", "Luzern–Stans–Engelberg-Bahn (LSE)", false));
        save(persister, new Bahnverwaltung(null, "MAV", "Magyar Államvasutak(MAV)", false));
        save(persister, new Bahnverwaltung(null, "MFFE", 
                "Großherzoglich Mecklenburgische Friedrich-Franz-Eisenbahn (MFFE)", false));
        save(persister, 
                new Bahnverwaltung(null, "MILW", "Chicago, Milwaukee, St Paul and Pacific Railroad (MILW)", false));
        save(persister, new Bahnverwaltung(null, "MKO", "Museumseisenbahn Küstenbahn Ostfriesland (MKO)", false));
        save(persister, new Bahnverwaltung(null, "MWB", "Mittelweserbahn GmbH (MWB)", false));
        save(persister, new Bahnverwaltung(null, "Makies AG", "Firma „Makies“ AG", false));
        save(persister, new Bahnverwaltung(null, "NB", "NordseeBahn (NB)", false));
        save(persister, new Bahnverwaltung(null, "NEG", "Norddeutsche Eisenbahngesellschaft Niebüll GmbH (NEG)", false));
        save(persister, new Bahnverwaltung(null, "NH", "New York, New Haven and Hartford Railroad (NH)", false));
        save(persister, new Bahnverwaltung(null, "NL", "P and O Nedlloyd (NL)", false));
        save(persister, new Bahnverwaltung(null, "NOHAB", "Nydqvist und Holm AB (NOHAB)", false));
        save(persister, new Bahnverwaltung(null, "NS Cargo", "Nederlandse Spoorweggen Cargo (NS Cargo)", false));
        save(persister, new Bahnverwaltung(null, "NS", "Nederlandse Spoorweggen (NS)", false));
        save(persister, new Bahnverwaltung(null, "NSB", "Norske Statsbaner (NSB)", false));
        save(persister, new Bahnverwaltung(null, "NYC", "New York Central Railroad (NYC)", false));
        save(persister, new Bahnverwaltung(null, "Nördlingen", "das Bayerische Eisenbahnmuseum (Nördlingen)", false));
        save(persister, new Bahnverwaltung(null, "ÖBB", "Österreichischen Bundesbahnen (ÖBB)", false));
        save(persister, new Bahnverwaltung(null, "ONR", "Ontario Northland Railway (ONR)", false));
        save(persister, new Bahnverwaltung(null, "On Rail", 
                "On Rail Gesellschaft für Eisenbahnausrüstung und Zubehör mbH (On Rail)", false));
        save(persister, new Bahnverwaltung(null, "Opel", "Opel AG", false));
        save(persister, new Bahnverwaltung(null, "Orbe Chav", 
                "Transport Vallée de Joux, Yverdon-les-bains et Sainte Croix (Travys)", false));
        save(persister, new Bahnverwaltung(null, "P+LE", "Pittsburgh and Lake Erie Railroad (P+LE)", false));
        save(persister, new Bahnverwaltung(null, "P.St.E", "Preußische Staatseisenbahnen (P.St.E)", false));
        save(persister, new Bahnverwaltung(null, "PEG", "Prignitzer Eisenbahn-Gesellschaft (PEG)", false));
        save(persister, new Bahnverwaltung(null, "PRR", "Pennsylvania Railroad (PRR)", false));
        save(persister, new Bahnverwaltung(null, "Persil", "Fa Henkel KGaA, Düsseldorf", false));
        save(persister, new Bahnverwaltung(null, "Pfalz.B", "Pfälzische Eisenbahnen (Pfalz.B)", false));
        save(persister, new Bahnverwaltung(null, "Privatbahn", "Privatbahn", false));
        save(persister, new Bahnverwaltung(null, "R", "Rutland Railroad (R)", false));
        save(persister, new Bahnverwaltung(null, "RAG", "Ruhrkohle AG (RAG)", false));
        save(persister, new Bahnverwaltung(null, "RCT", "Royal Corps of Transport (RCT)", false));
        save(persister, new Bahnverwaltung(null, "REL", "Reichseisenbahn Elsaß-Lothringen (REL)", false));
        save(persister, new Bahnverwaltung(null, "RENFE", "Red Nacional de Ferrocarriles Españoles (RENFE)", false));
        save(persister, new Bahnverwaltung(null, "Railbouw L", "Railbouw Leerdam Maatschappij", false));
        save(persister, new Bahnverwaltung(null, "SBB Cargo", "Schweizerischen Bundesbahnen Cargo (SBB Cargo)", false));
        save(persister, new Bahnverwaltung(null, "SBB/CFF/FFS", "Schweizerischen Bundesbahnen (SBB)", false));
        save(persister, new Bahnverwaltung(null, "SCF", "Southern Central Freight Railroad (SCF)", false));
        save(persister, new Bahnverwaltung(null, "SECO", "SECO Rail (SECO)", false));
        save(persister, new Bahnverwaltung(null, "SJ", "Statens Järnvägar (SJ)", false));
        save(persister, new Bahnverwaltung(null, "SKW", "Werkseisenbahn SKW Trostberg", false));
        save(persister, 
                new Bahnverwaltung(null, "SNCB/NMBS", "Société Nationale des Chemins de Fer Belges (SNCB)", false));
        save(persister, new Bahnverwaltung(null, "SNCF", "Société Nationale des Chemins de Fer Français (SNCF)", false));
        save(persister, new Bahnverwaltung(null, "SOB", "SüdOstBayernBahn (SOB)", false));
        save(persister, new Bahnverwaltung(null, "SOO", "Soo Line Railroad (SOO)", false));
        save(persister, new Bahnverwaltung(null, "SP", "Southern Pacific Railroad (SP)", false));
        save(persister, new Bahnverwaltung(null, "SZD", "Sovetskie železnye dorogi (SžD)", false));
        save(persister, new Bahnverwaltung(null, "Seeh.Kiel", "Seehaven Kiel", false));
        save(persister, new Bahnverwaltung(null, "Siemens", "Siemens AG", false));
        save(persister, new Bahnverwaltung(null, "SoM", "State of Maine (SoM)", false));
        save(persister, new Bahnverwaltung(null, "Stora", "Trafikaktiebolaget Grängesberg–Oxelösunds Järnvägar (Stora)", 
                false));
        save(persister, new Bahnverwaltung(null, "SüddZucker", "Südzucker AG", false));
        save(persister, new Bahnverwaltung(null, "T+N", "Texas and Northern Railway (T+N)", false));
        save(persister, new Bahnverwaltung(null, "T+P", "Texas and Pacific Railway Company (T+P)", false));
        save(persister, new Bahnverwaltung(null, "TAG", "Tegernsee-Bahn", false));
        save(persister, new Bahnverwaltung(null, "TAGAB", "Tågåkeriet i Bergslagen AB (TÅGAB)", false));
        save(persister, 
                new Bahnverwaltung(null, "TGOJ", "Trafikaktiebolaget Grängesberg–Oxelösunds Järnvägar (TGOJ)", false));
        save(persister, new Bahnverwaltung(null, "TKAB", "Tågkompaniet AB (TKAB)", false));
        save(persister, new Bahnverwaltung(null, "TMR", "Texas Mexican Railway (TM)", false));
        save(persister, new Bahnverwaltung(null, "TSO", "Travaux du Sud-Ouest (TSO)", false));
        save(persister, new Bahnverwaltung(null, "TW", "Texas Western Railroad (TW)", false));
        save(persister, new Bahnverwaltung(null, "Tegernsee", "„Tegernsee-Bahn“", false));
        save(persister, new Bahnverwaltung(null, "UEF", "Ulmer Eisenbahnfreunde (UEF)", false));
        save(persister, new Bahnverwaltung(null, "UP", "Union Pacific Railroad (UP)", false));
        save(persister, new Bahnverwaltung(null, "USTC", "United States Transport Corps (USTC)", false));
        save(persister, new Bahnverwaltung(null, "VTG", "VTG AG (VTG)", false));
        save(persister, new Bahnverwaltung(null, "VW", "Volkswagen AG (VW)", false));
        save(persister, new Bahnverwaltung(null, "WEG", "Württembergische Eisenbahngesellschaft (WEG)", false));
        save(persister, new Bahnverwaltung(null, "WLE", "Westfälische Landes-Eisenbahn GmbH (WLE)", false));
        save(persister, new Bahnverwaltung(null, "WP", "Western Pacific Railroad (WP)", false));
        save(persister, new Bahnverwaltung(null, "Wiebe", "Wiebe Gleisbau GmbH (Wiebe)", false));
        save(persister, new Bahnverwaltung(null, "ÖBB", "Österreichischen Bundesbahnen (ÖBB)", false));
        save(persister, new Bahnverwaltung(null, "ÖHB", "Österreichischen Bundesbahnen (ÖBB)", false));
    }

    protected void populateDecoder() {
    }

    protected void populateDecoderCV() {
    }

    protected void populateDecoderFunktion() {
    }

    protected void populateDecoderTyp() {
        IPersister<DecoderTyp> persister = persisterFactory.createNamedItemPersister(DecoderTyp.class);
        IPersister<Hersteller> herstellerLookup = persisterFactory.createNamedItemPersister(Hersteller.class);
        IPersister<Protokoll> protokollLookup = persisterFactory.createNamedItemPersister(Protokoll.class);

        IProtokoll delta = findByKey(protokollLookup, "DELTA");
        IProtokoll fx = findByKey(protokollLookup, "fx");
        IProtokoll mm = findByKey(protokollLookup, "MM");
        IProtokoll mfx = findByKey(protokollLookup, "mfx");
        IProtokoll weiche = findByKey(protokollLookup, "Weiche");

        IHersteller digitalbahn = findByKey(herstellerLookup, "Digital-Bahn");

        save(persister, new DecoderTyp(null, digitalbahn, weiche, "DSD2010", "Drehscheibendekoder", 16, false, Konfiguration.CV, false));

        Hersteller esu = findByKey(herstellerLookup, "ESU");

        save(persister, new DecoderTyp(null, esu, mfx, "61600", "LokPilot M4", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, mfx, "61601", "LokPilot M4 21MTC", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, mfx, "62400", "LokSound M4", 1, true, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, mfx, "62499", "LokSound M4 21MTC", 1, true, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, mm, "52620", "LokPilot FX", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, mm, "52621", "LokPilot FX 21MTC", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, weiche, "51800", "SwitchPilot", 8, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, weiche, "51802", "SwitchPilot Servo", 4, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, esu, weiche, "51820", "SwitchPilot 2", 8, false, Konfiguration.CV, false));

        Hersteller marklin = findByKey(herstellerLookup, "Märklin");

        save(persister, new DecoderTyp(null, marklin, delta, "602850", "602850", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, delta, "603999", "603999", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, delta, "6603", "Delta Modul", 1, false, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, delta, "66031", "Delta Modul mit Zusatzfunktion", 1, false, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, delta, "66032", "Delta Modul mit automatischer Systemerkennung", 1, 
                false, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, delta, "670040", "670040", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "115798", "115798", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "150436", "150436", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "219574", "219574", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "46715", "46715", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "602756", "602756", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "60760", "Hochleistungsdecoder", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "608862", "608862", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "611105", "611105", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, fx, "611754", "611754", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "103787", "103787", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "115166", "115166", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "115673", "115673", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "116836", "116836", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "123572", "123572", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "140131", "140131", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "148924", "148924", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "156787", "156787", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "162946", "162946", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "169274", "169274", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "253201", "253201", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "269706", "269706", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "39970", "39970", 1, true, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "60902", "Hochleistungselektronik", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mfx, "611077", "611077", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mm, "209394", "209394", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mm, "42973", "42973", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mm, "49940", "49940", 1, false, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, mm, "49960", "49960", 1, true, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, mm, "49961", "49961", 1, false, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, mm, "606174", "606174", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mm, "606896", "606896", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, mm, "608825", "608825", 1, false, Konfiguration.CV, false));
        save(persister, new DecoderTyp(null, marklin, weiche, "74460", "Einbau-Digital-Decoder", 1, false, Konfiguration.SWITCH, false));
        save(persister, new DecoderTyp(null, marklin, weiche, "7687", "Drehscheibendekoder", 16, false, Konfiguration.LINK, false));

        IHersteller uhlenbrock = findByKey(herstellerLookup, "Uhlenbrock");

        save(persister, new DecoderTyp(null, uhlenbrock, mm, "67900", "67900", 1, false, Konfiguration.SWITCH, false));
    }

    protected void populateDecoderTypCV() {
        IPersister<DecoderTypCV> persister = persisterFactory.createItemPersister(DecoderTypCV.class);
        IPersister<DecoderTyp> decoderTypLookup = persisterFactory.createNamedItemPersister(DecoderTyp.class);
        IPersister<Hersteller> herstellerLookup = persisterFactory.createNamedItemPersister(Hersteller.class); 
        IHersteller digitalbahn = findByKey(herstellerLookup, "Digital-Bahn"); 
        Hersteller esu = findByKey(herstellerLookup, "ESU"); 
        Hersteller marklin = findByKey(herstellerLookup, "Märklin"); 
        IHersteller uhlenbrock = findByKey(herstellerLookup, "Uhlenbrock"); 
        
        IDecoderTyp _103787 = findByKey(decoderTypLookup, marklin, "103787");

        save(persister, new DecoderTypCV(null, _103787, 1, "Adresse", 1, 80, 10, false));
        save(persister, new DecoderTypCV(null, _103787, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _103787, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _103787, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _103787, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _103787, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _115166 = findByKey(decoderTypLookup, marklin, "115166");

        save(persister, new DecoderTypCV(null, _115166, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _115166, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _115166, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _115166, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _115166, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _115166, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _115673 = findByKey(decoderTypLookup, marklin, "115673");

        save(persister, new DecoderTypCV(null, _115673, 1, "Adresse", 1, 80, 64, false));
        save(persister, new DecoderTypCV(null, _115673, 3, "Anfahrverzögerung", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _115673, 4, "Bremsverzögerung", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _115673, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _115673, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        save(persister, new DecoderTypCV(null, _115673, 63, "Lautstärke", 1, 63, 63, false));

        IDecoderTyp _115798 = findByKey(decoderTypLookup, marklin, "115798");

        save(persister, new DecoderTypCV(null, _115798, 1, "Adresse", 1, 255, null, false));
        save(persister, new DecoderTypCV(null, _115798, 2, "Anfahrverzögerung/Bremsverzögerung", 1, 31, null, false));
        save(persister, new DecoderTypCV(null, _115798, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _115798, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _115798, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _116836 = findByKey(decoderTypLookup, marklin, "116836");

        save(persister, new DecoderTypCV(null, _116836, 1, "Adresse", 1, 80, 70, false));
        save(persister, new DecoderTypCV(null, _116836, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _116836, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _116836, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _116836, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _116836, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _123572 = findByKey(decoderTypLookup, marklin, "123572");

        save(persister, new DecoderTypCV(null, _123572, 1, "Adresse", 0, 80, 42, false));
        save(persister, new DecoderTypCV(null, _123572, 3, "Anfahrverzögerung", 0, 63, 63, false));
        save(persister, new DecoderTypCV(null, _123572, 4, "Bremsverzögerung", 0, 63, 63, false));
        save(persister, new DecoderTypCV(null, _123572, 5, "Höchstgeschwindigkeit", 0, 63, 63, false));
        save(persister, new DecoderTypCV(null, _123572, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        save(persister, new DecoderTypCV(null, _123572, 63, "Lautstärke", 0, 63, 63, false));

        IDecoderTyp _140131 = findByKey(decoderTypLookup, marklin, "140131");

        save(persister, new DecoderTypCV(null, _140131, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _140131, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _140131, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _140131, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _140131, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _140131, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _148924 = findByKey(decoderTypLookup, marklin, "148924");

        save(persister, new DecoderTypCV(null, _148924, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _148924, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _148924, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _148924, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _148924, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _148924, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _148924, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _150436 = findByKey(decoderTypLookup, marklin, "150436");

        save(persister, new DecoderTypCV(null, _150436, 1, "Adresse", 1, 255, 38, false));
        save(persister, new DecoderTypCV(null, _150436, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _150436, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _150436, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _156787 = findByKey(decoderTypLookup, marklin, "156787");

        save(persister, new DecoderTypCV(null, _156787, 1, "Adresse", 1, 80, 49, false));
        save(persister, new DecoderTypCV(null, _156787, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _156787, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _156787, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _156787, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _162946 = findByKey(decoderTypLookup, marklin, "162946");

        save(persister, new DecoderTypCV(null, _162946, 1, "Adresse", 1, 80, 11, false));
        save(persister, new DecoderTypCV(null, _162946, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _162946, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _162946, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _162946, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _162946, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _169274 = findByKey(decoderTypLookup, marklin, "169274");

        save(persister, new DecoderTypCV(null, _169274, 1, "Adresse", 0, 80, 43, false));
        save(persister, new DecoderTypCV(null, _169274, 3, "Anfahrverzögerung", 0, 63, 63, false));
        save(persister, new DecoderTypCV(null, _169274, 4, "Bremsverzögerung", 0, 63, 63, false));
        save(persister, new DecoderTypCV(null, _169274, 5, "Höchstgeschwindigkeit", 0, 63, 63, false));
        save(persister, new DecoderTypCV(null, _169274, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        save(persister, new DecoderTypCV(null, _169274, 63, "Lautstärke", 0, 63, 63, false));

        IDecoderTyp _209394 = findByKey(decoderTypLookup, marklin, "209394");

        save(persister, new DecoderTypCV(null, _209394, 1, "Adresse", null, null, 54, false));
        save(persister, new DecoderTypCV(null, _209394, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _219574 = findByKey(decoderTypLookup, marklin, "219574");

        save(persister, new DecoderTypCV(null, _219574, 1, "Adresse", 1, 80, 45, false));
        save(persister, new DecoderTypCV(null, _219574, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _219574, 3, "Anfahrverzögerung", 1, 63, null, false));

        IDecoderTyp _253201 = findByKey(decoderTypLookup, marklin, "253201");

        save(persister, new DecoderTypCV(null, _253201, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _253201, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _253201, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _253201, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _253201, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _253201, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _269706 = findByKey(decoderTypLookup, marklin, "269706");

        save(persister, new DecoderTypCV(null, _269706, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _269706, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _269706, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _269706, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _269706, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _269706, 50, "Protokolle", 1, 15, 15, false));
        save(persister, new DecoderTypCV(null, _269706, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _39970 = findByKey(decoderTypLookup, marklin, "39970");

        save(persister, new DecoderTypCV(null, _39970, 1, "Adresse", 1, 80, null, false));

        IDecoderTyp _42973 = findByKey(decoderTypLookup, marklin, "42973");

        save(persister, new DecoderTypCV(null, _42973, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _42973, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _42973, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _42973, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _42973, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _42973, 63, "Lautstärke", 1, 63, 63, false));

        IDecoderTyp _46715 = findByKey(decoderTypLookup, marklin, "46715");

        save(persister, new DecoderTypCV(null, _46715, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _46715, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _49940 = findByKey(decoderTypLookup, marklin, "49940");

        save(persister, new DecoderTypCV(null, _49940, 1, "Adresse", 1, 80, 78, false));

        IDecoderTyp _49960 = findByKey(decoderTypLookup, marklin, "49960");

        save(persister, new DecoderTypCV(null, _49960, 1, "Adresse", 1, 80, null, false));

        IDecoderTyp _49961 = findByKey(decoderTypLookup, marklin, "49961");

        save(persister, new DecoderTypCV(null, _49961, 1, "Adresse", 1, 80, null, false));

        IDecoderTyp _51800 = findByKey(decoderTypLookup, esu, "51800");

        save(persister, new DecoderTypCV(null, _51800, 1, "Decoderadresse 1, LSB", 1, 63, 1, false));
        save(persister, new DecoderTypCV(null, _51800, 3, "Konfiguration Ausgang 1", 0, 64, 8, false));
        save(persister, new DecoderTypCV(null, _51800, 4, "Konfiguration Ausgang 2", 0, 64, 8, false));
        save(persister, new DecoderTypCV(null, _51800, 5, "Konfiguration Ausgang 3", 0, 64, 8, false));
        save(persister, new DecoderTypCV(null, _51800, 6, "Konfiguration Ausgang 4", 0, 64, 8, false));
        save(persister, new DecoderTypCV(null, _51800, 7, "Versionsnummer", null, null, 115, false));
        save(persister, new DecoderTypCV(null, _51800, 8, "Herstellerkennung", null, null, 151, false));
        save(persister, new DecoderTypCV(null, _51800, 9, "Decoderadresse 1, MSB", 0, 7, 0, false));
        save(persister, new DecoderTypCV(null, _51800, 28, "RailCom Konfiguration", 0, 6, 0, false));
        save(persister, new DecoderTypCV(null, _51800, 29, "Konfigurationsregister", 128, 136, 128, false));
        save(persister, new DecoderTypCV(null, _51800, 33, "Funktionsausgangsstatus", 0, 255, null, false));
        save(persister, new DecoderTypCV(null, _51800, 34, "„Zoom“-Konfiguration", 0, 15, 0, false));
        save(persister, new DecoderTypCV(null, _51800, 35, "Decoderadresse 2, LSB", 1, 63, 1, false));
        save(persister, new DecoderTypCV(null, _51800, 36, "Decoderadresse 2, MSB", 0, 8, 8, false));
        save(persister, new DecoderTypCV(null, _51800, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, 15, false));
        save(persister, new DecoderTypCV(null, _51800, 38, "Servo 1, Stellung „A“", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _51800, 39, "Servo 1, Stellung „B“", 1, 63, 40, false));
        save(persister, new DecoderTypCV(null, _51800, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, 15, false));
        save(persister, new DecoderTypCV(null, _51800, 41, "Servo 2, Stellung „A“", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _51800, 42, "Servo 2, Stellung „B“", 1, 63, 40, false));

        IDecoderTyp _51802 = findByKey(decoderTypLookup, esu, "51802");

        save(persister, new DecoderTypCV(null, _51802, 1, "Decoderadresse 1, LSB", 1, 63, 1, false));
        save(persister, new DecoderTypCV(null, _51802, 7, "Versionsnummer", null, null, 153, false));
        save(persister, new DecoderTypCV(null, _51802, 8, "Herstellerkennung", null, null, 151, false));
        save(persister, new DecoderTypCV(null, _51802, 9, "Decoderadresse 1, MSB", 0, 7, 0, false));
        save(persister, new DecoderTypCV(null, _51802, 28, "RailCom Konfiguration", 0, 6, 0, false));
        save(persister, new DecoderTypCV(null, _51802, 29, "Konfigurationsregister", 128, 136, 128, false));
        save(persister, new DecoderTypCV(null, _51802, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, 15, false));
        save(persister, new DecoderTypCV(null, _51802, 38, "Servo 1, Stellung „A“", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _51802, 39, "Servo 1, Stellung „B“", 1, 63, 40, false));
        save(persister, new DecoderTypCV(null, _51802, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, 15, false));
        save(persister, new DecoderTypCV(null, _51802, 41, "Servo 2, Stellung „A“", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _51802, 42, "Servo 2, Stellung „B“", 1, 63, 40, false));
        save(persister, new DecoderTypCV(null, _51802, 43, "Servo 3, Drehgeschwindigkeit", 1, 63, 15, false));
        save(persister, new DecoderTypCV(null, _51802, 44, "Servo 3, Stellung „A“", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _51802, 45, "Servo 3, Stellung „B“", 1, 63, 40, false));
        save(persister, new DecoderTypCV(null, _51802, 46, "Servo 4, Drehgeschwindigkeit", 1, 63, 15, false));
        save(persister, new DecoderTypCV(null, _51802, 47, "Servo 4, Stellung „A“", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _51802, 48, "Servo 4, Stellung „B“", 1, 63, 40, false));

        IDecoderTyp _51820 = findByKey(decoderTypLookup, esu, "51820");

        save(persister, new DecoderTypCV(null, _51820, 1, "Decoderadresse 1, LSB", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 3, "Konfiguration Ausgang 1", 0, 64, null, false));
        save(persister, new DecoderTypCV(null, _51820, 4, "Konfiguration Ausgang 2", 0, 64, null, false));
        save(persister, new DecoderTypCV(null, _51820, 5, "Konfiguration Ausgang 3", 0, 64, null, false));
        save(persister, new DecoderTypCV(null, _51820, 6, "Konfiguration Ausgang 4", 0, 64, null, false));
        save(persister, new DecoderTypCV(null, _51820, 7, "Versionsnummer", null, null, null, false));
        save(persister, new DecoderTypCV(null, _51820, 8, "Herstellerkennung", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _51820, 9, "Decoderadresse 1, MSB", 0, 7, null, false));
        save(persister, new DecoderTypCV(null, _51820, 28, "RailCom Konfiguration", 0, 6, null, false));
        save(persister, new DecoderTypCV(null, _51820, 29, "Konfigurationsregister", 128, 136, null, false));
        save(persister, new DecoderTypCV(null, _51820, 33, "Funktionsausgangsstatus", 0, 255, null, false));
        save(persister, new DecoderTypCV(null, _51820, 34, "„Zoom“-Konfiguration", 0, 15, null, false));
        save(persister, new DecoderTypCV(null, _51820, 35, "Decoderadresse 2, LSB", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 36, "Decoderadresse 2, MSB", 0, 8, null, false));
        save(persister, new DecoderTypCV(null, _51820, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 38, "Servo 1, Stellung „A“", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 39, "Servo 1, Stellung „B“", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 41, "Servo 2, Stellung „A“", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _51820, 42, "Servo 2, Stellung „B“", 1, 63, null, false));

        IDecoderTyp _52620 = findByKey(decoderTypLookup, esu, "52620");

        save(persister, new DecoderTypCV(null, _52620, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _52620, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _52621 = findByKey(decoderTypLookup, esu, "52621");

        save(persister, new DecoderTypCV(null, _52621, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _52621, 8, "Rückstellen auf Serienwerte", null, null, null, false));

        IDecoderTyp _602756 = findByKey(decoderTypLookup, marklin, "602756");

        save(persister, new DecoderTypCV(null, _602756, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _602756, 2, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _602850 = findByKey(decoderTypLookup, marklin, "602850");

        save(persister, new DecoderTypCV(null, _602850, 1, "Adresse", 1, 80, 11, false));

        IDecoderTyp _603999 = findByKey(decoderTypLookup, marklin, "603999");

        save(persister, new DecoderTypCV(null, _603999, 1, "Adresse", 2, 80, 54, false));

        IDecoderTyp _606174 = findByKey(decoderTypLookup, marklin, "606174");

        save(persister, new DecoderTypCV(null, _606174, 1, "Adresse", 1, 255, null, false));

        IDecoderTyp _606896 = findByKey(decoderTypLookup, marklin, "606896");

        save(persister, new DecoderTypCV(null, _606896, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _606896, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _606896, 3, "Anfahrverzögerung", 1, 63, null, false));

        IDecoderTyp _60760 = findByKey(decoderTypLookup, marklin, "60760");

        save(persister, new DecoderTypCV(null, _60760, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _60760, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, 16, false));
        save(persister, new DecoderTypCV(null, _60760, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _60760, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _608825 = findByKey(decoderTypLookup, marklin, "608825");

        save(persister, new DecoderTypCV(null, _608825, 1, "Adresse", 1, 80, 39, false));
        save(persister, new DecoderTypCV(null, _608825, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _608825, 3, "Anfahrverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _608825, 4, "Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _608825, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _608825, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _608825, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _608862 = findByKey(decoderTypLookup, marklin, "608862");

        save(persister, new DecoderTypCV(null, _608862, 1, "Adresse", 1, 80, 10, false));
        save(persister, new DecoderTypCV(null, _608862, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _608862, 3, "Anfahrverzögerung", 1, 63, null, false));

        IDecoderTyp _60902 = findByKey(decoderTypLookup, marklin, "60902");

        save(persister, new DecoderTypCV(null, _60902, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _60902, 2, "Anfahrverzögerung", 1, 63, 03, false));
        save(persister, new DecoderTypCV(null, _60902, 3, "Anfahrverzögerung", 1, 63, null, false));

        IDecoderTyp _611077 = findByKey(decoderTypLookup, marklin, "611077");

        save(persister, new DecoderTypCV(null, _611077, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _611077, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _611077, 4, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _611077, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _611077, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _611105 = findByKey(decoderTypLookup, marklin, "611105");

        save(persister, new DecoderTypCV(null, _611105, 1, "Adresse", 1, 80, 71, false));
        save(persister, new DecoderTypCV(null, _611105, 2, "Bedienung festgelegt", 0, 1, 0, false));
        save(persister, new DecoderTypCV(null, _611105, 3, "Baggerschaufel Zeitbegrenzung", 0, 1, 0, false));

        IDecoderTyp _611754 = findByKey(decoderTypLookup, marklin, "611754");

        save(persister, new DecoderTypCV(null, _611754, 1, "Adresse", 1, 80, null, false));
        save(persister, new DecoderTypCV(null, _611754, 2, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _611754, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _611754, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _61600 = findByKey(decoderTypLookup, esu, "61600");

        save(persister, new DecoderTypCV(null, _61600, 1, "Adresse", 1, 80, 03, false));
        save(persister, new DecoderTypCV(null, _61600, 2, "Anfahrverzögerung", 1, 63, 03, false));
        save(persister, new DecoderTypCV(null, _61600, 3, "Beschleunigungszeit", 1, 63, 16, false));
        save(persister, new DecoderTypCV(null, _61600, 4, "Bremsverzögerung", 1, 63, 12, false));
        save(persister, new DecoderTypCV(null, _61600, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _61600, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _61600, 53, "Regelungsreferenz", 1, 63, 45, false));
        save(persister, new DecoderTypCV(null, _61600, 54, "Lastregelung Param. K", 1, 63, 32, false));
        save(persister, new DecoderTypCV(null, _61600, 55, "Lastregelung Param. L", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _61600, 56, "Regelungseinfluss", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _61600, 73, "Speicheroptionen", 0, 7, 03, false));
        save(persister, new DecoderTypCV(null, _61600, 75, "Märklin Addresse 2", 1, 80, 04, false));
        save(persister, new DecoderTypCV(null, _61600, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        save(persister, new DecoderTypCV(null, _61600, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        IDecoderTyp _62400 = findByKey(decoderTypLookup, esu, "62400");

        save(persister, new DecoderTypCV(null, _62400, 1, "Adresse", 1, 80, 03, false));
        save(persister, new DecoderTypCV(null, _62400, 2, "Anfahrverzögerung", 1, 63, 03, false));
        save(persister, new DecoderTypCV(null, _62400, 3, "Beschleunigungszeit", 1, 63, 16, false));
        save(persister, new DecoderTypCV(null, _62400, 4, "Bremsverzögerung", 1, 63, 12, false));
        save(persister, new DecoderTypCV(null, _62400, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _62400, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _62400, 53, "Regelungsreferenz", 1, 63, 56, false));
        save(persister, new DecoderTypCV(null, _62400, 54, "Lastregelung Param. K", 1, 63, 32, false));
        save(persister, new DecoderTypCV(null, _62400, 55, "Lastregelung Param. L", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _62400, 56, "Regelungseinfluss", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _62400, 57, "Geräuschmodus 1", 1, 63, 10, false));
        save(persister, new DecoderTypCV(null, _62400, 58, "Geräuschmodus 2", 1, 63, 58, false));
        save(persister, new DecoderTypCV(null, _62400, 59, "Fahrgeräusch", 1, 63, 32, false));
        save(persister, new DecoderTypCV(null, _62400, 60, "Fahrgeräusch", 1, 63, 55, false));
        save(persister, new DecoderTypCV(null, _62400, 63, "Geräuschlautstärke", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _62400, 64, "Bremssoundschwelle", 1, 63, 07, false));
        save(persister, new DecoderTypCV(null, _62400, 73, "Speicheroptionen", 0, 7, 03, false));
        save(persister, new DecoderTypCV(null, _62400, 74, "Märklin Addresse 2", 1, 63, null, false));
        save(persister, new DecoderTypCV(null, _62400, 75, "Märklin Addresse 2", 1, 80, 04, false));
        save(persister, new DecoderTypCV(null, _62400, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        save(persister, new DecoderTypCV(null, _62400, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        IDecoderTyp _62499 = findByKey(decoderTypLookup, esu, "62499");

        save(persister, new DecoderTypCV(null, _62499, 1, "Adresse", 1, 80, 03, false));
        save(persister, new DecoderTypCV(null, _62499, 2, "Anfahrverzögerung", 1, 63, 03, false));
        save(persister, new DecoderTypCV(null, _62499, 3, "Beschleunigungszeit", 1, 63, 16, false));
        save(persister, new DecoderTypCV(null, _62499, 4, "Bremsverzögerung", 1, 63, 12, false));
        save(persister, new DecoderTypCV(null, _62499, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _62499, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        save(persister, new DecoderTypCV(null, _62499, 53, "Regelungsreferenz", 1, 63, 56, false));
        save(persister, new DecoderTypCV(null, _62499, 54, "Lastregelung Param. K", 1, 63, 32, false));
        save(persister, new DecoderTypCV(null, _62499, 55, "Lastregelung Param. L", 1, 63, 24, false));
        save(persister, new DecoderTypCV(null, _62499, 56, "Regelungseinfluss", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _62499, 57, "Geräuschmodus 1", 1, 63, 10, false));
        save(persister, new DecoderTypCV(null, _62499, 58, "Geräuschmodus 2", 1, 63, 58, false));
        save(persister, new DecoderTypCV(null, _62499, 59, "Fahrgeräusch", 1, 63, 32, false));
        save(persister, new DecoderTypCV(null, _62499, 60, "Fahrgeräusch", 1, 63, 55, false));
        save(persister, new DecoderTypCV(null, _62499, 63, "Geräuschlautstärke", 1, 63, 63, false));
        save(persister, new DecoderTypCV(null, _62499, 64, "Bremssoundschwelle", 1, 63, 07, false));
        save(persister, new DecoderTypCV(null, _62499, 73, "Speicheroptionen", 0, 7, 03, false));
        save(persister, new DecoderTypCV(null, _62499, 75, "Märklin Addresse 2", 1, 80, 04, false));
        save(persister, new DecoderTypCV(null, _62499, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        save(persister, new DecoderTypCV(null, _62499, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        IDecoderTyp _6603 = findByKey(decoderTypLookup, marklin, "6603");

        save(persister, new DecoderTypCV(null, _6603, 1, "Adresse", 2, 80, null, false));

        IDecoderTyp _66031 = findByKey(decoderTypLookup, marklin, "66031");

        save(persister, new DecoderTypCV(null, _66031, 1, "Adresse", 2, 80, null, false));

        IDecoderTyp _66032 = findByKey(decoderTypLookup, marklin, "66032");

        save(persister, new DecoderTypCV(null, _66032, 1, "Adresse", 2, 80, null, false));

        IDecoderTyp _670040 = findByKey(decoderTypLookup, marklin, "670040");

        save(persister, new DecoderTypCV(null, _670040, 1, "Adresse", 2, 80, 54, false));

        IDecoderTyp _67900 = findByKey(decoderTypLookup, uhlenbrock, "67900");

        save(persister, new DecoderTypCV(null, _67900, 1, "Adresse", 1, 127, 3, false));
        save(persister, new DecoderTypCV(null, _67900, 2, "Minimale Geschwindigkeit", 1, 63, 5, false));
        save(persister, new DecoderTypCV(null, _67900, 3, "Anfahrverzögerung", 1, 63, 2, false));
        save(persister, new DecoderTypCV(null, _67900, 4, "Bremsverzögerung", 1, 63, 2, false));
        save(persister, new DecoderTypCV(null, _67900, 5, "Maximale Geschwindigkeit", 1, 63, 20, false));
        save(persister, new DecoderTypCV(null, _67900, 6, "Maximale Motorspannung", 1, 255, 64, false));
        save(persister, new DecoderTypCV(null, _67900, 7, "Softwareversion", null, null, null, false));
        save(persister, new DecoderTypCV(null, _67900, 8, "Herstellerkennung", null, null, 85, false));
        save(persister, new DecoderTypCV(null, _67900, 17, "Lange Lokadresse MSB", 192, 231, 199, false));
        save(persister, new DecoderTypCV(null, _67900, 18, "Lange Lokadresse LSB", 0, 255, 208, false));
        save(persister, 
                new DecoderTypCV(null, _67900, 20, "Konfiguration beider Motoren nach DCC-Norm", 0, 33, 0, false));
        save(persister, new DecoderTypCV(null, _67900, 49, "Decoder-Konfiguration", 0, 195, 0, false));
        save(persister, new DecoderTypCV(null, _67900, 65, "Motorola Programmierung Offset", 0, 255, 0, false));
        save(persister, new DecoderTypCV(null, _67900, 67, "Maximale Geschwindigkeit für Taste 1", 0, 255, 40, false));
        save(persister, new DecoderTypCV(null, _67900, 68, "Maximale Geschwindigkeit für Taste 2", 0, 255, 40, false));
        save(persister, new DecoderTypCV(null, _67900, 69, "Maximale Geschwindigkeit für Taste 3", 0, 255, 50, false));
        save(persister, new DecoderTypCV(null, _67900, 70, "Maximale Geschwindigkeit für Taste 4", 0, 255, 50, false));
        save(persister, new DecoderTypCV(null, _67900, 71, "Anfahrverzögerung für Taste 1", 0, 255, 5, false));
        save(persister, new DecoderTypCV(null, _67900, 72, "Anfahrverzögerung für Taste 2", 0, 255, 5, false));
        save(persister, new DecoderTypCV(null, _67900, 73, "Anfahrverzögerung für Taste 3", 0, 255, 5, false));
        save(persister, new DecoderTypCV(null, _67900, 74, "Anfahrverzögerung für Taste 4", 0, 255, 5, false));
        save(persister, new DecoderTypCV(null, _67900, 75, "Bremsverzögerung für Taste 1", 0, 255, 1, false));
        save(persister, new DecoderTypCV(null, _67900, 76, "Bremsverzögerung für Taste 2", 0, 255, 1, false));
        save(persister, new DecoderTypCV(null, _67900, 77, "Bremsverzögerung für Taste 3", 0, 255, 1, false));
        save(persister, new DecoderTypCV(null, _67900, 78, "Bremsverzögerung für Taste 4", 0, 255, 1, false));
        save(persister, 
                new DecoderTypCV(null, _67900, 79, "Maximale Motorspannung im Analogbetrieb", 0, 255, 180, false));
        save(persister, 
                new DecoderTypCV(null, _67900, 98, "Zeitbegrenztes Einschalten der Ausgänge A1 + A2", 0, 3, 3, false));
        save(persister, new DecoderTypCV(null, _67900, 99, "Maximale Einschaltzeit in Sekunden", 0, 255, 45, false));

        IDecoderTyp _74460 = findByKey(decoderTypLookup, marklin, "74460");

        save(persister, new DecoderTypCV(null, _74460, 1, "Adresse", 1, 255, null, false));

        IDecoderTyp _7687 = findByKey(decoderTypLookup, marklin, "7687");

        save(persister, new DecoderTypCV(null, _7687, 1, "Adresse", 14, 15, 14, false));

        IDecoderTyp _DSD2010 = findByKey(decoderTypLookup, digitalbahn, "DSD2010");

        save(persister, new DecoderTypCV(null, _DSD2010, 1, "48 / 24 Positions", 0, 1, 1, false));
        save(persister, new DecoderTypCV(null, _DSD2010, 2, "DCC / Motorola", 0, 1, 1, false));
        save(persister, new DecoderTypCV(null, _DSD2010, 3, "Position specification", 0, 1, 0, false));
    }

    protected void populateDecoderTypFunktion() {
        IPersister<DecoderTyp> decoderTypLookup = persisterFactory.createNamedItemPersister(DecoderTyp.class);
        IPersister<DecoderTypFunktion> persister = persisterFactory.createItemPersister(DecoderTypFunktion.class);
        IPersister<Hersteller> herstellerLookup = persisterFactory.createNamedItemPersister(Hersteller.class); 
        IHersteller digitalbahn = findByKey(herstellerLookup, "Digital-Bahn"); 
        Hersteller esu = findByKey(herstellerLookup, "ESU"); 
        Hersteller marklin = findByKey(herstellerLookup, "Märklin"); 
        IHersteller uhlenbrock = findByKey(herstellerLookup, "Uhlenbrock"); 
        
        IDecoderTyp _103787 = findByKey(decoderTypLookup, marklin, "103787"); 

        save(persister, new DecoderTypFunktion(null, _103787, 0, "F0", "Spitzensignal", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F1", "Rauchgenerator", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F2", "Lokpfeife", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F3", "Fahrgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F5", "Luftpumpe", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F6", "Triebwerksbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F7", "Bremsenquietschen aus", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F8", "Rangierpfeife", false, false));
        save(persister, new DecoderTypFunktion(null, _103787, 0, "F9", "Dampf ablassen", false, false));

        IDecoderTyp _115166 = findByKey(decoderTypLookup, marklin, "115166"); 

        save(persister, new DecoderTypFunktion(null, _115166, 0, "F0", "Strinbeleuchtung / Innenbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F1", "Schlusslicht ausschalten", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F2", "Dieselmotor und Bremse", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F3", "Signalhorn", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F5", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F6", "Türe Zu", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F7", "Glocke", false, false));
        save(persister, new DecoderTypFunktion(null, _115166, 0, "F8", "Abfahrtspfiff", false, false));
        
        IDecoderTyp _115673 = findByKey(decoderTypLookup, marklin, "115673"); 

        save(persister, new DecoderTypFunktion(null, _115673, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F1", "Rauchgenerator", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F2", "Betriebsgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F3", "Pfeife", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F5", "Luftpumpe", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F6", "Kohleschaufeln", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F7", "Glocke", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F8", "Dampf ablassen", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F9", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _115673, 0, "F10", "Schüttelrost", false, false));
        
        IDecoderTyp _115798 = findByKey(decoderTypLookup, marklin, "115798"); 

        IDecoderTyp _116836 = findByKey(decoderTypLookup, marklin, "116836"); 

        save(persister, new DecoderTypFunktion(null, _116836, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F2", "Betriebsgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F3", "Signalhorn", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F5", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F6", "Metallsäge", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F7", "Hämmern", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F8", "Winkelschleifer", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F9", "Elektroschweißen", false, false));
        save(persister, new DecoderTypFunktion(null, _116836, 0, "F10", "Schleifbock", false, false));
        
        IDecoderTyp _123572 = findByKey(decoderTypLookup, marklin, "123572"); 

        save(persister, new DecoderTypFunktion(null, _123572, 0, "F0", "Strinbeleuchtung / Innenbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _123572, 0, "F2", "Bahnhofsansage", false, false));
        save(persister, new DecoderTypFunktion(null, _123572, 0, "F3", "Signalhorn", false, false));
        save(persister, new DecoderTypFunktion(null, _123572, 0, "F4", "ABV", false, false));
        
        IDecoderTyp _140131 = findByKey(decoderTypLookup, marklin, "140131"); 

        save(persister, new DecoderTypFunktion(null, _140131, 0, "F0", "Strinbeleuchtung / Schlusslicht", false, false));
        save(persister, new DecoderTypFunktion(null, _140131, 0, "F1", "Schlusslicht ausschalten", false, false));
        save(persister, new DecoderTypFunktion(null, _140131, 0, "F2", "Betriebsgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _140131, 0, "F3", "Signalhorn", false, false));
        save(persister, new DecoderTypFunktion(null, _140131, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _140131, 0, "F5", "Druckluft ablassen", false, false));
        save(persister, new DecoderTypFunktion(null, _140131, 0, "F6", "Bremsenquietschen", false, false));
        
        IDecoderTyp _148924 = findByKey(decoderTypLookup, marklin, "148924"); 

        save(persister, new DecoderTypFunktion(null, _148924, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F1", "Schlusslicht ausschalten", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F2", "Betriebsgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F3", "Horn", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F5", "Druckluft ablassen", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F6", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F7", "Führerstandsbeleuchtung vorn", false, false));
        save(persister, new DecoderTypFunktion(null, _148924, 0, "F8", "Führerstandsbeleuchtung hinten", false, false));
        
        IDecoderTyp _150436 = findByKey(decoderTypLookup, marklin, "150436"); 

        save(persister, new DecoderTypFunktion(null, _150436, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _150436, 0, "F4", "ABV", false, false));
        
        IDecoderTyp _156787 = findByKey(decoderTypLookup, marklin, "156787"); 

        save(persister, new DecoderTypFunktion(null, _156787, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _156787, 0, "F1", "Innenbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _156787, 0, "F2", "Begrüßungs-Ansage", false, false));
        save(persister, new DecoderTypFunktion(null, _156787, 0, "F3", "Pfeife", false, false));
        save(persister, new DecoderTypFunktion(null, _156787, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _156787, 0, "F5", "Innenbeleuchtung dimmen", false, false));
        
        IDecoderTyp _162946 = findByKey(decoderTypLookup, marklin, "162946"); 

        save(persister, new DecoderTypFunktion(null, _162946, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F1", "Innenbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F2", "Betriebsgeräusch ", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F3", "Horn", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F5", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F6", "Bahnhofsansage", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F7", "Türe zu", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F8", "Schaffnerpfiff", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F9", "Hilfsdiesel", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F10", "Lüfter", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F11", "Kompressor", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F12", "Überdruckventil", false, false));
        save(persister, new DecoderTypFunktion(null, _162946, 0, "F13", "Druckluft ablassen", false, false));
        
        IDecoderTyp _169274 = findByKey(decoderTypLookup, marklin, "169274"); 

        save(persister, new DecoderTypFunktion(null, _169274, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F1", "Tischlampen", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F2", "Betriebsgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F3", "Horn", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F5", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F6", "Schaffnerpfiff", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F7", "Türen", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F8", "Bahnhofsansage", false, false));
        save(persister, new DecoderTypFunktion(null, _169274, 0, "F9", "Rangierpfiff", false, false));
        
        IDecoderTyp _209394 = findByKey(decoderTypLookup, marklin, "209394"); 

        save(persister, new DecoderTypFunktion(null, _209394, 0, "F0", "Strinbeleuchtung", false, false));
        
        IDecoderTyp _219574 = findByKey(decoderTypLookup, marklin, "219574"); 

        save(persister, new DecoderTypFunktion(null, _219574, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F1", "Rauchgenerator", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F11", "Schüttelrost", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F2", "Lokpfeife", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F3", "Dampftriebwerk", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F4", "ABV", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F5", "Luftpumpe", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F6", "Feuerschein - Feuerbüchse", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F7", "Bremsenquietschen", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F8", "Rangierpfiff", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F9", "Dampf ablassen", false, false));
        save(persister, new DecoderTypFunktion(null, _219574, 0, "F10", "Kohleschaufeln", false, false));

        IDecoderTyp _253201 = findByKey(decoderTypLookup, marklin, "253201"); 

        save(persister, new DecoderTypFunktion(null, _253201, 0, "F0", "Innenbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F1", "Start / Stopp", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F2", "Pause", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F3", "ein Lied vor", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F4", "ein Lied zurück", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F5", "Lauter", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F6", "Leiser", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F7", "Lichtorgel an / aus", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F8", "Barbeleuchtung an / aus", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F9", "Strom führende Kupplung", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F10", "Stroboskop", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F11", "Umgebungsgeräusch 1", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F12", "Umgebungsgeräusch 2", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F13", "Betriebsgeräusch 1", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F14", "Betriebsgeräusch 2", false, false));
        save(persister, new DecoderTypFunktion(null, _253201, 0, "F15", "Betriebsgeräusch 3", false, false));

        IDecoderTyp _269706 = findByKey(decoderTypLookup, marklin, "269706"); 

        save(persister, new DecoderTypFunktion(null, _269706, 0, "F0", "Spitzensignal", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F1", "Innenbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F2", "Fahrgeräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F3", "Signalton", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F4", "Direktsteuerung", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F5", "Bremsenquietschen aus", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F6", "Spitzensignal Lokseite 2", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F7", "Schaffnerpfiff", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F8", "Spitzensignal Lokseite 1", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F9", "Türenschließen", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F10", "Schienenstoß", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F11", "Bahnhofsansage", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F12", "Dialog", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F13", "Dialog", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F14", "Dialog", false, false));
        save(persister, new DecoderTypFunktion(null, _269706, 0, "F15", "Dialog ", false, false));

        IDecoderTyp _39970 = findByKey(decoderTypLookup, marklin, "39970"); 

        save(persister, new DecoderTypFunktion(null, _39970, 0, "F1", "Arbeitsbühne heben", false, false));
        save(persister, new DecoderTypFunktion(null, _39970, 0, "F2", "Arbeitsbühne schwenken", false, false));
        save(persister, new DecoderTypFunktion(null, _39970, 0, "F3", "Stromabnehmer", false, false));
        save(persister, new DecoderTypFunktion(null, _39970, 0, "F4", "Initialisierung", false, false));
        
        IDecoderTyp _42973 = findByKey(decoderTypLookup, marklin, "42973"); 

        save(persister, new DecoderTypFunktion(null, _42973, 0, "F2", "Pantograf", false, false));
        save(persister, new DecoderTypFunktion(null, _42973, 0, "F3", "Geräusch einer Schaffner", false, false));

        IDecoderTyp _46715 = findByKey(decoderTypLookup, marklin, "46715"); 

        save(persister, new DecoderTypFunktion(null, _46715, 0, "F1", "Kranhaus drehen", false, false));
        save(persister, new DecoderTypFunktion(null, _46715, 0, "F2", "Kranausleger Heben heben", false, false));
        save(persister, new DecoderTypFunktion(null, _46715, 0, "F3", "Haken heben", false, false));

        IDecoderTyp _49940 = findByKey(decoderTypLookup, marklin, "49940"); 

        save(persister, new DecoderTypFunktion(null, _49940, 0, "F1", "Kamera", false, false));

        IDecoderTyp _49960 = findByKey(decoderTypLookup, marklin, "49960"); 

        save(persister, new DecoderTypFunktion(null, _49960, 0, "F1", "Meßbereich", false, false));
        save(persister, new DecoderTypFunktion(null, _49960, 0, "F2", "Meßbereich", false, false));
        save(persister, new DecoderTypFunktion(null, _49960, 0, "F3", "Maßeinheit", false, false));
        save(persister, new DecoderTypFunktion(null, _49960, 0, "F4", "Anzeigen", false, false));

        IDecoderTyp _49961 = findByKey(decoderTypLookup, marklin, "49961"); 

        IDecoderTyp _51800 = findByKey(decoderTypLookup, esu, "51800"); 
        
        save(persister, new DecoderTypFunktion(null, _51800, 0, "K1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51800, 0, "K2", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51800, 0, "K3", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51800, 0, "K4", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51800, 0, "S1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51800, 0, "S2", "", false, false));
        
        IDecoderTyp _51802 = findByKey(decoderTypLookup, esu, "51802"); 

        save(persister, new DecoderTypFunktion(null, _51802, 0, "S1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51802, 0, "S2", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51802, 0, "S3", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51802, 0, "S4", "", false, false));
        
        IDecoderTyp _51820 = findByKey(decoderTypLookup, esu, "51820"); 

        save(persister, new DecoderTypFunktion(null, _51820, 0, "K1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51820, 0, "K2", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51820, 0, "K3", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51820, 0, "K4", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51820, 0, "S1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _51820, 0, "S2", "", false, false));
        
        IDecoderTyp _52620 = findByKey(decoderTypLookup, esu, "52620"); 

        save(persister, new DecoderTypFunktion(null, _52620, 0, "F0", "", false, false));
        save(persister, new DecoderTypFunktion(null, _52620, 0, "F1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _52620, 0, "F2", "", false, false));
        save(persister, new DecoderTypFunktion(null, _52620, 0, "F3", "", false, false));
        save(persister, new DecoderTypFunktion(null, _52620, 0, "F4", "", false, false));

        IDecoderTyp _52621 = findByKey(decoderTypLookup, esu, "52621"); 

        save(persister, new DecoderTypFunktion(null, _52621, 0, "F0", "", false, false));
        
        IDecoderTyp _602756 = findByKey(decoderTypLookup, marklin, "602756"); 

        save(persister, new DecoderTypFunktion(null, _602756, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _602756, 0, "F1", "Schleuderrad Geräusch", false, false));
        save(persister, new DecoderTypFunktion(null, _602756, 0, "F2", "Schleuderrad", false, false));
        save(persister, new DecoderTypFunktion(null, _602756, 0, "F3", "Pfeife", false, false));
        save(persister, new DecoderTypFunktion(null, _602756, 0, "F4", "Signalstreckenlampen", false, false));
        
        IDecoderTyp _602850 = findByKey(decoderTypLookup, marklin, "602850"); 

        save(persister, new DecoderTypFunktion(null, _602850, 0, "F0", "Strinbeleuchtung", false, false));
        
        IDecoderTyp _603999 = findByKey(decoderTypLookup, marklin, "603999"); 

        save(persister, new DecoderTypFunktion(null, _603999, 0, "F0", "Strinbeleuchtung", false, false));
        
        IDecoderTyp _606174 = findByKey(decoderTypLookup, marklin, "606174"); 

        save(persister, new DecoderTypFunktion(null, _606174, 0, "F0", "", false, false));
        
        IDecoderTyp _606896 = findByKey(decoderTypLookup, marklin, "606896"); 

        save(persister, new DecoderTypFunktion(null, _606896, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _606896, 0, "F4", "ABV", false, false));
        
        IDecoderTyp _60760 = findByKey(decoderTypLookup, marklin, "60760"); 

        save(persister, new DecoderTypFunktion(null, _60760, 0, "F0", "", false, false));
        
        IDecoderTyp _608825 = findByKey(decoderTypLookup, marklin, "608825"); 

        IDecoderTyp _608862 = findByKey(decoderTypLookup, marklin, "608862"); 

        save(persister, new DecoderTypFunktion(null, _608862, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _608862, 0, "F1", "Maschinenraumbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _608862, 0, "F2", "Stromabnehmer vorn", false, false));
        save(persister, new DecoderTypFunktion(null, _608862, 0, "F3", "Stromabnehmer hinten", false, false));
        save(persister, new DecoderTypFunktion(null, _608862, 0, "F4", "ABV", false, false));
        
        IDecoderTyp _60902 = findByKey(decoderTypLookup, marklin, "60902"); 

        IDecoderTyp _611077 = findByKey(decoderTypLookup, marklin, "611077"); 

        save(persister, new DecoderTypFunktion(null, _611077, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _611077, 0, "F4", "ABV", false, false));
        
        IDecoderTyp _611105 = findByKey(decoderTypLookup, marklin, "611105"); 

        save(persister, new DecoderTypFunktion(null, _611105, 0, "F0", "Kohleschaufe schließen", false, false));
        save(persister, new DecoderTypFunktion(null, _611105, 0, "F1", "Laufgestell Motoren", false, false));
        save(persister, new DecoderTypFunktion(null, _611105, 0, "F2", "Führerhaus Beleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _611105, 0, "F3", "Kohleschaufe Heben", false, false));
        save(persister, new DecoderTypFunktion(null, _611105, 0, "F4", "Führerhaus Drehen", false, false));
        
        IDecoderTyp _611754 = findByKey(decoderTypLookup, marklin, "611754"); 

        save(persister, new DecoderTypFunktion(null, _611754, 0, "F0", "Strinbeleuchtung", false, false));
        save(persister, new DecoderTypFunktion(null, _611754, 0, "F2", "Telex-Kupplung", false, false));
        save(persister, new DecoderTypFunktion(null, _611754, 0, "F4", "ABV", false, false));
        
        IDecoderTyp _61600 = findByKey(decoderTypLookup, esu, "61600"); 

        save(persister, new DecoderTypFunktion(null, _61600, 0, "F0", "", false, false));
        
        IDecoderTyp _62400 = findByKey(decoderTypLookup, esu, "62400"); 

        save(persister, new DecoderTypFunktion(null, _62400, 0, "F0", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F2", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F3", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F4", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F5", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F6", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F7", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F8", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F9", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F10", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F11", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F12", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F13", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F14", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62400, 0, "F15", "", false, false));
        
        IDecoderTyp _62499 = findByKey(decoderTypLookup, esu, "62499"); 

        save(persister, new DecoderTypFunktion(null, _62499, 0, "F0", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F1", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F2", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F3", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F4", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F5", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F6", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F7", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F8", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F9", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F10", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F11", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F12", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F13", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F14", "", false, false));
        save(persister, new DecoderTypFunktion(null, _62499, 0, "F15", "", false, false));

        IDecoderTyp _6603 = findByKey(decoderTypLookup, marklin, "6603"); 

        save(persister, new DecoderTypFunktion(null, _6603, 0, "F0", "", false, false));
        
        IDecoderTyp _66031 = findByKey(decoderTypLookup, marklin, "66031"); 

        save(persister, new DecoderTypFunktion(null, _66031, 0, "F0", "", false, false));
        
        IDecoderTyp _66032 = findByKey(decoderTypLookup, marklin, "66032"); 
        
        save(persister, new DecoderTypFunktion(null, _66032, 0, "F0", "", false, false));

        IDecoderTyp _670040 = findByKey(decoderTypLookup, marklin, "670040"); 

        save(persister, new DecoderTypFunktion(null, _670040, 0, "F0", "", false, false));
        
        IDecoderTyp _67900 = findByKey(decoderTypLookup, uhlenbrock, "67900"); 

        save(persister, new DecoderTypFunktion(null, _67900, 0, "F0", "Kranhaken/Laufkatze", false, false));
        save(persister, new DecoderTypFunktion(null, _67900, 0, "F1", "F1", false, false));
        save(persister, new DecoderTypFunktion(null, _67900, 0, "F2", "F2", false, false));
        save(persister, new DecoderTypFunktion(null, _67900, 0, "F3", "F3", false, false));
        save(persister, new DecoderTypFunktion(null, _67900, 0, "F4", "F4", false, false));

        IDecoderTyp _74460 = findByKey(decoderTypLookup, marklin, "74460"); 

        save(persister, new DecoderTypFunktion(null, _74460, 0, "F0", "", false, false));
        
        IDecoderTyp _7687 = findByKey(decoderTypLookup, marklin, "7687"); 

        save(persister, new DecoderTypFunktion(null, _7687, 0, "F0", "End", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F1", "Input", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F2", "Clear", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F3", "180", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F4", "Step >", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F5", "Step <", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F6", "Spoke 1", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F7", "Spoke 2", false, false));
        save(persister, new DecoderTypFunktion(null, _7687, 0, "F8", "Spoke 3", false, false));
        
        IDecoderTyp _DSD2010 = findByKey(decoderTypLookup, digitalbahn, "DSD2010"); 

        save(persister, new DecoderTypFunktion(null, _DSD2010, 0, "F0", "", false, false));
    }

    protected void populateEpoch() {
        IPersister<Epoch> persister = persisterFactory.createNamedItemPersister(Epoch.class);

        save(persister, new Epoch(null, "I", "1835 - 1920", false));
        save(persister, new Epoch(null, "II", "1920 - 1950", false));
        save(persister, new Epoch(null, "III", "1949 - 1970", false));
        save(persister, new Epoch(null, "IV", "1965 - 1990", false));
        save(persister, new Epoch(null, "V", "1990 - 2006", false));
        save(persister, new Epoch(null, "VI", "2006 -", false));
        save(persister, new Epoch(null, "Ia", "1835 - 1875", false));
        save(persister, new Epoch(null, "Ib", "1875 - 1895", false));
        save(persister, new Epoch(null, "Ic", "1895 - 1910", false));
        save(persister, new Epoch(null, "Id", "1910 - 1920", false));
        save(persister, new Epoch(null, "IIa", "1920 - 1925", false));
        save(persister, new Epoch(null, "IIb", "1925 - 1937", false));
        save(persister, new Epoch(null, "IIc", "1937 - 1950", false));
        save(persister, new Epoch(null, "IIIa", "1949 - 1956", false));
        save(persister, new Epoch(null, "IIIb", "1956 - 1970", false));
        save(persister, new Epoch(null, "IVa", "1965 - 1970", false));
        save(persister, new Epoch(null, "IVb", "1970 – 1980", false));
        save(persister, new Epoch(null, "IVc", "1980 – 1990", false));
        save(persister, new Epoch(null, "Va", "1990 - 1994", false));
        save(persister, new Epoch(null, "Vb", "1994 - 2000", false));
        save(persister, new Epoch(null, "Vc", "2000 - 2006", false));
    }

    protected void populateGattung() {
        IPersister<Gattung> persister = persisterFactory.createNamedItemPersister(Gattung.class);

        save(persister, new Gattung(null, "AB3yge", "AB3yge", false));
        save(persister, new Gattung(null, "ADümh 101", "ADümh 101", false));
        save(persister, new Gattung(null, "ARDümz 106", "ARDümz 106", false));
        save(persister, new Gattung(null, "Apümh 121", "Apümh 121", false));
        save(persister, new Gattung(null, "Avmz 206", "Avmz 206", false));
        save(persister, new Gattung(null, "Avümh 111", "Avümh 111", false));
        save(persister, new Gattung(null, "Aüm 203", "Aüm 203", false));
        save(persister, new Gattung(null, "B3yge", "B3yge", false));
        save(persister, new Gattung(null, "B4yge", "B4yge", false));
        save(persister, new Gattung(null, "BA 115", "BA 115", false));
        save(persister, new Gattung(null, "BD3yge", "BD3yge", false));
        save(persister, new Gattung(null, "BR 03", "BR 03", false));
        save(persister, new Gattung(null, "BR 03.10", "BR 03.10", false));
        save(persister, new Gattung(null, "BR 103", "BR 103", false));
        save(persister, new Gattung(null, "BR 111", "BR 111", false));
        save(persister, new Gattung(null, "BR 151", "BR 151", false));
        save(persister, new Gattung(null, "BR 211", "BR 211", false));
        save(persister, new Gattung(null, "BR 216", "BR 216", false));
        save(persister, new Gattung(null, "BR 220", "BR 220", false));
        save(persister, new Gattung(null, "BR 230", "BR 230", false));
        save(persister, new Gattung(null, "BR 24", "BR 24", false));
        save(persister, new Gattung(null, "BR 260", "BR 260", false));
        save(persister, new Gattung(null, "BR 321", "BR 321", false));
        save(persister, new Gattung(null, "BR 45", "BR 45", false));
        save(persister, new Gattung(null, "BR 50", "BR 50", false));
        save(persister, new Gattung(null, "BR 53", "BR 53", false));
        save(persister, new Gattung(null, "BR 601", "BR 601", false));
        save(persister, new Gattung(null, "BR 64", "BR 64", false));
        save(persister, new Gattung(null, "BR 701", "BR 701", false));
        save(persister, new Gattung(null, "BR 81", "BR 81", false));
        save(persister, new Gattung(null, "BR 85", "BR 85", false));
        save(persister, new Gattung(null, "BR 86", "BR 86", false));
        save(persister, new Gattung(null, "BR 89.0", "BR 89.0", false));
        save(persister, new Gattung(null, "BR 96", "BR 96", false));
        save(persister, new Gattung(null, "BR 98.3", "BR 98.3", false));
        save(persister, new Gattung(null, "BT 10", "BT 10", false));
        save(persister, new Gattung(null, "BTmm 51", "BTmm 51", false));
        save(persister, new Gattung(null, "Bi 18t", "Bi 18t", false));
        save(persister, new Gattung(null, "Bi", "Bi", false));
        save(persister, new Gattung(null, "DByg 546", "DByg 546", false));
        save(persister, new Gattung(null, "DHG 700C", "DHG 700C", false));
        save(persister, new Gattung(null, "ELD4", "ELD4", false));
        save(persister, new Gattung(null, "ET 403", "ET 403", false));
        save(persister, new Gattung(null, "ET 91", "ET 91", false));
        save(persister, new Gattung(null, "El-u 061", "El-u 061", false));
        save(persister, new Gattung(null, "F7", "F7", false));
        save(persister, new Gattung(null, "G 10", "G 10", false));
        save(persister, new Gattung(null, "Gl", "Gl", false));
        save(persister, new Gattung(null, "Gmhs 53", "Gmhs 53", false));
        save(persister, new Gattung(null, "Gr 20", "Gr 20", false));
        save(persister, new Gattung(null, "Gs 210", "Gs 210", false));
        save(persister, new Gattung(null, "H 10", "H 10", false));
        save(persister, new Gattung(null, "H10", "H10", false));
        save(persister, new Gattung(null, "ICR-A10", "ICR-A10", false));
        save(persister, new Gattung(null, "ICR-B10", "ICR-B10", false));
        save(persister, new Gattung(null, "Ibdlps 383", "Ibdlps 383", false));
        save(persister, new Gattung(null, "Ichqrs 377", "Ichqrs 377", false));
        save(persister, new Gattung(null, "Kbs 443", "Kbs 443", false));
        save(persister, new Gattung(null, "Kklm 505", "Kklm 505", false));
        save(persister, new Gattung(null, "Laae 540", "Laae 540", false));
        save(persister, new Gattung(null, "Mannschaftswagen 376", "Mannschaftswagen 376", false));
        save(persister, new Gattung(null, "NS 6400", "NS 6400", false));
        save(persister, new Gattung(null, "OOtz 50", "OOtz 50", false));
        save(persister, new Gattung(null, "Om „Breslau“", "Om „Breslau“", false));
        save(persister, new Gattung(null, "Om 12", "Om 12", false));
        save(persister, new Gattung(null, "Otmm 70", "Otmm 70", false));
        save(persister, new Gattung(null, "Pw 90 HzL", "Pw 90 HzL", false));
        save(persister, new Gattung(null, "Pwg Pr 14", "Pwg Pr 14", false));
        save(persister, new Gattung(null, "Pwg Pr 14", "Pwg Pr 14", false));
        save(persister, new Gattung(null, "Pwgs 41", "Pwgs 41", false));
        save(persister, new Gattung(null, "Pwi 28", "Pwi 28", false));
        save(persister, new Gattung(null, "Pwi Wü 13", "Pwi Wü 13", false));
        save(persister, new Gattung(null, "R 02", "R 02", false));
        save(persister, new Gattung(null, "Rlmmps 651", "Rlmmps 651", false));
        save(persister, new Gattung(null, "Rlmms 58", "Rlmms 58", false));
        save(persister, new Gattung(null, "Rlmms", "Rlmms", false));
        save(persister, new Gattung(null, "Rlmmso 56", "Rlmmso 56", false));
        save(persister, new Gattung(null, "Rs 684", "Rs 684", false));
        save(persister, new Gattung(null, "SSH 71", "SSH 71", false));
        save(persister, new Gattung(null, "SSym „Köln“", "SSym „Köln“", false));
        save(persister, new Gattung(null, "Samms 709", "Samms 709", false));
        save(persister, new Gattung(null, "Schotterwagen 166", "Schotterwagen 166", false));
        save(persister, new Gattung(null, "Sm 24", "Sm 24", false));
        save(persister, new Gattung(null, "Tehs 50", "Tehs 50", false));
        save(persister, new Gattung(null, "Tko 02", "Tko 02", false));
        save(persister, new Gattung(null, "Ucs", "Ucs", false));
        save(persister, new Gattung(null, "V 200", "V 200", false));
        save(persister, new Gattung(null, "V 80", "V 80", false));
        save(persister, new Gattung(null, "VS 98", "VS 98", false));
        save(persister, new Gattung(null, "VT 75", "VT 75", false));
        save(persister, new Gattung(null, "VT 95", "VT 95", false));
        save(persister, new Gattung(null, "VT 98", "VT 98", false));
        save(persister, new Gattung(null, "Viehtransport", "Viehtransport", false));
        save(persister, new Gattung(null, "WGmh 824", "WGmh 824", false));
        save(persister, new Gattung(null, "WRmz 135", "WRmz 135", false));
        save(persister, new Gattung(null, "WRümh 131", "WRümh 131", false));
        save(persister, new Gattung(null, "X05 „Erfurt“", "X05 „Erfurt“", false));
        save(persister, new Gattung(null, "Zces", "Zces", false));
        save(persister, new Gattung(null, "üm 312", "üm 312", false));
        save(persister, new Gattung(null, "üm 313", "üm 313", false));
    }

    protected void populateHersteller() {
        IPersister<Hersteller> persister = persisterFactory.createNamedItemPersister(Hersteller.class);

        save(persister, new Hersteller(null, "Avago Technologies", null, null, null, false));
        save(persister, new Hersteller(null, "4MFOR", null, null, null, false));
        save(persister, new Hersteller(null, "Artitec", null, null, null, false));
        save(persister, new Hersteller(null, "Auhagen", null, null, null, false));
        save(persister, new Hersteller(null, "B&K", null, null, null, false));
        save(persister, new Hersteller(null, "Brawa", null, null, null, false));
        save(persister, new Hersteller(null, "Busch", null, null, null, false));
        save(persister, new Hersteller(null, "DCC Supplies", null, null, null, false));
        save(persister, new Hersteller(null, "Deluxe Materials", null, null, null, false));
        save(persister, new Hersteller(null, "Digital-Bahn", null, null, null, false));
        save(persister, new Hersteller(null, "DigiTrain", null, null, null, false));
        save(persister, new Hersteller(null, "Diotec", null, null, null, false));
        save(persister, new Hersteller(null, "Erbert", null, null, null, false));
        save(persister, new Hersteller(null, "ESU", null, null, null, false));
        save(persister, new Hersteller(null, "Evergreen", null, null, null, false));
        save(persister, new Hersteller(null, "Fairchild", null, null, null, false));
        save(persister, new Hersteller(null, "Faller", null, null, null, false));
        save(persister, new Hersteller(null, "Fleischmann", null, null, null, false));
        save(persister, new Hersteller(null, "Gassner", null, null, null, false));
        save(persister, new Hersteller(null, "GaugeMaster", null, null, null, false));
        save(persister, new Hersteller(null, "Heico", null, null, null, false));
        save(persister, new Hersteller(null, "Herkat", null, null, null, false));
        save(persister, new Hersteller(null, "Herpa", null, null, null, false));
        save(persister, new Hersteller(null, "Humbrol", null, null, null, false));
        save(persister, new Hersteller(null, "Jordan", null, null, null, false));
        save(persister, new Hersteller(null, "Kibri", null, null, null, false));
        save(persister, new Hersteller(null, "Kingbright", null, null, null, false));
        save(persister, new Hersteller(null, "KKPMO", null, null, null, false));
        save(persister, new Hersteller(null, "Knipex", null, null, null, false));
        save(persister, new Hersteller(null, "Kühn", null, null, null, false));
        save(persister, new Hersteller(null, "LDT", null, null, null, false));
        save(persister, new Hersteller(null, "Liliput", null, null, null, false));
        save(persister, new Hersteller(null, "Lima", null, null, null, false));
        save(persister, new Hersteller(null, "Littfinski", null, null, null, false));
        save(persister, new Hersteller(null, "LUX-Modellbau", null, null, null, false));
        save(persister, new Hersteller(null, "Maquett", null, null, null, false));
        save(persister, new Hersteller(null, "Märklin", null, null, null, false));
        save(persister, new Hersteller(null, "Mehano", null, null, null, false));
        save(persister, new Hersteller(null, "Merten", null, null, null, false));
        save(persister, new Hersteller(null, "Noch", null, null, null, false));
        save(persister, new Hersteller(null, "Omron", null, null, null, false));
        save(persister, new Hersteller(null, "Preiser", null, null, null, false));
        save(persister, new Hersteller(null, "Proses", null, null, null, false));
        save(persister, new Hersteller(null, "Ratio", null, null, null, false));
        save(persister, new Hersteller(null, "Red Line", null, null, null, false));
        save(persister, new Hersteller(null, "Revell", null, null, null, false));
        save(persister, new Hersteller(null, "Ricko", null, null, null, false));
        save(persister, new Hersteller(null, "Rivarossi", null, null, null, false));
        save(persister, new Hersteller(null, "Roco", null, null, null, false));
        save(persister, new Hersteller(null, "RTS", null, null, null, false));
        save(persister, new Hersteller(null, "Schuco", null, null, null, false));
        save(persister, new Hersteller(null, "Seuthe", null, null, null, false));
        save(persister, new Hersteller(null, "Taiwan Semiconductor", null, null, null, false));
        save(persister, new Hersteller(null, "Tamiya", null, null, null, false));
        save(persister, new Hersteller(null, "Tams", null, null, null, false));
        save(persister, new Hersteller(null, "Tower Pro", null, null, null, false));
        save(persister, new Hersteller(null, "Trix", null, null, null, false));
        save(persister, new Hersteller(null, "TruOpto", null, null, null, false));
        save(persister, new Hersteller(null, "Uhlenbrock", null, null, null, false));
        save(persister, new Hersteller(null, "Unbekant", null, null, null, false));
        save(persister, new Hersteller(null, "Viessmann", null, null, null, false));
        save(persister, new Hersteller(null, "Walthers", null, null, null, false));
        save(persister, new Hersteller(null, "Weinert", null, null, null, false));
        save(persister, new Hersteller(null, "Wiking", null, null, null, false));
        save(persister, new Hersteller(null, "Woodland Scenics", null, null, null, false));
        save(persister, new Hersteller(null, "Zemo", null, null, null, false));
    }

    protected void populateKategorie() {
        IPersister<Kategorie> persister = persisterFactory.createNamedItemPersister(Kategorie.class);

        save(persister, new Kategorie(null, "Ausgestaltung", "Ausgestaltung", false));
        save(persister, new Kategorie(null, "Beleuchtung", "Beleuchtung", false));
        save(persister, new Kategorie(null, "Decoder", "Decoder", false));
        save(persister, new Kategorie(null, "Ersatzteil", "Ersatzteil", false));
        save(persister, new Kategorie(null, "Fahrzeug", "Fahrzeug", false));
        save(persister, new Kategorie(null, "Gebaüde", "Gebaüde", false));
        save(persister, new Kategorie(null, "Gleismateriel", "Gleismateriel", false));
        save(persister, new Kategorie(null, "Landschaftsbau", "Landschaftsbau", false));
        save(persister, new Kategorie(null, "Lokomotiv", "Lokomotiv", false));
        save(persister, new Kategorie(null, "Oberleitung", "Oberleitung", false));
        save(persister, new Kategorie(null, "Set", "Set", false));
        save(persister, new Kategorie(null, "Signaltechnik", "Signaltechnik", false));
        save(persister, new Kategorie(null, "Sonstiges", "Sonstiges", false));
        save(persister, new Kategorie(null, "Steuerungstechnik", "Steuerungstechnik", false));
        save(persister, new Kategorie(null, "Treibwagen", "Treibwagen", false));
        save(persister, new Kategorie(null, "Wagen", "Wagen", false));
        save(persister, new Kategorie(null, "Werkzeug", "Werkzeug", false));
        save(persister, new Kategorie(null, "Zubehör", "Zubehör", false));
    }

    protected void populateKupplung() {
        IPersister<Kupplung> persister = persisterFactory.createNamedItemPersister(Kupplung.class);

        save(persister, new Kupplung(null, "Relex", "Relex Kupplung", false));
        save(persister, new Kupplung(null, "KK", "Märklin-Kurzkupplungen mit Drehpunkt", false));
        save(persister, new Kupplung(null, "NEM", "Märklin-Kurzkupplungen in Norm-Aufnahme mit Drehpunkt", false));
        save(persister, 
                new Kupplung(null, "NEM KK", "Märklin-Kurzkupplungen in Norm-Aufnahme mit Kulissenführung", false));
        save(persister, new Kupplung(null, "SF KK", 
                "Märklin-Kurzkupplungen in Norm-Aufnahme mit Stromfürhrender Kulissenführung", false));
        save(persister, new Kupplung(null, "Telex", "Telex Kupplung", false));
    }

    protected void populateLand() {
    }

    protected void populateLicht() {
        IPersister<Licht> persister = persisterFactory.createNamedItemPersister(Licht.class);

        save(persister, new Licht(null, "L1V", "Einfach-Spitzensignal vorne", false));
        save(persister, new Licht(null, "L1W", "Einfach-Spitzensignal mit der Fahrtrichtung wechselnd.", false));
        save(persister, new Licht(null, "L2V", "Zweilicht-Spitzensignal vorne", false));
        save(persister, new Licht(null, "L2L2", "Zweilicht-Spitzensignal vorne und hinten", false));
        save(persister, new Licht(null, "L2W", "Zweilicht-Spitzensignal mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "L3V", "Dreilicht-Spitzensignal vorne", false));
        save(persister, new Licht(null, "L3W", "Dreilicht-Spitzensignal mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "L4W", "Vierlicht-Spitzensignal mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "R1H", "Ein rotes Schlusslicht", false));
        save(persister, new Licht(null, "R2H", "Zwei rote Schlusslichter", false));
        save(persister, new Licht(null, "L2R2W", 
                "Zweilicht-Spitzensignal und zwei rote Schlusslichter mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "L3R1W", 
                "Dreilicht-Spitzensignal und ein rotes Schlusslicht mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "L3R2W", 
                "Dreilicht-Spitzensignal und zwei rote Schlusslichter mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "L3L1W", 
                "Dreilicht-Spitzensignal und ein weißes Schlusslicht mit der Fahrtrichtung wechselnd", false));
        save(persister, new Licht(null, "L3L2W", 
                "Dreilicht-Spitzensignal und zwei weißes Schlusslicht mit der Fahrtrichtung wechselnd", false));
    }

    protected void populateMassstab() {
        IPersister<Massstab> persister = persisterFactory.createNamedItemPersister(Massstab.class);

        save(persister, new Massstab(null, "0", "1:45 32 mm", false));
        save(persister, new Massstab(null, "0e", "1:45 16.5 mm", false));
        save(persister, new Massstab(null, "0i", "1:45 12 mm", false));
        save(persister, new Massstab(null, "0m", "1:45 22.5 mm", false));
        save(persister, new Massstab(null, "0p", "1:45 9 mm", false));
        save(persister, new Massstab(null, "1\"", "1:12 121 mm", false));
        save(persister, new Massstab(null, "1", "1:32 45 mm", false));
        save(persister, new Massstab(null, "1e", "1:32 22.5 mm", false));
        save(persister, new Massstab(null, "1i", "1:32 16.5 mm", false));
        save(persister, new Massstab(null, "1m", "1:32 32 mm", false));
        save(persister, new Massstab(null, "1n3", "1:32 28.6 mm", false));
        save(persister, new Massstab(null, "1p", "1:32 12 mm", false));
        save(persister, new Massstab(null, "F", "1:20.32 70.64 mm", false));
        save(persister, new Massstab(null, "Fn3", "1:20.32 45 mm", false));
        save(persister, new Massstab(null, "H0", "1:87 16.5 mm", false));
        save(persister, new Massstab(null, "H0e", "1:87 9 mm", false));
        save(persister, new Massstab(null, "H0i", "1:87 6.5 mm (H0f)", false));
        save(persister, new Massstab(null, "H0m", "1:87 12 mm", false));
        save(persister, new Massstab(null, "H0p", "1:87 4.5 mm", false));
        save(persister, new Massstab(null, "HOn2", "1:87.1 7 mm", false));
        save(persister, new Massstab(null, "II", "1:22.5 63.5 mm", false));
        save(persister, new Massstab(null, "IIe", "1:22.5 32 mm", false));
        save(persister, new Massstab(null, "Iii (NEM)", "1:22.5 22.5 mm", false));
        save(persister, new Massstab(null, "III", "1:16 89 mm", false));
        save(persister, new Massstab(null, "IIIe", "1:16 45 mm", false));
        save(persister, new Massstab(null, "IIIi (NMRA)", "1:16 32 mm (3/4\")", false));
        save(persister, new Massstab(null, "IIIm", "1:16 63.5 mm", false));
        save(persister, new Massstab(null, "IIIp", "1:16 22.5 mm", false));
        save(persister, new Massstab(null, "IIm", "1:22.5 45 mm", false));
        save(persister, new Massstab(null, "IIp", "1:22.5 16.5 mm", false));
        save(persister, new Massstab(null, "N", "1:160 9 mm", false));
        save(persister, new Massstab(null, "Ne", "1:160 4.5 mm (Nn2)", false));
        save(persister, new Massstab(null, "Nm", "1:160 6.5 mm (Nn3)", false));
        save(persister, new Massstab(null, "O", "1:48 31.75 mm", false));
        save(persister, new Massstab(null, "On2", "1:48 12.7 mm", false));
        save(persister, new Massstab(null, "On3", "1:48 19.4 mm ", false));
        save(persister, new Massstab(null, "On30", "1:48 16.5 mm", false));
        save(persister, new Massstab(null, "OO", "1:76.2 19.05 mm", false));
        save(persister, new Massstab(null, "S", "1:64 22.5 mm", false));
        save(persister, new Massstab(null, "Se", "1:64 12 mm", false));
        save(persister, new Massstab(null, "Si", "1:64 9 mm", false));
        save(persister, new Massstab(null, "Sm", "1:64 16.5 mm", false));
        save(persister, new Massstab(null, "Sn3", "1:64 14.3 mm", false));
        save(persister, new Massstab(null, "Sp", "1:64 6.5 mm", false));
        save(persister, new Massstab(null, "TT", "1:120 12 mm", false));
        save(persister, new Massstab(null, "TTe", "1:120 6.5 mm", false));
        save(persister, new Massstab(null, "TTi", "1:120 4.5 mm", false));
        save(persister, new Massstab(null, "TTm", "1:120 9 mm", false));
        save(persister, new Massstab(null, "V", "1:11 127 mm", false));
        save(persister, new Massstab(null, "Ve", "1:11 63.5 mm", false));
        save(persister, new Massstab(null, "Vi", "1:11 45 mm", false));
        save(persister, new Massstab(null, "VII", "1:8 184 mm", false));
        save(persister, new Massstab(null, "VIIe", "1:8 89 mm", false));
        save(persister, new Massstab(null, "VIIi", "1:8 63.5 mm", false));
        save(persister, new Massstab(null, "VIIm", "1:8 127 mm", false));
        save(persister, new Massstab(null, "VIIp", "1:8 45 mm", false));
        save(persister, new Massstab(null, "Vm", "1:11 89 mm", false));
        save(persister, new Massstab(null, "Vp", "1:11 32 mm", false));
        save(persister, new Massstab(null, "X", "1:5.5 260 mm", false));
        save(persister, new Massstab(null, "Xe", "1:5.5 127 mm", false));
        save(persister, new Massstab(null, "Xi", "1:5.5 89 mm", false));
        save(persister, new Massstab(null, "Xm", "1:5.5 184 mm", false));
        save(persister, new Massstab(null, "Xp", "1:5.5 63.5 mm", false));
        save(persister, new Massstab(null, "Z", "1:220 6.5 mm", false));
        save(persister, new Massstab(null, "Zm", "1:220 4.5 mm", false));
    }

    protected void populateMotorTyp() {
        IPersister<MotorTyp> persister = persisterFactory.createNamedItemPersister(MotorTyp.class);

        save(persister, new MotorTyp(null, "C-Sinus", "C-Sinus", false));
        save(persister, new MotorTyp(null, "DCM", "DCM", false));
        save(persister, new MotorTyp(null, "Glockenanker", "Glockenanker", false));
        save(persister, new MotorTyp(null, "HLM", "HLM", false));
        save(persister, new MotorTyp(null, "HLM MS1-7", "HLM MS1-7", false));
        save(persister, new MotorTyp(null, "HLM MS1-8", "HLM MS1-8", false));
        save(persister, new MotorTyp(null, "HLM MS2-7", "HLM MS2-7", false));
        save(persister, new MotorTyp(null, "HLM MS2-8", "HLM MS2-8", false));
        save(persister, new MotorTyp(null, "HLM S", "HLM S", false));
        save(persister, new MotorTyp(null, "LFCM MS1-7", "LFCM MS1-7", false));
        save(persister, new MotorTyp(null, "LFCM MS1-8", "LFCM MS1-8", false));
        save(persister, new MotorTyp(null, "LFCM MS2-7", "LFCM MS2-7", false));
        save(persister, new MotorTyp(null, "LFCM MS2-8", "LFCM MS2-8", false));
        save(persister, new MotorTyp(null, "SFCM", "SFCM", false));
    }

    protected void populateProdukt() {
    }

    protected void populateProduktTeil() {
    }

    protected void populateProtokoll() {
        IPersister<Protokoll> persister = persisterFactory.createNamedItemPersister(Protokoll.class);

        save(persister, new Protokoll(null, "DELTA", "Märklin DELTA", false));
        save(persister, new Protokoll(null, "fx", "Märklin fx", false));
        save(persister, new Protokoll(null, "mfx", "Märklin mfx", false));
        save(persister, new Protokoll(null, "DCC", "DCC", false));
        save(persister, new Protokoll(null, "MM", "Märklin Motorola", false));
        save(persister, new Protokoll(null, "Weiche", "Märklin Motorola Weiche", false));
    }

    protected void populateSonderModell() {
        IPersister<SonderModell> persister = persisterFactory.createNamedItemPersister(SonderModell.class);

        save(persister, new SonderModell(null, "Märklin Magazin", "Märklin Magazin", false));
        save(persister, new SonderModell(null, "Märklin Insider", "Märklin Insider", false));
        save(persister, new SonderModell(null, "MHI Exclusiv", "Märklin Handler Initiative", false));
        save(persister, new SonderModell(null, "MM Jahreswagen", "Märklin Magazin Jahreswagen", false));
        save(persister, new SonderModell(null, "KC Jahreswagen", "Märklin Kids Club Jahreswagen", false));
        save(persister, new SonderModell(null, "Einmalige Serien", "Einmalige Serien", false));
        save(persister, new SonderModell(null, "Museumswagen", "Museumswagen", false));
        save(persister, new SonderModell(null, "Weihnachtswagen", "Weihnachtswagen", false));
        save(persister, new SonderModell(null, "Sondermodel", "Sondermodel", false));
    }

    protected void populateSpurweite() {
        IPersister<Spurweite> persister = persisterFactory.createNamedItemPersister(Spurweite.class);

        save(persister, new Spurweite(null, "0", "32mm", false));
        save(persister, new Spurweite(null, "TT", "12mm", false));
        save(persister, new Spurweite(null, "H0", "16, 5mm", false));
        save(persister, new Spurweite(null, "N", "9mm", false));
        save(persister, new Spurweite(null, "Z", "6, 5mm", false));
        save(persister, new Spurweite(null, "I", "45mm", false));
        save(persister, new Spurweite(null, "S", "22, 5mm", false));
        save(persister, new Spurweite(null, "II", "64mm", false));
        save(persister, new Spurweite(null, "III", "89mm", false));
        save(persister, new Spurweite(null, "V", "127mm", false));
        save(persister, new Spurweite(null, "VII", "184mm", false));
        save(persister, new Spurweite(null, "X", "260mm", false));
    }

    protected void populateSteuerung() {
        IPersister<Steuerung> persister = persisterFactory.createNamedItemPersister(Steuerung.class);

        save(persister, new Steuerung(null, "digital", "digital", false));
        save(persister, new Steuerung(null, "mechanisch", "mechanisch (FRU)", false));
        save(persister, new Steuerung(null, "Umschaltelektronik", "Umschaltelektronik", false));
    }

    protected void populateUnterKategorie() {
        IPersister<UnterKategorie> persister = persisterFactory.createNamedItemPersister(UnterKategorie.class);

        IPersister<Kategorie> lookup = persisterFactory.createNamedItemPersister(Kategorie.class);

        IKategorie ausgestaltung = findByKey(lookup, "Ausgestaltung");

        save(persister, new UnterKategorie(null, ausgestaltung, "Ausgestaltung", "Ausgestaltung", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Blühmen", "Blühmen", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Bäume", "Bäume", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Büsche", "Büsche", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Figuren", "Figuren", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Hecken", "Hecken", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Ladegut", "Ladegut", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Pflanzen", "Pflanzen", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Zeichen", "Zeichen", false));
        save(persister, new UnterKategorie(null, ausgestaltung, "Zäune", "Zäune", false));

        IKategorie beleuchtung = findByKey(lookup, "Beleuchtung");

        save(persister, new UnterKategorie(null, beleuchtung, "Beleuchtung", "Beleuchtung", false));
        save(persister, new UnterKategorie(null, beleuchtung, "Gluehlampe", "Gluehlampe", false));
        save(persister, new UnterKategorie(null, beleuchtung, "Innenbeleuchtung", "Innenbeleuchtung", false));
        save(persister, new UnterKategorie(null, beleuchtung, "Leuchteinsatz", "Leuchteinsatz", false));
        save(persister, 
                new UnterKategorie(null, beleuchtung, "Stromführendekupplungen", "Stromführendekupplungen", false));
        save(persister, new UnterKategorie(null, beleuchtung, "Stromzuführung", "Stromzuführung", false));
        save(persister, new UnterKategorie(null, beleuchtung, "Zugschlussbeleuchtung", "Zugschlussbeleuchtung", false));

        IKategorie decoder = findByKey(lookup, "Decoder");

        save(persister, new UnterKategorie(null, decoder, "Decoder", "Decoder", false));
        save(persister, new UnterKategorie(null, decoder, "Lautsprecher", "Lautsprecher", false));

        IKategorie ersatzteil = findByKey(lookup, "Ersatzteil");

        save(persister, new UnterKategorie(null, ersatzteil, "Anker", "Anker", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Beschwerung", "Beschwerung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Drehgestellrahmen", "Drehgestellrahmen", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Drehgestell", "Drehgestell", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Entstördrossel", "Entstördrossel", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Ersatzteil", "Ersatzteil", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Feder", "Feder", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Feldmagnet", "Feldmagnet", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Fenster", "Fenster", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Grundplatte", "Grundplatte", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Haftreifen", "Haftreifen", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Halteplatte", "Halteplatte", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Inneneinrichtung", "Inneneinrichtung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Isolierung", "Isolierung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kabel", "Kabel", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kabelklemmen", "Kabelklemmen", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Klappe", "Klappe", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kohlbursten", "Kohlbursten", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kuppelstange", "Kuppelstange", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kupplung", "Kupplung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kupplungsdeichsel", "Kupplungsdeichsel", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kupplungshaken", "Kupplungshaken", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kupplungskinematik", "Kupplungskinematik", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kupplungsschacht", "Kupplungsschacht", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Kurzkupplung", "Kurzkupplung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Leitschaufel", "Leitschaufel", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Lokumbausätze", "Lokumbausätze", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Massefeder", "Massefeder", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Messingblech", "Messingblech", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Motorschild", "Motorschild", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Mutter", "Mutter", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Pantograph", "Pantograph", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Prallplatte", "Prallplatte", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Puffer", "Puffer", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Rad", "Rad", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Relais", "Relais", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Relexkupplung", "Relexkupplung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Schaltsfeder", "Schaltsfeder", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Schleifer", "Schleifer", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Schraube", "Schraube", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Schraubenkupplung", "Schraubenkupplung", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Senkschraube", "Senkschraube", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Stange", "Stange", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Telex", "Telex", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Traeger", "Traeger", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Wagenboden", "Wagenboden", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Weichenfeder", "Weichenfeder", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Zugfeder", "Zugfeder", false));
        save(persister, new UnterKategorie(null, ersatzteil, "Zylinderschraube", "Zylinderschraube", false));

        IKategorie fahrzeug = findByKey(lookup, "Fahrzeug");

        save(persister, new UnterKategorie(null, fahrzeug, "Fahrzeug", "Fahrzeug", false));

        IKategorie gebaude = findByKey(lookup, "Gebaüde");

        save(persister, new UnterKategorie(null, gebaude, "Bekohlung", "Bekohlung", false));
        save(persister, new UnterKategorie(null, gebaude, "Bockkrän", "Bockkrän", false));
        save(persister, new UnterKategorie(null, gebaude, "Drehscheibe", "Drehscheibe", false));
        save(persister, new UnterKategorie(null, gebaude, "Gebaüde", "Gebaüde", false));

        IKategorie gleismateriel = findByKey(lookup, "Gleismateriel");

        save(persister, new UnterKategorie(null, gleismateriel, "Gleismateriel", "Gleismateriel", false));

        IKategorie landschaftsbau = findByKey(lookup, "Landschaftsbau");

        save(persister, new UnterKategorie(null, landschaftsbau, "Landschaftsbau", "Landschaftsbau", false));

        IKategorie lokomotive = findByKey(lookup, "Lokomotiv");

        save(persister, new UnterKategorie(null, lokomotive, "Lokomotiv", "Lokomotiv", false));
        save(persister, new UnterKategorie(null, lokomotive, "Akku", "Akku", false));
        save(persister, new UnterKategorie(null, lokomotive, "Dampf", "Dampf", false));
        save(persister, new UnterKategorie(null, lokomotive, "Diesel", "Diesel", false));
        save(persister, new UnterKategorie(null, lokomotive, "Elektro", "Elektro", false));

        IKategorie oberleitung = findByKey(lookup, "Oberleitung");

        save(persister, new UnterKategorie(null, oberleitung, "Oberleitung", "Oberleitung", false));

        IKategorie set = findByKey(lookup, "Set");

        save(persister, new UnterKategorie(null, set, "Set", "Set", false));

        IKategorie signaltechnik = findByKey(lookup, "Signaltechnik");

        save(persister, new UnterKategorie(null, signaltechnik, "Signalbirne", "Signalbirne", false));
        save(persister, new UnterKategorie(null, signaltechnik, "Signaltechnik", "Signaltechnik", false));

        IKategorie sonstiges = findByKey(lookup, "Sonstiges");

        save(persister, new UnterKategorie(null, sonstiges, "Sonstiges", "Sonstiges", false));

        IKategorie steuerungstechnik = findByKey(lookup, "Steuerungstechnik");

        save(persister, new UnterKategorie(null, steuerungstechnik, "Steuerungstechnik", "Steuerungstechnik", false));
        save(persister, new UnterKategorie(null, steuerungstechnik, "Stromversorgung", "Stromversorgung", false));

        IKategorie treibwagen = findByKey(lookup, "Treibwagen");

        save(persister, new UnterKategorie(null, treibwagen, "Beiwagen", "Beiwagen", false));
        save(persister, new UnterKategorie(null, treibwagen, "Mittelwagen", "Mittelwagen", false));
        save(persister, new UnterKategorie(null, treibwagen, "Steurwagen", "Steurwagen", false));
        save(persister, new UnterKategorie(null, treibwagen, "Treibwagen", "Treibwagen", false));

        IKategorie wagen = findByKey(lookup, "Wagen");

        save(persister, new UnterKategorie(null, wagen, "Abteilwagen", "Abteilwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Aussichtswagen", "Aussichtswagen", false));
        save(persister, new UnterKategorie(null, wagen, "Autotransportwagen", "Autotransportwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Bahndienstwagen", "Bahndienstwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Bananenwagen", "Bananenwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Barwagen", "Barwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Behältertragwagen", "Behältertragwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Bierwagen", "Bierwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Carbid-Flaschenwagen", "Carbid-Flaschenwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Containertragwagen", "Containertragwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Doppelstockwagen", "Doppelstockwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Drehschemelwagen", "Drehschemelwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Fahrradtransportwagen", "Fahrradtransportwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Flachwagen", "Flachwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Gaswagen", "Gaswagen", false));
        save(persister, new UnterKategorie(null, wagen, "Gedeckter Güterwagen", "Gedeckter Güterwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Gepäckwagen", "Gepäckwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Gesellschaftswagen", "Gesellschaftswagen", false));
        save(persister, new UnterKategorie(null, wagen, "Großraumwagen", "Großraumwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Güterwagen", "Güterwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Güterzugbegleitwagen", "Güterzugbegleitwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Hochbordwagen", "Hochbordwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Kesselwagen", "Kesselwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Kränwagen", "Kränwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Kühlwagen", "Kühlwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Mannschaftswagen", "Mannschaftswagen", false));
        save(persister, new UnterKategorie(null, wagen, "Messewagen", "Messewagen", false));
        save(persister, new UnterKategorie(null, wagen, "Nahverkehrswagen", "Nahverkehrswagen", false));
        save(persister, new UnterKategorie(null, wagen, "Niederbordwagen", "Niederbordwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Rolldachwagen", "Rolldachwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Schiebewandwagen", "Schiebewandwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Schneepflug", "Schneepflug", false));
        save(persister, new UnterKategorie(null, wagen, "Schotterwagen", "Schotterwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Schwerlastwagen", "Schwerlastwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Schüttgut-Kippwagen", "Schüttgut-Kippwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Seitenentladewagen", "Seitenentladewagen", false));
        save(persister, new UnterKategorie(null, wagen, "Silowagen", "Silowagen", false));
        save(persister, new UnterKategorie(null, wagen, "Sonderfahrzeug", "Sonderfahrzeug", false));
        save(persister, new UnterKategorie(null, wagen, "Speisewagen", "Speisewagen", false));
        save(persister, new UnterKategorie(null, wagen, "Taschenwagen", "Taschenwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Tiefladewagen", "Tiefladewagen", false));
        save(persister, new UnterKategorie(null, wagen, "Torpedopfannenwagen", "Torpedopfannenwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Umbauwagen", "Umbauwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Verschlagwagen", "Verschlagwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Viehwagen", "Viehwagen", false));
        save(persister, new UnterKategorie(null, wagen, "Wagen", "Wagen", false));
        save(persister, new UnterKategorie(null, wagen, "Weihnachtswagen", "Weihnachtswagen", false));
        save(persister, new UnterKategorie(null, wagen, "Weinwagen", "Weinwagen", false));

        IKategorie werkzeug = findByKey(lookup, "Werkzeug");

        save(persister, new UnterKategorie(null, werkzeug, "Bücher", "Bücher", false));
        save(persister, new UnterKategorie(null, werkzeug, "Farbe", "Farbe", false));
        save(persister, new UnterKategorie(null, werkzeug, "Kleb", "Kleb", false));
        save(persister, new UnterKategorie(null, werkzeug, "Werkzeug", "Werkzeug", false));

        IKategorie zubehor = findByKey(lookup, "Zubehör");

        save(persister, new UnterKategorie(null, zubehor, "Beschriftigung", "Beschriftigung", false));
        save(persister, new UnterKategorie(null, zubehor, "Zubehör", "Zubehör", false));
    }

    protected void populateVorbild() {
    }

    protected void populateWahrung() {
        IPersister<Wahrung> persister = persisterFactory.createNamedItemPersister(Wahrung.class);

        save(persister, new Wahrung(null, "AUD", "Australian Dollar", 2, false));
        save(persister, new Wahrung(null, "DEM", "Deutschemark", 2, false));
        save(persister, new Wahrung(null, "EUR", "Euro", 2, false));
        save(persister, new Wahrung(null, "GBP", "Pound Serling", 2, false));
        save(persister, new Wahrung(null, "USD", "US Dollar", 2, false));
    }

    protected void populateZug() {
    }

    protected void populateZugConsist() {
    }

    protected void populateZugTyp() {
        IPersister<ZugTyp> persister = persisterFactory.createNamedItemPersister(ZugTyp.class);

        save(persister, new ZugTyp(null, "Gütterzug", "Gütterzug", false));
        save(persister, new ZugTyp(null, "Nahvekerszug", "Nahvekerszug", false));
        save(persister, new ZugTyp(null, "Bahndienstzug", "Bahndienstzug", false));
        save(persister, new ZugTyp(null, "Interregiozug", "Interregiozug", false));
        save(persister, new ZugTyp(null, "Intercityzug", "Intercityzug", false));
        save(persister, new ZugTyp(null, "TEE Zug", "TEE Zug", false));
        save(persister, new ZugTyp(null, "Militär Zug", "Militär Zug", false));
    }
    
    <E extends IItem> E save(IPersister<E> persister, E item) {
        try {
            return persister.save(item);
        } catch (Exception e) {
            logger.error("Failed to save " + item + " : " + e.getMessage(), e);
        }
        
        return null;
    }

    <E extends IItem> E findByKey(IPersister<E> persister, Object... keys) {
        try {
            return persister.findByKey(keys);
        } catch (Exception e) {
            logger.error("Error finding " + persister.getEntityClass().getSimpleName() + "(" + keys + ") : " + e.getMessage(), e);
        }
        
        return null;
    }
}