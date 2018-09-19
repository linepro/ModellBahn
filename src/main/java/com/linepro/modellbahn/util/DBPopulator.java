package com.linepro.modellbahn.util;

import javax.inject.Inject;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypAdress;
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
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.impl.Vorbild;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.model.impl.ZugTyp;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.model.util.Konfiguration;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.IPersisterFactory;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DBPopulator {

    private final IPersisterFactory persisterFactory;

    private final Logger logger;

    @Inject
    public DBPopulator(IPersisterFactory persisterFactory, ILoggerFactory logManger) {
        this.persisterFactory = persisterFactory;
        this.logger = logManger.getLogger(getClass().getName());
    }

    public void populate() {
        logger.info("Start population");

        populateAchsfolg();
        populateAntrieb();
        populateAufbau();
        populateBahnverwaltung();
        populateEpoch();
        populateGattung();
        populateHersteller();
        populateKupplung();
        populateLicht();
        populateMassstab();
        populateMotorTyp();
        populateProtokoll();
        populateSonderModell();
        populateSpurweite();
        populateSteuerung();

        populateKategorie();

        populateWahrung();
        populateLand();

        populateDecoderTyp();

        populateVorbild();

        populateDecoder();

        populateProdukt();
        populateProduktTeil();

        populateArtikel();

        populateZugTyp();
        populateZug();
        populateZugConsist();
    }

    protected void dump() {
        dump(Achsfolg.class);
        dump(Antrieb.class);
        dump(Aufbau.class);
        dump(Bahnverwaltung.class);
        dump(Epoch.class);
        dump(Gattung.class);
        dump(Hersteller.class);
        dump(Kupplung.class);
        dump(Licht.class);
        dump(Massstab.class);
        dump(MotorTyp.class);
        dump(Protokoll.class);
        dump(SonderModell.class);
        dump(Spurweite.class);
        dump(Steuerung.class);

        dump(Kategorie.class);

        dump(Wahrung.class);
        dump(Land.class);

        dump(DecoderTyp.class);

        dump(Vorbild.class);

        dump(Decoder.class);

        dump(Produkt.class);
        dump(ProduktTeil.class);

        dump(Artikel.class);

        dump(ZugTyp.class);
        dump(Zug.class);
        dump(ZugConsist.class);
    }

    protected void populateAchsfolg() {
        IPersister<Achsfolg> persister = persisterFactory.createPersister(Achsfolg.class);

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
        IPersister<Antrieb> persister = persisterFactory.createPersister(Antrieb.class);

        save(persister, new Antrieb(null, "Akku", "Akku", false));
        save(persister, new Antrieb(null, "Dampf", "Dampf", false));
        save(persister, new Antrieb(null, "Diesel", "Diesel", false));
        save(persister, new Antrieb(null, "Elektro", "Elektro", false));
        save(persister, new Antrieb(null, "Druckluft", "Druckluft", false));
    }

    protected void populateArtikel() {
    }

    protected void populateAufbau() {
        IPersister<Aufbau> persister = persisterFactory.createPersister(Aufbau.class);

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
        IPersister<Bahnverwaltung> persister = persisterFactory.createPersister(Bahnverwaltung.class);

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

    /**
     * Populate decoder typ.
     */
    protected void populateDecoderTyp() {
        IPersister<Protokoll> protokollLookup = persisterFactory.createPersister(Protokoll.class);

        IProtokoll delta = findByKey(protokollLookup, new NameKey("DELTA"));
        IProtokoll fx = findByKey(protokollLookup, new NameKey("fx"));
        IProtokoll mm = findByKey(protokollLookup, new NameKey("MM"));
        IProtokoll mfx = findByKey(protokollLookup, new NameKey("mfx"));
        IProtokoll weiche = findByKey(protokollLookup, new NameKey("Weiche"));

        IPersister<Hersteller> herstellerLookup = persisterFactory.createPersister(Hersteller.class);

        Hersteller digitalbahn = findByKey(herstellerLookup, new NameKey("Digital-Bahn"));
        Hersteller esu = findByKey(herstellerLookup, new NameKey("ESU"));
        Hersteller marklin = findByKey(herstellerLookup, new NameKey("Märklin"));
        Hersteller uhlenbrock = findByKey(herstellerLookup, new NameKey("Uhlenbrock"));

        IPersister<DecoderTyp> persister = persisterFactory.createPersister(DecoderTyp.class);

        addDSD2010(weiche, digitalbahn, persister);

        addLokPilotM4(mfx, esu, "61600", "LokPilot M4", persister);
        addLokPilotM4(mfx, esu, "61601", "LokPilot M4 21MTC", persister);
        addLokSoundM4(mfx, esu, "62400", "LokSound M4", persister);
        addLokSoundM4(mfx, esu, "62499", "LokSound M4 21MTC", persister);
        addLokPilotFX(mm, esu, "52620", "LokPilot FX", persister);
        addLokPilotFX(mm, esu, "52621", "LokPilot FX 21MTC", persister);
        addSwitchPilot(weiche, esu, "51800", "SwitchPilot", persister);
        addSwitchPilot(weiche, esu, "51820", "SwitchPilot 2", persister);
        addSwitchPilotServo(weiche, esu, persister);

        addMarklinSoundDecoder(mfx, marklin, "103787", "103787", persister);
        
        addMarklinDELTADecoder(mm, marklin, "49940", "49940", persister);
        addMarklinDELTADecoder(mm, marklin, "49961", "49961", persister);
        addMarklinDELTADecoder(delta, marklin, "602850", "602850", persister);
        addMarklinDELTADecoder(delta, marklin, "603999", "603999", persister);
        addMarklinDELTADecoder(mm, marklin, "606174", "606174", persister);
        addMarklinDELTADecoder(delta, marklin, "6603", "Delta Modul", persister);
        addMarklinDELTADecoder(delta, marklin, "66031", "Delta Modul mit Zusatzfunktion", persister);
        addMarklinDELTADecoder(delta, marklin, "66032", "Delta Modul mit automatischer Systemerkennung", persister);
        addMarklinDELTADecoder(delta, marklin, "670040", "670040", persister);
    
        add46715(fx, marklin, persister);
        add60760(fx, marklin, persister);
        add115798(fx, marklin, persister);
        add150436(fx, marklin, persister);
        add219574(fx, marklin, persister);
        add602756(fx, marklin, persister);
        add608862(fx, marklin, persister);
        add611105(fx, marklin, persister);
        add611754(fx, marklin, persister);

        add115166(mfx, marklin, persister);
        add115673(mfx, marklin, persister);
        add116836(mfx, marklin, persister);
        add123572(mfx, marklin, persister);
        add140131(mfx, marklin, persister);
        add148924(mfx, marklin, persister);
        add156787(mfx, marklin, persister);
        add162946(mfx, marklin, persister);
        add169274(mfx, marklin, persister);
        add253201(mfx, marklin, persister);
        add269706(mfx, marklin, persister);
        add39970(mfx, marklin, persister);
        add60902(mfx, marklin, persister);
        add611077(mfx, marklin, persister);

        add209394(mm, marklin, persister);
        add42973(mm, marklin, persister);
        add49960(mm, marklin, persister);
        add606896(mm, marklin, persister);
        add608825(mm, marklin, persister);
    
        addWeicheDecoder(weiche, marklin, "74460", "Einbau-Digital-Decoder", persister);
    
        addDrehscheibendekoder(weiche, marklin, persister);
    
        addUhlenbrock67900(mm, uhlenbrock, persister);
    }

    protected DecoderTyp add60760(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "60760", "Hochleistungsdecoder", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, 16, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add46715(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "46715", "46715", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Kranhaus drehen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Kranausleger Heben heben", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Haken heben", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addWeicheDecoder(IProtokoll weiche, Hersteller marklin, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, weiche, bestellNr, bezeichnung, false, Konfiguration.SWITCH, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.WEICHE, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 255, null, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addDrehscheibendekoder(IProtokoll weiche, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, weiche, "7687", "Drehscheibendekoder", false, Konfiguration.LINK, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.WEICHE, 1, 16, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 14, 15, 14, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "End", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Input", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Clear", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "180", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "Step >", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Step <", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Spoke 1", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Spoke 2", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Spoke 3", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addUhlenbrock67900(IProtokoll mm, Hersteller uhlenbrock, IPersister<DecoderTyp> persister) {
            DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, uhlenbrock, mm, "67900", "67900", false, Konfiguration.SWITCH, false));

            decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.MM, 1, 8, false));

            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 127, 3, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Minimale Geschwindigkeit", 1, 63, 5, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, 2, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, 2, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Maximale Geschwindigkeit", 1, 63, 20, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 6, "Maximale Motorspannung", 1, 255, 64, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 7, "Softwareversion", null, null, null, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Herstellerkennung", null, null, 85, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 17, "Lange Lokadresse MSB", 192, 231, 199, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 18, "Lange Lokadresse LSB", 0, 255, 208, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 20, "Konfiguration beider Motoren nach DCC-Norm", 0, 33, 0, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 49, "Decoder-Konfiguration", 0, 195, 0, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 65, "Motorola Programmierung Reihe", 0, 255, 0, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 67, "Maximale Geschwindigkeit für Taste 1", 0, 255, 40, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 68, "Maximale Geschwindigkeit für Taste 2", 0, 255, 40, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 69, "Maximale Geschwindigkeit für Taste 3", 0, 255, 50, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 70, "Maximale Geschwindigkeit für Taste 4", 0, 255, 50, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 71, "Anfahrverzögerung für Taste 1", 0, 255, 5, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 72, "Anfahrverzögerung für Taste 2", 0, 255, 5, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 73, "Anfahrverzögerung für Taste 3", 0, 255, 5, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 74, "Anfahrverzögerung für Taste 4", 0, 255, 5, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 75, "Bremsverzögerung für Taste 1", 0, 255, 1, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 76, "Bremsverzögerung für Taste 2", 0, 255, 1, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 77, "Bremsverzögerung für Taste 3", 0, 255, 1, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 78, "Bremsverzögerung für Taste 4", 0, 255, 1, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 79, "Maximale Motorspannung im Analogbetrieb", 0, 255, 180, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 98, "Zeitbegrenztes Einschalten der Ausgänge A1 + A2", 0, 3, 3, false));
            decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 99, "Maximale Einschaltzeit in Sekunden", 0, 255, 45, false));

            decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Kranhaken/Laufkatze", false, false));
            decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "F1", false, false));
            decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "F2", false, false));
            decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "F3", false, false));
            decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "F4", false, false));

            return update(persister, decoderTyp);
    }
    
    private DecoderTyp addMarklinDELTADecoder(IProtokoll mm, Hersteller marklin, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mm, bestellNr, bezeichnung, false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DELTA, 1, 80, false));
        
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 11, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addSwitchPilotServo(IProtokoll weiche, Hersteller esu, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, esu, weiche, "51802", "SwitchPilot Servo", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.WEICHE, 1, 4, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Decoderadresse 1, LSB", 1, 63, 1, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 7, "Versionsnummer", null, null, 153, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Herstellerkennung", null, null, 151, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 9, "Decoderadresse 1, MSB", 0, 7, 0, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 28, "RailCom Konfiguration", 0, 6, 0, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 29, "Konfigurationsregister", 128, 136, 128, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 38, "Servo 1, Stellung „A“", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 39, "Servo 1, Stellung „B“", 1, 63, 40, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 41, "Servo 2, Stellung „A“", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 42, "Servo 2, Stellung „B“", 1, 63, 40, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 43, "Servo 3, Drehgeschwindigkeit", 1, 63, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 44, "Servo 3, Stellung „A“", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 45, "Servo 3, Stellung „B“", 1, 63, 40, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 46, "Servo 4, Drehgeschwindigkeit", 1, 63, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 47, "Servo 4, Stellung „A“", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 48, "Servo 4, Stellung „B“", 1, 63, 40, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "S1", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "S2", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "S3", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "S4", "", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addSwitchPilot(IProtokoll weiche, Hersteller esu, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, esu, weiche, bestellNr, bezeichnung, false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.WEICHE, 1, 8, false));
        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 2, AdressTyp.WEICHE, 1, 2, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Decoderadresse 1, LSB", 1, 63, 1, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Konfiguration Ausgang 1", 0, 64, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Konfiguration Ausgang 2", 0, 64, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Konfiguration Ausgang 3", 0, 64, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 6, "Konfiguration Ausgang 4", 0, 64, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 7, "Versionsnummer", null, null, 115, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Herstellerkennung", null, null, 151, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 9, "Decoderadresse 1, MSB", 0, 7, 0, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 28, "RailCom Konfiguration", 0, 6, 0, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 29, "Konfigurationsregister", 128, 136, 128, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 33, "Funktionsausgangsstatus", 0, 255, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 34, "„Zoom“-Konfiguration", 0, 15, 0, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 35, "Decoderadresse 2, LSB", 1, 63, 1, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 36, "Decoderadresse 2, MSB", 0, 8, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 38, "Servo 1, Stellung „A“", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 39, "Servo 1, Stellung „B“", 1, 63, 40, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 41, "Servo 2, Stellung „A“", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 42, "Servo 2, Stellung „B“", 1, 63, 40, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K1", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K2", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K3", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K4", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "S1", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "S2", "", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addLokPilotFX(IProtokoll mm, Hersteller esu, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, esu, mm, bestellNr, bezeichnung, false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));
        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 2, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addLokSoundM4(IProtokoll mfx, Hersteller esu, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, esu, mfx, bestellNr, bezeichnung, true, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));
        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 2, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Anfahrverzögerung", 1, 63, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Beschleunigungszeit", 1, 63, 16, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, 12, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 53, "Regelungsreferenz", 1, 63, 56, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 54, "Lastregelung Param. K", 1, 63, 32, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 55, "Lastregelung Param. L", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 56, "Regelungseinfluss", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 57, "Geräuschmodus 1", 1, 63, 10, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 58, "Geräuschmodus 2", 1, 63, 58, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 59, "Fahrgeräusch", 1, 63, 32, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 60, "Fahrgeräusch", 1, 63, 55, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Geräuschlautstärke", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 64, "Bremssoundschwelle", 1, 63, 07, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 73, "Speicheroptionen", 0, 7, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 74, "Märklin Addresse 2", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 75, "Märklin Addresse 2", 1, 80, 04, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F11", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F12", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F13", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F14", "", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F15", "", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addLokPilotM4(IProtokoll mfx, Hersteller esu, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, esu, mfx, bestellNr, bezeichnung, false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Anfahrverzögerung", 1, 63, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Beschleunigungszeit", 1, 63, 16, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, 12, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 53, "Regelungsreferenz", 1, 63, 45, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 54, "Lastregelung Param. K", 1, 63, 32, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 55, "Lastregelung Param. L", 1, 63, 24, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 56, "Regelungseinfluss", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 73, "Speicheroptionen", 0, 7, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 75, "Märklin Addresse 2", 1, 80, 04, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addMarklinSoundDecoder(IProtokoll mfx, Hersteller marklin, String bestellNr, String bezeichnung, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, bestellNr, bezeichnung, false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 10, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Spitzensignal", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Rauchgenerator", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Lokpfeife", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Fahrgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Luftpumpe", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Triebwerksbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Bremsenquietschen aus", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Rangierpfeife", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Dampf ablassen", false, false));

        return update(persister, decoderTyp);
    }
    protected DecoderTyp add115798(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "115798", "115798", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 255, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Anfahrverzögerung/Bremsverzögerung", 1, 31, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add150436(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "150436", "150436", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 255, 38, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add219574(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "219574", "219574", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 45, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Rauchgenerator", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F11", "Schüttelrost", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Lokpfeife", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Dampftriebwerk", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Luftpumpe", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Feuerschein - Feuerbüchse", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Rangierpfiff", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Dampf ablassen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "Kohleschaufeln", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add602756(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "602756", "602756", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Schleuderrad Geräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Schleuderrad", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Pfeife", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "Signalstreckenlampen", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add608862(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "608862", "608862", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 10, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Maschinenraumbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Stromabnehmer vorn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Stromabnehmer hinten", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add611105(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "611105", "611105", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 71, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Bedienung festgelegt", 0, 1, 0, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Baggerschaufel Zeitbegrenzung", 0, 1, 0, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Kohleschaufe schließen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Laufgestell Motoren", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Führerhaus Beleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Kohleschaufe Heben", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "Führerhaus Drehen", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add611754(IProtokoll fx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, fx, "611754", "611754", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Telex-Kupplung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add115166(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "115166", "115166", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung / Innenbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Schlusslicht ausschalten", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Dieselmotor und Bremse", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Signalhorn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Türe Zu", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Glocke", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Abfahrtspfiff", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add115673(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "115673", "115673", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 64, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, 63, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Rauchgenerator", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Betriebsgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Pfeife", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Luftpumpe", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Kohleschaufeln", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Glocke", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Dampf ablassen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "Schüttelrost", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add116836(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "116836", "116836", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 70, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Betriebsgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Signalhorn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Metallsäge", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Hämmern", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Winkelschleifer", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Elektroschweißen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "Schleifbock", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add123572(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "123572", "123572", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 0, 80, 42, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 0, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 0, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 0, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 0, 63, 63, false));


        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung / Innenbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Bahnhofsansage", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Signalhorn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add140131(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "140131", "140131", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung / Schlusslicht", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Schlusslicht ausschalten", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Betriebsgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Signalhorn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Druckluft ablassen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Bremsenquietschen", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add148924(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "148924", "148924", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));


        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Schlusslicht ausschalten", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Betriebsgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Horn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Druckluft ablassen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Führerstandsbeleuchtung vorn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Führerstandsbeleuchtung hinten", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add156787(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "156787", "156787", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 49, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Innenbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Begrüßungs-Ansage", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Pfeife", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Innenbeleuchtung dimmen", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add162946(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "162946", "162946", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 11, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Innenbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Betriebsgeräusch ", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Horn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Bahnhofsansage", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Türe zu", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Schaffnerpfiff", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Hilfsdiesel", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "Lüfter", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F11", "Kompressor", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F12", "Überdruckventil", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F13", "Druckluft ablassen", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add169274(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "169274", "169274", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 0, 80, 43, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 0, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 0, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 0, 63, 63, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 0, 63, 63, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Tischlampen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Betriebsgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Horn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Bremsenquietschen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Schaffnerpfiff", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Türen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Bahnhofsansage", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Rangierpfiff", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add253201(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "253201", "253201", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Innenbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Start / Stopp", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Pause", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "ein Lied vor", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ein Lied zurück", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Lauter", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Leiser", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Lichtorgel an / aus", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Barbeleuchtung an / aus", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Strom führende Kupplung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "Stroboskop", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F11", "Umgebungsgeräusch 1", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F12", "Umgebungsgeräusch 2", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F13", "Betriebsgeräusch 1", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F14", "Betriebsgeräusch 2", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F15", "Betriebsgeräusch 3", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add269706(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "269706", "269706", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 50, "Protokolle", 1, 15, 15, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Spitzensignal", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Innenbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Fahrgeräusch", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Signalton", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "Direktsteuerung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F5", "Bremsenquietschen aus", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F6", "Spitzensignal Lokseite 2", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F7", "Schaffnerpfiff", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F8", "Spitzensignal Lokseite 1", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F9", "Türenschließen", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F10", "Schienenstoß", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F11", "Bahnhofsansage", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F12", "Dialog", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F13", "Dialog", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F14", "Dialog", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F15", "Dialog ", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add39970(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "39970", "39970", true, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Arbeitsbühne heben", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Arbeitsbühne schwenken", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Stromabnehmer", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "Initialisierung", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add60902(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "60902", "Hochleistungselektronik", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Anfahrverzögerung", 1, 63, 03, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add611077(IProtokoll mfx, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, mfx, "611077", "611077", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add209394(IProtokoll protokoll, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, protokoll, "209394", "209394", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", null, null, 54, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add42973(IProtokoll protokoll, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, protokoll, "42973", "42973", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, 63, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Pantograf", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Geräusch einer Schaffner", false, false));

        return update(persister, decoderTyp);
    }


    protected DecoderTyp add49960(IProtokoll protokoll, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, protokoll, "49960", "49960", true, Konfiguration.SWITCH, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F1", "Meßbereich", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F2", "Meßbereich", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F3", "Maßeinheit", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "Anzeigen", false, false));

        return update(persister, decoderTyp);
    }


    protected DecoderTyp add606896(IProtokoll protokoll, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, protokoll, "606896", "606896", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F0", "Strinbeleuchtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "F4", "ABV", false, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp add608825(IProtokoll protokoll, Hersteller marklin, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, marklin, protokoll, "608825", "608825", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.DIGITAL, 1, 1, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "Adresse", 1, 80, 39, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Anfahrverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 4, "Bremsverzögerung", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 63, "Lautstärke", 1, 63, null, false));

        return update(persister, decoderTyp);
    }

    protected DecoderTyp addDSD2010(IProtokoll weiche, Hersteller digitalbahn, IPersister<DecoderTyp> persister) {
        DecoderTyp decoderTyp = save(persister, new DecoderTyp(null, digitalbahn, weiche, "DSD2010", "Drehscheibendekoder", false, Konfiguration.CV, false));

        decoderTyp.addAdress(new DecoderTypAdress(null, decoderTyp, 1, AdressTyp.WEICHE, 1, 16, false));

        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 1, "48 / 24 Positions", 0, 1, 1, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 2, "DCC / Motorola", 0, 1, 1, false));
        decoderTyp.addCV(new DecoderTypCV(null, decoderTyp, 3, "Position specification", 0, 1, 0, false));

        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K0", "Licht", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K1", "Step", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K2", "Turn", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K3", "Richtung", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K4", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K5", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K6", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K7", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K8", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K9", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K10", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K11", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K12", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K13", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K14", "Position", false, false));
        decoderTyp.addFunktion(new DecoderTypFunktion(null, decoderTyp, 0, "K15", "Position", false, false));

        return update(persister, decoderTyp);
    }

    protected void populateEpoch() {
        IPersister<Epoch> persister = persisterFactory.createPersister(Epoch.class);

        save(persister, new Epoch(null, "I", "1835 - 1920", false));
        save(persister, new Epoch(null, "II", "1920 - 1950", false));
        save(persister, new Epoch(null, "III", "1949 - 1970", false));
        save(persister, new Epoch(null, "IV", "1965 - 1990", false));
        save(persister, new Epoch(null, "V", "1990 - 2006", false));
        save(persister, new Epoch(null, "VI", "2006 -", false));
        save(persister, new Epoch(null, "Ia", "1835 - 1875", false));
        save(persister, new Epoch(null, "Ib", "1875 - 1895", false));
        save(persister, new Epoch(null, "Ic", "1895 - 1910", false));
        save(persister, new Epoch(null, ApiNames.ID, "1910 - 1920", false));
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
        IPersister<Gattung> persister = persisterFactory.createPersister(Gattung.class);

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
        IPersister<Hersteller> persister = persisterFactory.createPersister(Hersteller.class);

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
        IPersister<Kategorie> persister = persisterFactory.createPersister(Kategorie.class);
        
        Kategorie kategorie = save(persister, new Kategorie(null, "Ausgestaltung", "Ausgestaltung", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Ausgestaltung", "Ausgestaltung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Blühmen", "Blühmen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Bäume", "Bäume", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Büsche", "Büsche", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Figuren", "Figuren", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Hecken", "Hecken", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Ladegut", "Ladegut", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Pflanzen", "Pflanzen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Zeichen", "Zeichen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Zäune", "Zäune", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Beleuchtung", "Beleuchtung", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Beleuchtung", "Beleuchtung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gluehlampe", "Gluehlampe", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Innenbeleuchtung", "Innenbeleuchtung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Leuchteinsatz", "Leuchteinsatz", false));
        kategorie.addUnterKategorie(
                new UnterKategorie(null, kategorie, "Stromführendekupplungen", "Stromführendekupplungen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Stromzuführung", "Stromzuführung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Zugschlussbeleuchtung", "Zugschlussbeleuchtung", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Decoder", "Decoder", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Decoder", "Decoder", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Lautsprecher", "Lautsprecher", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Ersatzteil", "Ersatzteil", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Anker", "Anker", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Beschwerung", "Beschwerung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Drehgestellrahmen", "Drehgestellrahmen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Drehgestell", "Drehgestell", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Entstördrossel", "Entstördrossel", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Ersatzteil", "Ersatzteil", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Feder", "Feder", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Feldmagnet", "Feldmagnet", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Fenster", "Fenster", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Grundplatte", "Grundplatte", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Haftreifen", "Haftreifen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Halteplatte", "Halteplatte", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Inneneinrichtung", "Inneneinrichtung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Isolierung", "Isolierung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kabel", "Kabel", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kabelklemmen", "Kabelklemmen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Klappe", "Klappe", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kohlbursten", "Kohlbursten", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kuppelstange", "Kuppelstange", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, ApiNames.KUPPLUNG, ApiNames.KUPPLUNG, false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kupplungsdeichsel", "Kupplungsdeichsel", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kupplungshaken", "Kupplungshaken", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kupplungskinematik", "Kupplungskinematik", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kupplungsschacht", "Kupplungsschacht", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kurzkupplung", "Kurzkupplung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Leitschaufel", "Leitschaufel", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Lokumbausätze", "Lokumbausätze", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Massefeder", "Massefeder", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Messingblech", "Messingblech", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Motorschild", "Motorschild", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Mutter", "Mutter", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Pantograph", "Pantograph", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Prallplatte", "Prallplatte", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Puffer", "Puffer", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Rad", "Rad", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Relais", "Relais", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Relexkupplung", "Relexkupplung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schaltsfeder", "Schaltsfeder", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schleifer", "Schleifer", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schraube", "Schraube", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schraubenkupplung", "Schraubenkupplung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Senkschraube", "Senkschraube", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Stange", "Stange", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Telex", "Telex", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Traeger", "Traeger", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Wagenboden", "Wagenboden", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Weichenfeder", "Weichenfeder", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Zugfeder", "Zugfeder", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Zylinderschraube", "Zylinderschraube", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Fahrzeug", "Fahrzeug", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Fahrzeug", "Fahrzeug", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Gebaüde", "Gebaüde", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Bekohlung", "Bekohlung", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Bockkrän", "Bockkrän", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Drehscheibe", "Drehscheibe", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gebaüde", "Gebaüde", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Gleismateriel", "Gleismateriel", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gleismateriel", "Gleismateriel", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Landschaftsbau", "Landschaftsbau", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Landschaftsbau", "Landschaftsbau", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Lokomotiv", "Lokomotiv", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Lokomotiv", "Lokomotiv", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Akku", "Akku", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Dampf", "Dampf", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Diesel", "Diesel", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Elektro", "Elektro", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Oberleitung", "Oberleitung", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Oberleitung", "Oberleitung", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Set", "Set", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Set", "Set", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Signaltechnik", "Signaltechnik", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Signalbirne", "Signalbirne", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Signaltechnik", "Signaltechnik", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Sonstiges", "Sonstiges", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Sonstiges", "Sonstiges", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Steuerungstechnik", "Steuerungstechnik", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Steuerungstechnik", "Steuerungstechnik", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Stromversorgung", "Stromversorgung", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Treibwagen", "Treibwagen", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Beiwagen", "Beiwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Mittelwagen", "Mittelwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Steurwagen", "Steurwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Treibwagen", "Treibwagen", false));

        update(persister, kategorie);

        kategorie = save(persister, new Kategorie(null, "Wagen", "Wagen", false));

        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Abteilwagen", "Abteilwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Aussichtswagen", "Aussichtswagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Autotransportwagen", "Autotransportwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Bahndienstwagen", "Bahndienstwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Bananenwagen", "Bananenwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Barwagen", "Barwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Behältertragwagen", "Behältertragwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Bierwagen", "Bierwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Carbid-Flaschenwagen", "Carbid-Flaschenwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Containertragwagen", "Containertragwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Doppelstockwagen", "Doppelstockwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Drehschemelwagen", "Drehschemelwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Fahrradtransportwagen", "Fahrradtransportwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Flachwagen", "Flachwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gaswagen", "Gaswagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gedeckter Güterwagen", "Gedeckter Güterwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gepäckwagen", "Gepäckwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Gesellschaftswagen", "Gesellschaftswagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Großraumwagen", "Großraumwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Güterwagen", "Güterwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Güterzugbegleitwagen", "Güterzugbegleitwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Hochbordwagen", "Hochbordwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kesselwagen", "Kesselwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kränwagen", "Kränwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Kühlwagen", "Kühlwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Mannschaftswagen", "Mannschaftswagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Messewagen", "Messewagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Nahverkehrswagen", "Nahverkehrswagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Niederbordwagen", "Niederbordwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Rolldachwagen", "Rolldachwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schiebewandwagen", "Schiebewandwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schneepflug", "Schneepflug", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schotterwagen", "Schotterwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schwerlastwagen", "Schwerlastwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Schüttgut-Kippwagen", "Schüttgut-Kippwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Seitenentladewagen", "Seitenentladewagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Silowagen", "Silowagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Sonderfahrzeug", "Sonderfahrzeug", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Speisewagen", "Speisewagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Taschenwagen", "Taschenwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Tiefladewagen", "Tiefladewagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Torpedopfannenwagen", "Torpedopfannenwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Umbauwagen", "Umbauwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Verschlagwagen", "Verschlagwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Viehwagen", "Viehwagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Wagen", "Wagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Weihnachtswagen", "Weihnachtswagen", false));
        kategorie.addUnterKategorie(new UnterKategorie(null, kategorie, "Weinwagen", "Weinwagen", false));

        update(persister, kategorie);

        Kategorie werkzeug = save(persister, new Kategorie(null, "Werkzeug", "Werkzeug", false));

        werkzeug.addUnterKategorie(new UnterKategorie(null, werkzeug, "Bücher", "Bücher", false));
        werkzeug.addUnterKategorie(new UnterKategorie(null, werkzeug, "Farbe", "Farbe", false));
        werkzeug.addUnterKategorie(new UnterKategorie(null, werkzeug, "Kleb", "Kleb", false));
        werkzeug.addUnterKategorie(new UnterKategorie(null, werkzeug, "Werkzeug", "Werkzeug", false));

        save(persister, werkzeug);

        Kategorie zubehor = save(persister, new Kategorie(null, "Zubehör", "Zubehör", false));

        zubehor.addUnterKategorie(new UnterKategorie(null, zubehor, "Beschriftigung", "Beschriftigung", false));
        zubehor.addUnterKategorie(new UnterKategorie(null, zubehor, "Zubehör", "Zubehör", false));

        save(persister, zubehor);
    }

    protected void populateKupplung() {
        IPersister<Kupplung> persister = persisterFactory.createPersister(Kupplung.class);

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
        IPersister<Wahrung> wahrungLookup = persisterFactory.createPersister(Wahrung.class);
        IPersister<Land> persister = persisterFactory.createPersister(Land.class);
        
        IWahrung aud = findByKey(wahrungLookup, new NameKey("AUD"));
        IWahrung eur = findByKey(wahrungLookup, new NameKey("EUR"));
        IWahrung gbp = findByKey(wahrungLookup, new NameKey("GBP"));
        IWahrung usd = findByKey(wahrungLookup, new NameKey("USD"));
        
        save(persister, new Land(null, "AU", "Australien", aud, false));
        save(persister, new Land(null, "BE", "Belgien", eur, false));
        save(persister, new Land(null, "DE", "Deutschland", eur, false));
        save(persister, new Land(null, "FR", "Frankreich", eur, false));
        save(persister, new Land(null, "IT", "Italien", eur, false));
        save(persister, new Land(null, "NL", "Niederland", eur, false));
        save(persister, new Land(null, "UK", "Vereinigtes Königreich", gbp, false));
        save(persister, new Land(null, "US", "Vereinigte Staaten", usd, false));
    }

    protected void populateLicht() {
        IPersister<Licht> persister = persisterFactory.createPersister(Licht.class);

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
        IPersister<Massstab> persister = persisterFactory.createPersister(Massstab.class);

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
        IPersister<MotorTyp> persister = persisterFactory.createPersister(MotorTyp.class);

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
        IPersister<Protokoll> persister = persisterFactory.createPersister(Protokoll.class);

        save(persister, new Protokoll(null, "DELTA", "Märklin DELTA", false));
        save(persister, new Protokoll(null, "fx", "Märklin fx", false));
        save(persister, new Protokoll(null, "mfx", "Märklin mfx", false));
        save(persister, new Protokoll(null, "DCC", "DCC", false));
        save(persister, new Protokoll(null, "MM", "Märklin Motorola", false));
        save(persister, new Protokoll(null, "Weiche", "Märklin Motorola Weiche", false));
    }

    protected void populateSonderModell() {
        IPersister<SonderModell> persister = persisterFactory.createPersister(SonderModell.class);

        save(persister, new SonderModell(null, "Märklin Magazin", "Märklin Magazin", false));
        save(persister, new SonderModell(null, "Märklin Insider", "Märklin Insider", false));
        save(persister, new SonderModell(null, "MHI Exclusiv", "Märklin Handler Initiative", false));
        save(persister, new SonderModell(null, "MM Jahreswagen", "Märklin Magazin Jahreswagen", false));
        save(persister, new SonderModell(null, "KC Jahreswagen", "Märklin Kids Club Jahreswagen", false));
        save(persister, new SonderModell(null, "Einmalige Serien", "Einmalige Serien", false));
        save(persister, new SonderModell(null, "Museumswagen", "Museumswagen", false));
        save(persister, new SonderModell(null, "Weihnachtswagen", "Weihnachtswagen", false));
        save(persister, new SonderModell(null, ApiNames.SONDERMODELL, ApiNames.SONDERMODELL, false));
    }

    protected void populateSpurweite() {
        IPersister<Spurweite> persister = persisterFactory.createPersister(Spurweite.class);

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
        IPersister<Steuerung> persister = persisterFactory.createPersister(Steuerung.class);

        save(persister, new Steuerung(null, "digital", "digital", false));
        save(persister, new Steuerung(null, "mechanisch", "mechanisch (FRU)", false));
        save(persister, new Steuerung(null, "Umschaltelektronik", "Umschaltelektronik", false));
    }

    protected void populateVorbild() {
    }

    protected void populateWahrung() {
        IPersister<Wahrung> persister = persisterFactory.createPersister(Wahrung.class);

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
        IPersister<ZugTyp> persister = persisterFactory.createPersister(ZugTyp.class);

        save(persister, new ZugTyp(null, "Gütterzug", "Gütterzug", false));
        save(persister, new ZugTyp(null, "Nahvekerszug", "Nahvekerszug", false));
        save(persister, new ZugTyp(null, "Bahndienstzug", "Bahndienstzug", false));
        save(persister, new ZugTyp(null, "Interregiozug", "Interregiozug", false));
        save(persister, new ZugTyp(null, "Intercityzug", "Intercityzug", false));
        save(persister, new ZugTyp(null, "TEE Zug", "TEE Zug", false));
        save(persister, new ZugTyp(null, "Militär Zug", "Militär Zug", false));
    }

    <E extends IItem<?>> E findByKey(IPersister<E> persister, IKey key) {
        try {
            return persister.findByKey(key, false);
        } catch (Exception e) {
            logger.error("Error finding " + key, e);
        }
        
        return null;
    }

    <E extends IItem<?>> E save(IPersister<E> persister, E item) {
        try {
            return persister.save(item);
        } catch (Exception e) {
            logger.error("Failed to save " + item + " : " + e.getMessage(), e);
        }
        
        return null;
    }

    <E extends IItem<?>> E update(IPersister<E> persister, E item) {
        try {
            return persister.update(item);
        } catch (Exception e) {
            logger.error("Failed to save " + item + " : " + e.getMessage(), e);
        }
        
        return null;
    }

    <E extends IItem<?>> void dump(Class<E> entityClass) {
        try {
            IPersister<E> persister = persisterFactory.createPersister(entityClass);
        
            int i = 0;
    
            for (E item : persister.findAll()) {
                i++;
                System.out.println(item);
            }
            
            logger.debug("dumped: " + i + " " + entityClass.getSimpleName());
        } catch (Exception e) {
            logger.error("dump error: " + entityClass.getSimpleName());
        }
    }
}