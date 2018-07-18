package com.linepro.modellbahn.util;

import javax.inject.Inject;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IProtokoll;
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
import com.linepro.modellbahn.persistence.INamedItemPersister;
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
        populateEpoch();
        populateGattung();
        populateHersteller();
        populateKategorie();
        populateKupplung();
        populateLicht();
        populateMassstab();
        populateMotorTyp();
        populateProtokoll();
        populateSonderModell();
        populateSpurweite();
        populateSteuerung();
        populateWahrung();
        populateZugTyp();

        populateLand();

        populateDecoderTyp();
        populateDecoderTypCV();
        populateDecoderTypFunktion();
        
        populateUnterKategorie();
        populateVorbild();

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
    
        persister.save(new Achsfolg( null, "(1'C)D 3'2'T35", "(1'C)D 3'2'T35", false));
        persister.save(new Achsfolg( null, "1'C 1' h2t", "1'C 1' h2t", false));
        persister.save(new Achsfolg( null, "1'C h2 3T16", "1'C h2 3T16", false));
        persister.save(new Achsfolg( null, "1'D 1' h2t", "1'D 1' h2t", false));
        persister.save(new Achsfolg( null, "1'E 1' h3 2'3'T28", "1'E 1' h3 2'3'T28", false));
        persister.save(new Achsfolg( null, "1'E h2 2'2T26 KAB5", "1'E h2 2'2T26 KAB5", false));
        persister.save(new Achsfolg( null, "1'E1' h2t", "1'E1' h2t", false));
        persister.save(new Achsfolg( null, "2'C 1' h3 2'2'T26", "2'C 1' h3 2'2'T26", false));
        persister.save(new Achsfolg( null, "2'C1' 2'2'T26", "2'C1' 2'2'T26", false));
        persister.save(new Achsfolg( null, "2'C1' h3 2'2'T40", "2'C1' h3 2'2'T40", false));
        persister.save(new Achsfolg( null, "A1 dm", "A1 dm", false));
        persister.save(new Achsfolg( null, "AA dm", "AA dm", false));
        persister.save(new Achsfolg( null, "B drn", "B drn", false));
        persister.save(new Achsfolg( null, "B h2t", "B h2t", false));
        persister.save(new Achsfolg( null, "B'2' dh 2'2' 2'2' 2'B' dh", "B'2' dh 2'2' 2'2' 2'B' dh", false));
        persister.save(new Achsfolg( null, "B'B' de", "B'B' de", false));
        persister.save(new Achsfolg( null, "B'B' dh", "B'B' dh", false));
        persister.save(new Achsfolg( null, "B'B'", "B'B'", false));
        persister.save(new Achsfolg( null, "Bo'2' e", "Bo'2' e", false));
        persister.save(new Achsfolg( null, "Bo'Bo'+2'2'+Bo'Bo'", "Bo'Bo'+2'2'+Bo'Bo'", false));
        persister.save(new Achsfolg( null, "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", false));
        persister.save(new Achsfolg( null, "C dh", "C dh", false));
        persister.save(new Achsfolg( null, "C h2t", "C h2t", false));
        persister.save(new Achsfolg( null, "C'C' dh", "C'C' dh", false));
        persister.save(new Achsfolg( null, "Co'Co' w6gf", "Co'Co' w6gf", false));
        persister.save(new Achsfolg( null, "Co'Co'", "Co'Co'", false));
        persister.save(new Achsfolg( null, "D h2t", "D h2t", false));
        persister.save(new Achsfolg( null, "D'D h4vt", "D'D h4vt", false));
    }
    
    protected void populateAdress() throws Exception {
    }

    protected void populateAdressTyp() throws Exception {
        IPersister<AdressTyp> persister = persisterFactory.createNamedItemPersister(AdressTyp.class);
        
        

    }

    protected void populateAntrieb() throws Exception {
        IPersister<Antrieb> persister = persisterFactory.createNamedItemPersister(Antrieb.class);

        persister.save(new Antrieb( null, "Akku", "Akku", false));
        persister.save(new Antrieb( null, "Dampf", "Dampf", false));
        persister.save(new Antrieb( null, "Diesel", "Diesel", false));
        persister.save(new Antrieb( null, "Elektro", "Elektro", false));
        persister.save(new Antrieb( null, "Druckluft", "Druckluft", false));
    }

    protected void populateArtikel() throws Exception {
    }
    
    protected void populateAufbau() throws Exception {
        IPersister<Aufbau> persister = persisterFactory.createNamedItemPersister(Aufbau.class);

        persister.save(new Aufbau( null, "Lok Kunststoff", "Fahrgestell der Lok aus Metall", false));
        persister.save(new Aufbau( null, "Lok Metall / Kunststoff", "Fahrgestell und vorwiegender Aufbau der Loks aus Metall", false));
        persister.save(new Aufbau( null, "Lok Metall", "Fahrgestell und Aufbau der Lokomotive aus Metall", false));
        persister.save(new Aufbau( null, "Wagen Kunststoff", "Fahrgestell des Wagens aus Metall", false));
        persister.save(new Aufbau( null, "Wagen Metall / Kunststoff", "Überwiegender Teil des Wagenaufbaus aus Metall", false));
        persister.save(new Aufbau( null, "Wagen Metall", "Fahrgestell und Aufbau des Wagens aus Metall", false));
        persister.save(new Aufbau( null, "Lok Kunststoff / Metall", "Überwiegender Teil des Lokaufbaues aus Metall", false));
    }

    protected void populateBahnverwaltung() throws Exception {
        IPersister<Bahnverwaltung> persister = persisterFactory.createNamedItemPersister(Bahnverwaltung.class);
        
        persister.save(new Bahnverwaltung(null, "AAE", "AAE", false));
        persister.save(new Bahnverwaltung(null, "ADtranz", "ABB Daimler-Benz Transportation (ADtranz)", false));
        persister.save(new Bahnverwaltung(null, "AL", "Anhaltische Leopoldsbahn (AL)", false));
        persister.save(new Bahnverwaltung(null, "ALNO", "ALNO Küchen", false));
        persister.save(new Bahnverwaltung(null, "AMTRAK", "American and Track (AMTRAK)", false));
        persister.save(new Bahnverwaltung(null, "AR", "Alaska Railroad (ARR)", false));
        persister.save(new Bahnverwaltung(null, "AT+SF", "Atchison, Topeka and Santa Fe Railway (AT+SF)", false));
        persister.save(new Bahnverwaltung(null, "AVE", "Alta Velocidad Española (AVE)", false));
        persister.save(new Bahnverwaltung(null, "AVG", "Albtal-Verkehrs-Gesellschaft mbH (AVG)", false));
        persister.save(new Bahnverwaltung(null, "Alaska", "Alaska Railroad (ARR)", false));
        persister.save(new Bahnverwaltung(null, "Alusuisse", "Schweizerische Aluminium AG (Alusuisse)", false));
        persister.save(new Bahnverwaltung(null, "B+A", "Boston and Albany Railway (B+A)", false));
        persister.save(new Bahnverwaltung(null, "B+O", "Baltomore and Ohio Railway (B+0)", false));
        persister.save(new Bahnverwaltung(null, "BBÖ", "Österreichischen Bundesbahnen (ÖBB)", false));
        persister.save(new Bahnverwaltung(null, "BHE", "Bebra-Hanauer Eisenbahn (BHE)", false));
        persister.save(new Bahnverwaltung(null, "BLS", "Bern–Lötschberg–Simplon railway (BLS)", false));
        persister.save(new Bahnverwaltung(null, "BN", "Burlington Northern Railroad (BN)", false));
        persister.save(new Bahnverwaltung(null, "BVG", "Berliner Verkehrsbetriebe (BVG)", false));
        persister.save(new Bahnverwaltung(null, "BuH", "Ruhrkohle AG Bahn und Hafen GmbH (RAG/BuH)", false));
        persister.save(new Bahnverwaltung(null, "C+G", "Columbus and Greenville Railway (C+G)", false));
        persister.save(new Bahnverwaltung(null, "CB+Q", "Chicago, Burlington and Quincy Railroad (CBQ)", false));
        persister.save(new Bahnverwaltung(null, "CFL", "Société Nationale des Chemins de Fer Luxembourgeois (CFL)", false));
        persister.save(new Bahnverwaltung(null, "CFV3V", "Chemin de Fer à Vapeur des 3 Vallées (CFV3V)", false));
        persister.save(new Bahnverwaltung(null, "CIWL", "Compagnie Internationale des Wagons-Lits (CIWL)", false));
        persister.save(new Bahnverwaltung(null, "CN", "Canadian National Railway (CN)", false));
        persister.save(new Bahnverwaltung(null, "CONNEX", "CONNEX GmbH", false));
        persister.save(new Bahnverwaltung(null, "DAB", "Dortmunder Actien-Brauerei (DAB)", false));
        persister.save(new Bahnverwaltung(null, "DB AG", "Deutschen Bahn AG (DB AG)", false));
        persister.save(new Bahnverwaltung(null, "DB AutoZug", "Deutschen Bahn AG AutoZug (DB AutoZug)", false));
        persister.save(new Bahnverwaltung(null, "DB Cargo", "Deutschen Bahn AG Cargo (DB Cargo)", false));
        persister.save(new Bahnverwaltung(null, "DB Museum", "Deutschen Bundesbahn Museum (DB Museum)", false));
        persister.save(new Bahnverwaltung(null, "DB", "Deutschen Bundesbahn (DB)", false));
        persister.save(new Bahnverwaltung(null, "DB (DR)", "Deutschen Reichsbahn (DB/DR)", false));
        persister.save(new Bahnverwaltung(null, "DBP", "Deutschen Bahnpost (DBP)", false));
        persister.save(new Bahnverwaltung(null, "DL", "Dixie Line (DL)", false));
        persister.save(new Bahnverwaltung(null, "DR (DDR)", "Deutschen Reichsbahn (DDR)", false));
        persister.save(new Bahnverwaltung(null, "DR", "Deutschen Reichsbahn (DR)", false));
        persister.save(new Bahnverwaltung(null, "DRB", "Deutschen Reichsbahn (DRB)", false));
        persister.save(new Bahnverwaltung(null, "DRG GV Bay", "Deutschen Reichsbahn Gesellschaft (DRG)", false));
        persister.save(new Bahnverwaltung(null, "DRG", "Deutschen Reichsbahn Gesellschaft (DRG)", false));
        persister.save(new Bahnverwaltung(null, "DRGW", "Denver and Rio Grande Western Railroad (DRGW)", false));
        persister.save(new Bahnverwaltung(null, "DSB", "Danske Statsbaner (DSB)", false));
        persister.save(new Bahnverwaltung(null, "DSG", "Deutsche Schlafwagen und Speisewagengesellschaft (DSG)", false));
        persister.save(new Bahnverwaltung(null, "EBOE", "Elmshorn-Barmstedt-Oldesloer Eisenbahn (EBOE)", false));
        persister.save(new Bahnverwaltung(null, "ESG", "Eisenbahn-Service GmbH (ESG)", false));
        persister.save(new Bahnverwaltung(null, "EST", " Société Nationale des Chemins de Fer Français (SNCF)", false));
        persister.save(new Bahnverwaltung(null, "EVB", "Eisenbahnen und Verkehrsbetriebe Elbe-Weser GmbH (EVB)", false));
        persister.save(new Bahnverwaltung(null, "EMD", "GM Electro Motive Division (EMD)", false));
        persister.save(new Bahnverwaltung(null, "EuH", "„Eisenbahn + Häfen“", false));
        persister.save(new Bahnverwaltung(null, "FS Cargo", "Ferrovie dello Stato Italiane Cargo (FS Cargo)", false));
        persister.save(new Bahnverwaltung(null, "FS", "Ferrovie dello Stato Italiane (FS)", false));
        persister.save(new Bahnverwaltung(null, "GATX", "GATX Corporation (GATX)", false));
        persister.save(new Bahnverwaltung(null, "GBAG", "GB Netz der Deutschen Bahn AG (GBAG)", false));
        persister.save(new Bahnverwaltung(null, "G.Bad.St.E", "Großherzoglich Badische Staatseisenbahn (G.Bad.St.E)", false));
        persister.save(new Bahnverwaltung(null, "GKM", "Grosskraftwerk Mannheim AG (GKM)", false));
        persister.save(new Bahnverwaltung(null, "G.O.St.B", "Großherzoglich Oldenburgische Staatseisenbahn (G.O.St.B)", false));
        persister.save(new Bahnverwaltung(null, "GSW", "Great Southwest Railroad (GSW)", false));
        persister.save(new Bahnverwaltung(null, "GVB", "Gruppenverwaltung Bayern (GVB)", false));
        persister.save(new Bahnverwaltung(null, "GhMFFE", "Großherzoglich Mecklenburgische Friedrich-Franz-Eisenbahn (MFFE)", false));
        persister.save(new Bahnverwaltung(null, "HBS", "Herzoglich Braunschweigische Staatseisenbahn (HBS)", false));
        persister.save(new Bahnverwaltung(null, "HEG", "Hersfelder Eisenbahn Gesellschaft mbH (HEG)", false));
        persister.save(new Bahnverwaltung(null, "HNSt.B", "Herzoglich Nassauische Staatsbahn (HNSt.B)", false));
        persister.save(new Bahnverwaltung(null, "Hansa", "Hansabahn Dortmund", false));
        persister.save(new Bahnverwaltung(null, "Henkel", "Fa Henkel KGaA, Düsseldorf", false));
        persister.save(new Bahnverwaltung(null, "ICG", "Illonois Central Railway (ICG)", false));
        persister.save(new Bahnverwaltung(null, "IGE", "Internationalen Gesellschaft für Eisenbahnverkehr (IGE)", false));
        persister.save(new Bahnverwaltung(null, "Ilmebahn", "Ilmebahn GmbH", false));
        persister.save(new Bahnverwaltung(null, "K.Bay.St.E", "Königlich Bayerischen Staatseisenbahnen (K.Bay.St.E)", false));
        persister.save(new Bahnverwaltung(null, "K.H.St.B", "Königlich Hannöversche Staatseisenbahnen (K.H.St.B)", false));
        persister.save(new Bahnverwaltung(null, "K.P.E.V", "Königlich Preußische Eisenbahn-Verwaltung (K.P.E.V)", false));
        persister.save(new Bahnverwaltung(null, "K.P.St.E", "Königlich Preußische Staatseisenbahnen (K.P.St.E)", false));
        persister.save(new Bahnverwaltung(null, "KB", "Deutschen Bundesbahn (DB)", false));
        persister.save(new Bahnverwaltung(null, "KEG", "Karsdorfer Eisenbahngesellschaft GmbH (KEG)", false));
        persister.save(new Bahnverwaltung(null, "KH", "Kraftwerk Herne", false));
        persister.save(new Bahnverwaltung(null, "KLVM", "", false));
        persister.save(new Bahnverwaltung(null, "K.Pu.G.H.St.E", "Königlich Preußische und Großherzoglich Hessischen Staatseisenbahnen (K.Pu.G.H.St.E", false));
        persister.save(new Bahnverwaltung(null, "K.Sächs.St.E", "Königlich Sächsische Staatseisenbahnen (K.Sächs.St.E)", false));
        persister.save(new Bahnverwaltung(null, "K.W.St.E", "Königlich Württembergischen Staatseisenbahnen (K.W.St.E)", false));
        persister.save(new Bahnverwaltung(null, "LAG", "Lokalbahn Aktien-Gesellschaft (LAG)", false));
        persister.save(new Bahnverwaltung(null, "LMS", "London, Midland and Scottish Railway (LMS)", false));
        persister.save(new Bahnverwaltung(null, "LNER", "London Northeast Railway (LNER)", false));
        persister.save(new Bahnverwaltung(null, "LSE", "Luzern–Stans–Engelberg-Bahn (LSE)", false));
        persister.save(new Bahnverwaltung(null, "MAV", "Magyar Államvasutak(MAV)", false));
        persister.save(new Bahnverwaltung(null, "MFFE", "Großherzoglich Mecklenburgische Friedrich-Franz-Eisenbahn (MFFE)", false));
        persister.save(new Bahnverwaltung(null, "MILW", "Chicago, Milwaukee, St Paul and Pacific Railroad (MILW)", false));
        persister.save(new Bahnverwaltung(null, "MKO", "Museumseisenbahn Küstenbahn Ostfriesland (MKO)", false));
        persister.save(new Bahnverwaltung(null, "MWB", "Mittelweserbahn GmbH (MWB)", false));
        persister.save(new Bahnverwaltung(null, "Makies AG", "Firma „Makies“ AG", false));
        persister.save(new Bahnverwaltung(null, "NB", "NordseeBahn (NB)", false));
        persister.save(new Bahnverwaltung(null, "NEG", "Norddeutsche Eisenbahngesellschaft Niebüll GmbH (NEG)", false));
        persister.save(new Bahnverwaltung(null, "NH", "New York, New Haven and Hartford Railroad (NH)", false));
        persister.save(new Bahnverwaltung(null, "NL", "P and O Nedlloyd (NL)", false));
        persister.save(new Bahnverwaltung(null, "NOHAB", "Nydqvist und Holm AB (NOHAB)", false));
        persister.save(new Bahnverwaltung(null, "NS Cargo", "Nederlandse Spoorweggen Cargo (NS Cargo)", false));
        persister.save(new Bahnverwaltung(null, "NS", "Nederlandse Spoorweggen (NS)", false));
        persister.save(new Bahnverwaltung(null, "NSB", "Norske Statsbaner (NSB)", false));
        persister.save(new Bahnverwaltung(null, "NYC", "New York Central Railroad (NYC)", false));
        persister.save(new Bahnverwaltung(null, "Nördlingen", "das Bayerische Eisenbahnmuseum (Nördlingen)", false));
        persister.save(new Bahnverwaltung(null, "ÖBB", "Österreichischen Bundesbahnen (ÖBB)", false));
        persister.save(new Bahnverwaltung(null, "ONR", "Ontario Northland Railway (ONR)", false));
        persister.save(new Bahnverwaltung(null, "On Rail", "On Rail Gesellschaft für Eisenbahnausrüstung und Zubehör mbH (On Rail)", false));
        persister.save(new Bahnverwaltung(null, "Opel", "Opel AG", false));
        persister.save(new Bahnverwaltung(null, "Orbe Chav", "Transport Vallée de Joux, Yverdon-les-bains et Sainte Croix (Travys)", false));
        persister.save(new Bahnverwaltung(null, "P+LE", "Pittsburgh and Lake Erie Railroad (P+LE)", false));
        persister.save(new Bahnverwaltung(null, "P.St.E", "Preußische Staatseisenbahnen (P.St.E)", false));
        persister.save(new Bahnverwaltung(null, "PEG", "Prignitzer Eisenbahn-Gesellschaft (PEG)", false));
        persister.save(new Bahnverwaltung(null, "PRR", "Pennsylvania Railroad (PRR)", false));
        persister.save(new Bahnverwaltung(null, "Persil", "Fa Henkel KGaA, Düsseldorf", false));
        persister.save(new Bahnverwaltung(null, "Pfalz.B", "Pfälzische Eisenbahnen (Pfalz.B)", false));
        persister.save(new Bahnverwaltung(null, "Privatbahn", "Privatbahn", false));
        persister.save(new Bahnverwaltung(null, "R", "Rutland Railroad (R)", false));
        persister.save(new Bahnverwaltung(null, "RAG", "Ruhrkohle AG (RAG)", false));
        persister.save(new Bahnverwaltung(null, "RCT", "Royal Corps of Transport (RCT)", false));
        persister.save(new Bahnverwaltung(null, "REL", "Reichseisenbahn Elsaß-Lothringen (REL)", false));
        persister.save(new Bahnverwaltung(null, "RENFE", "Red Nacional de Ferrocarriles Españoles (RENFE)", false));
        persister.save(new Bahnverwaltung(null, "Railbouw L", "Railbouw Leerdam Maatschappij", false));
        persister.save(new Bahnverwaltung(null, "SBB Cargo", "Schweizerischen Bundesbahnen Cargo (SBB Cargo)", false));
        persister.save(new Bahnverwaltung(null, "SBB/CFF/FFS", "Schweizerischen Bundesbahnen (SBB)", false));
        persister.save(new Bahnverwaltung(null, "SCF", "Southern Central Freight Railroad (SCF)", false));
        persister.save(new Bahnverwaltung(null, "SECO", "SECO Rail (SECO)", false));
        persister.save(new Bahnverwaltung(null, "SJ", "Statens Järnvägar (SJ)", false));
        persister.save(new Bahnverwaltung(null, "SKW", "Werkseisenbahn SKW Trostberg", false));
        persister.save(new Bahnverwaltung(null, "SNCB/NMBS", "Société Nationale des Chemins de Fer Belges (SNCB)", false));
        persister.save(new Bahnverwaltung(null, "SNCF", "Société Nationale des Chemins de Fer Français (SNCF)", false));
        persister.save(new Bahnverwaltung(null, "SOB", "SüdOstBayernBahn (SOB)", false));
        persister.save(new Bahnverwaltung(null, "SOO", "Soo Line Railroad (SOO)", false));
        persister.save(new Bahnverwaltung(null, "SP", "Southern Pacific Railroad (SP)", false));
        persister.save(new Bahnverwaltung(null, "SZD", "Sovetskie železnye dorogi (SžD)", false));
        persister.save(new Bahnverwaltung(null, "Seeh.Kiel", "Seehaven Kiel", false));
        persister.save(new Bahnverwaltung(null, "Siemens", "Siemens AG", false));
        persister.save(new Bahnverwaltung(null, "SoM", "State of Maine (SoM)", false));
        persister.save(new Bahnverwaltung(null, "Stora", "Trafikaktiebolaget Grängesberg–Oxelösunds Järnvägar (Stora)", false));
        persister.save(new Bahnverwaltung(null, "SüddZucker", "Südzucker AG", false));
        persister.save(new Bahnverwaltung(null, "T+N", "Texas and Northern Railway (T+N)", false));
        persister.save(new Bahnverwaltung(null, "T+P", "Texas and Pacific Railway Company (T+P)", false));
        persister.save(new Bahnverwaltung(null, "TAG", "Tegernsee-Bahn", false));
        persister.save(new Bahnverwaltung(null, "TAGAB", "Tågåkeriet i Bergslagen AB (TÅGAB)", false));
        persister.save(new Bahnverwaltung(null, "TGOJ", "Trafikaktiebolaget Grängesberg–Oxelösunds Järnvägar (TGOJ)", false));
        persister.save(new Bahnverwaltung(null, "TKAB", "Tågkompaniet AB (TKAB)", false));
        persister.save(new Bahnverwaltung(null, "TMR", "Texas Mexican Railway (TM)", false));
        persister.save(new Bahnverwaltung(null, "TSO", "Travaux du Sud-Ouest (TSO)", false));
        persister.save(new Bahnverwaltung(null, "TW", "Texas Western Railroad (TW)", false));
        persister.save(new Bahnverwaltung(null, "Tegernsee", "„Tegernsee-Bahn“", false));
        persister.save(new Bahnverwaltung(null, "UEF", "Ulmer Eisenbahnfreunde (UEF)", false));
        persister.save(new Bahnverwaltung(null, "UP", "Union Pacific Railroad (UP)", false));
        persister.save(new Bahnverwaltung(null, "USTC", "United States Transport Corps (USTC)", false));
        persister.save(new Bahnverwaltung(null, "VTG", "VTG AG (VTG)", false));
        persister.save(new Bahnverwaltung(null, "VW", "Volkswagen AG (VW)", false));
        persister.save(new Bahnverwaltung(null, "WEG", "Württembergische Eisenbahngesellschaft (WEG)", false));
        persister.save(new Bahnverwaltung(null, "WLE", "Westfälische Landes-Eisenbahn GmbH (WLE)", false));
        persister.save(new Bahnverwaltung(null, "WP", "Western Pacific Railroad (WP)", false));
        persister.save(new Bahnverwaltung(null, "Wiebe", "Wiebe Gleisbau GmbH (Wiebe)", false));
        persister.save(new Bahnverwaltung(null, "ÖBB", "Österreichischen Bundesbahnen (ÖBB)", false));
        persister.save(new Bahnverwaltung(null, "ÖHB", "Österreichischen Bundesbahnen (ÖBB)", false));
    }
    
    protected void populateDecoder() throws Exception {
    }
    
    protected void populateDecoderCV() throws Exception {
    }
    
    protected void populateDecoderFunktion() throws Exception {
    }

    protected void populateDecoderTyp() throws Exception {
        IPersister<DecoderTyp> persister = persisterFactory.createNamedItemPersister(DecoderTyp.class);
        INamedItemPersister<Hersteller> herstellerLookup = (INamedItemPersister<Hersteller>) persisterFactory.createNamedItemPersister(Hersteller.class);
        INamedItemPersister<Protokoll> protokollLookup = (INamedItemPersister<Protokoll>) persisterFactory.createNamedItemPersister(Protokoll.class);
    
        IProtokoll delta = protokollLookup.findByName("DELTA");
        IProtokoll mm = protokollLookup.findByName("MM");
        IProtokoll mfx = protokollLookup.findByName("mfx");
        IProtokoll weiche = protokollLookup.findByName("Weiche");
        
        IHersteller digitalbahn = herstellerLookup.findByName("Digital-Bahn");

        persister.save(new DecoderTyp(null, digitalbahn, weiche, "DSD2010", "Drehscheibendekoder", 16, false, false));

        Hersteller esu = herstellerLookup.findByName("ESU");

        persister.save(new DecoderTyp(null, esu, mm, "52620", "LokPilot FX", 1, false, false));
        persister.save(new DecoderTyp(null, esu, mfx, "61600", "LokPilot M4", 1, false, false));
        persister.save(new DecoderTyp(null, esu, mfx, "61601", "LokPilot M4 21MTC", 1, false, false));
        persister.save(new DecoderTyp(null, esu, mfx, "62499", "LokSound M4 21MTC", 1, true, false));
        persister.save(new DecoderTyp(null, esu, mfx, "62400", "LokSound M4", 1, true, false));
        persister.save(new DecoderTyp(null, esu, weiche, "51802", "SwitchPilot Servo", 4, false, false));
        persister.save(new DecoderTyp(null, esu, weiche, "51800", "SwitchPilot", 8, false, false));
        persister.save(new DecoderTyp(null, esu, weiche, "51820", "SwitchPilot 2", 8, false, false));
        persister.save(new DecoderTyp(null, esu, mm, "52621", "LokPilot FX 21MTC", 1, false, false));

        Hersteller marklin = herstellerLookup.findByName("Märklin");

        persister.save(new DecoderTyp(null, marklin, mm, "103787", "103787", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "115166", "115166", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "115673", "115673", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "116836", "116836", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "140131", "140131", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "148924", "148924", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "150436", "150436", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "156787", "156787", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "209394", "209394", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "219574", "219574", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "42973", "42973", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "46715", "46715", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "49940", "49940", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "602756", "602756", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "602850", "602850", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "603999", "603999", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "606174", "606174", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "606896", "606896", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mfx, "60760", "Hochleistungsdecoder", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "608825", "608825", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mfx, "60902", "Hochleistungselektronik", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "611077", "611077", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "611105", "611105", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "611754", "611754", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, delta, "6603", "Delta Modul", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, delta, "66031", "Delta Modul mit Zusatzfunktion", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, delta, "66032", "Delta Modul mit automatischer Systemerkennung", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "670040", "670040", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, weiche, "74460", "Einbau-Digital-Decoder", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, weiche, "7687", "Drehscheibendekoder", 16, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "123572", "123572", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "150436", "150436", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "162946", "162946", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "169274", "169274", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "253201", "253201", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "39970", "39970", 1, true, false));
        persister.save(new DecoderTyp(null, marklin, mm, "49960", "49960", 1, true, false));
        persister.save(new DecoderTyp(null, marklin, mm, "49961", "49961", 1, false, false));
        persister.save(new DecoderTyp(null, marklin, mm, "608862", "608862", 1, false, false));
        
        IHersteller uhlenbrock = herstellerLookup.findByName("Uhlenbrock");
        
        persister.save(new DecoderTyp(null, uhlenbrock , mm, "67900", "67900", 1, false, false));        
    }

    protected void populateDecoderTypCV() throws Exception {
        IPersister<DecoderTypCV> persister = persisterFactory.createItemPersister(DecoderTypCV.class);
        INamedItemPersister<DecoderTyp> decoderTypLookup = (INamedItemPersister<DecoderTyp>) persisterFactory.createNamedItemPersister(DecoderTyp.class);
        
        IDecoderTyp _103787 = decoderTypLookup.findByName("103787");

        persister.save(new DecoderTypCV(null, _103787, 1, "Adresse", 1, 80, 10, false));
        persister.save(new DecoderTypCV(null, _103787, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _103787, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _103787, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _103787, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _103787, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _115166 = decoderTypLookup.findByName("115166");

        persister.save(new DecoderTypCV(null, _115166, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _115166, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _115166, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _115166, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _115166, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _115166, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _115673 = decoderTypLookup.findByName("115673");

        persister.save(new DecoderTypCV(null, _115673, 1, "Adresse", 1, 80, 64, false));
        persister.save(new DecoderTypCV(null, _115673, 3, "Anfahrverzögerung", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _115673, 4, "Bremsverzögerung", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _115673, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _115673, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        persister.save(new DecoderTypCV(null, _115673, 63, "Lautstärke", 1, 63, 63, false));
        
        IDecoderTyp _115798 = decoderTypLookup.findByName("115798");

        persister.save(new DecoderTypCV(null, _115798, 1, "Adresse", 1, 255, null, false));
        persister.save(new DecoderTypCV(null, _115798, 2, "Anfahrverzögerung/Bremsverzögerung", 1, 31, null, false));
        persister.save(new DecoderTypCV(null, _115798, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _115798, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _115798, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _116836 = decoderTypLookup.findByName("116836");

        persister.save(new DecoderTypCV(null, _116836, 1, "Adresse", 1, 80, 70, false));
        persister.save(new DecoderTypCV(null, _116836, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _116836, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _116836, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _116836, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _116836, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _123572 = decoderTypLookup.findByName("123572");

        persister.save(new DecoderTypCV(null, _123572, 1, "Adresse", 0, 80, 42, false));
        persister.save(new DecoderTypCV(null, _123572, 3, "Anfahrverzögerung", 0, 63, 63, false));
        persister.save(new DecoderTypCV(null, _123572, 4, "Bremsverzögerung", 0, 63, 63, false));
        persister.save(new DecoderTypCV(null, _123572, 5, "Höchstgeschwindigkeit", 0, 63, 63, false));
        persister.save(new DecoderTypCV(null, _123572, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        persister.save(new DecoderTypCV(null, _123572, 63, "Lautstärke", 0, 63, 63, false));

        IDecoderTyp _140131 = decoderTypLookup.findByName("140131");

        persister.save(new DecoderTypCV(null, _140131, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _140131, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _140131, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _140131, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _140131, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _140131, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _148924 = decoderTypLookup.findByName("148924");

        persister.save(new DecoderTypCV(null, _148924, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _148924, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _148924, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _148924, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _148924, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _148924, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _148924, 63, "Lautstärke", 1, 63, null, false));

        IDecoderTyp _150436 = decoderTypLookup.findByName("150436");

        persister.save(new DecoderTypCV(null, _150436, 1, "Adresse", 1, 255, 38, false));
        persister.save(new DecoderTypCV(null, _150436, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _150436, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _150436, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _156787 = decoderTypLookup.findByName("156787");

        persister.save(new DecoderTypCV(null, _156787, 1, "Adresse", 1, 80, 49, false));
        persister.save(new DecoderTypCV(null, _156787, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _156787, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _156787, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _156787, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _162946 = decoderTypLookup.findByName("162946");

        persister.save(new DecoderTypCV(null, _162946, 1, "Adresse", 1, 80, 11, false));
        persister.save(new DecoderTypCV(null, _162946, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _162946, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _162946, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _162946, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _162946, 63, "Lautstärke", 1, 63, null, false));
        
        IDecoderTyp _169274 = decoderTypLookup.findByName("169274");
        
        persister.save(new DecoderTypCV(null, _169274, 1, "Adresse", 0, 80, 43, false));
        persister.save(new DecoderTypCV(null, _169274, 3, "Anfahrverzögerung", 0, 63, 63, false));
        persister.save(new DecoderTypCV(null, _169274, 4, "Bremsverzögerung", 0, 63, 63, false));
        persister.save(new DecoderTypCV(null, _169274, 5, "Höchstgeschwindigkeit", 0, 63, 63, false));
        persister.save(new DecoderTypCV(null, _169274, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        persister.save(new DecoderTypCV(null, _169274, 63, "Lautstärke", 0, 63, 63, false));
        
        IDecoderTyp _209394 = decoderTypLookup.findByName("209394");
        
        persister.save(new DecoderTypCV(null, _209394, 1, "Adresse", null, null, 54, false));
        persister.save(new DecoderTypCV(null, _209394, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        
        IDecoderTyp _219574 = decoderTypLookup.findByName("219574");
        
        persister.save(new DecoderTypCV(null, _219574, 1, "Adresse", 1, 80, 45, false));
        persister.save(new DecoderTypCV(null, _219574, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _219574, 3, "Anfahrverzögerung", 1, 63, null, false));
        
        IDecoderTyp _253201 = decoderTypLookup.findByName("253201");
        
        persister.save(new DecoderTypCV(null, _253201, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _253201, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _253201, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _253201, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _253201, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _253201, 63, "Lautstärke", 1, 63, null, false));
        
        IDecoderTyp _269706 = decoderTypLookup.findByName("269706");
        
        persister.save(new DecoderTypCV(null, _269706, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _269706, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _269706, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _269706, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _269706, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _269706, 50, "Protokolle", 1, 15, 15, false));
        persister.save(new DecoderTypCV(null, _269706, 63, "Lautstärke", 1, 63, null, false));
        
        IDecoderTyp _39970 = decoderTypLookup.findByName("39970");
        
        persister.save(new DecoderTypCV(null, _39970, 1, "Adresse", 1, 80, null, false));
        
        IDecoderTyp _42973 = decoderTypLookup.findByName("42973");

        persister.save(new DecoderTypCV(null, _42973, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _42973, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _42973, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _42973, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _42973, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _42973, 63, "Lautstärke", 1, 63, 63, false));
        
        IDecoderTyp _46715 = decoderTypLookup.findByName("46715");
        
        persister.save(new DecoderTypCV(null, _46715, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _46715, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        
        IDecoderTyp _49940 = decoderTypLookup.findByName("49940");
        
        persister.save(new DecoderTypCV(null, _49940, 1, "Adresse", 1, 80, 78, false));
        
        IDecoderTyp _49960 = decoderTypLookup.findByName("49960");
        
        persister.save(new DecoderTypCV(null, _49960, 1, "Adresse", 1, 80, null, false));
        
        IDecoderTyp _49961 = decoderTypLookup.findByName("_49961");
        
        persister.save(new DecoderTypCV(null, _49961, 1, "Adresse", 1, 80, null, false));
        
        IDecoderTyp _51800 = decoderTypLookup.findByName("51800");

        persister.save(new DecoderTypCV(null, _51800, 1, "Decoderadresse 1, LSB", 1, 63, 1, false));
        persister.save(new DecoderTypCV(null, _51800, 3, "Konfiguration Ausgang 1", 0, 64, 8, false));
        persister.save(new DecoderTypCV(null, _51800, 4, "Konfiguration Ausgang 2", 0, 64, 8, false));
        persister.save(new DecoderTypCV(null, _51800, 5, "Konfiguration Ausgang 3", 0, 64, 8, false));
        persister.save(new DecoderTypCV(null, _51800, 6, "Konfiguration Ausgang 4", 0, 64, 8, false));
        persister.save(new DecoderTypCV(null, _51800, 7, "Versionsnummer", null, null, 115, false));
        persister.save(new DecoderTypCV(null, _51800, 8, "Herstellerkennung", null, null, 151, false));
        persister.save(new DecoderTypCV(null, _51800, 9, "Decoderadresse 1, MSB", 0, 7, 0, false));
        persister.save(new DecoderTypCV(null, _51800, 28, "RailCom Konfiguration", 0, 6, 0, false));
        persister.save(new DecoderTypCV(null, _51800, 29, "Konfigurationsregister", 128,136, 128, false));
        persister.save(new DecoderTypCV(null, _51800, 33, "Funktionsausgangsstatus", 0, 255, null, false));
        persister.save(new DecoderTypCV(null, _51800, 34, "„Zoom“-Konfiguration", 0, 15, 0, false));
        persister.save(new DecoderTypCV(null, _51800, 35, "Decoderadresse 2, LSB", 1, 63, 1, false));
        persister.save(new DecoderTypCV(null, _51800, 36, "Decoderadresse 2, MSB", 0, 8, 8, false));
        persister.save(new DecoderTypCV(null, _51800, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, 15, false));
        persister.save(new DecoderTypCV(null, _51800, 38, "Servo 1, Stellung „A“", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _51800, 39, "Servo 1, Stellung „B“", 1, 63, 40, false));
        persister.save(new DecoderTypCV(null, _51800, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, 15, false));
        persister.save(new DecoderTypCV(null, _51800, 41, "Servo 2, Stellung „A“", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _51800, 42, "Servo 2, Stellung „B“", 1, 63, 40, false));
        
        IDecoderTyp _51802 = decoderTypLookup.findByName("51802");

        persister.save(new DecoderTypCV(null, _51802, 1, "Decoderadresse 1, LSB", 1, 63, 1, false));
        persister.save(new DecoderTypCV(null, _51802, 7, "Versionsnummer", null, null, 153, false));
        persister.save(new DecoderTypCV(null, _51802, 8, "Herstellerkennung", null, null, 151, false));
        persister.save(new DecoderTypCV(null, _51802, 9, "Decoderadresse 1, MSB", 0, 7, 0, false));
        persister.save(new DecoderTypCV(null, _51802, 28, "RailCom Konfiguration", 0, 6, 0, false));
        persister.save(new DecoderTypCV(null, _51802, 29, "Konfigurationsregister", 128,136, 128, false));
        persister.save(new DecoderTypCV(null, _51802, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, 15, false));
        persister.save(new DecoderTypCV(null, _51802, 38, "Servo 1, Stellung „A“", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _51802, 39, "Servo 1, Stellung „B“", 1, 63, 40, false));
        persister.save(new DecoderTypCV(null, _51802, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, 15, false));
        persister.save(new DecoderTypCV(null, _51802, 41, "Servo 2, Stellung „A“", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _51802, 42, "Servo 2, Stellung „B“", 1, 63, 40, false));
        persister.save(new DecoderTypCV(null, _51802, 43, "Servo 3, Drehgeschwindigkeit", 1, 63, 15, false));
        persister.save(new DecoderTypCV(null, _51802, 44, "Servo 3, Stellung „A“", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _51802, 45, "Servo 3, Stellung „B“", 1, 63, 40, false));
        persister.save(new DecoderTypCV(null, _51802, 46, "Servo 4, Drehgeschwindigkeit", 1, 63, 15, false));
        persister.save(new DecoderTypCV(null, _51802, 47, "Servo 4, Stellung „A“", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _51802, 48, "Servo 4, Stellung „B“", 1, 63, 40, false));
        
        IDecoderTyp _51820 = decoderTypLookup.findByName("51820");

        persister.save(new DecoderTypCV(null, _51820, 1, "Decoderadresse 1, LSB", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 3, "Konfiguration Ausgang 1", 0, 64, null, false));
        persister.save(new DecoderTypCV(null, _51820, 4, "Konfiguration Ausgang 2", 0, 64, null, false));
        persister.save(new DecoderTypCV(null, _51820, 5, "Konfiguration Ausgang 3", 0, 64, null, false));
        persister.save(new DecoderTypCV(null, _51820, 6, "Konfiguration Ausgang 4", 0, 64, null, false));
        persister.save(new DecoderTypCV(null, _51820, 7, "Versionsnummer", null, null, null, false));
        persister.save(new DecoderTypCV(null, _51820, 8, "Herstellerkennung", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _51820, 9, "Decoderadresse 1, MSB", 0, 7, null, false));
        persister.save(new DecoderTypCV(null, _51820, 28, "RailCom Konfiguration", 0, 6, null, false));
        persister.save(new DecoderTypCV(null, _51820, 29, "Konfigurationsregister", 128,136, null, false));
        persister.save(new DecoderTypCV(null, _51820, 33, "Funktionsausgangsstatus", 0, 255, null, false));
        persister.save(new DecoderTypCV(null, _51820, 34, "„Zoom“-Konfiguration", 0, 15, null, false));
        persister.save(new DecoderTypCV(null, _51820, 35, "Decoderadresse 2, LSB", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 36, "Decoderadresse 2, MSB", 0, 8, null, false));
        persister.save(new DecoderTypCV(null, _51820, 37, "Servo 1, Drehgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 38, "Servo 1, Stellung „A“", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 39, "Servo 1, Stellung „B“", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 40, "Servo 2, Drehgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 41, "Servo 2, Stellung „A“", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _51820, 42, "Servo 2, Stellung „B“", 1, 63, null, false));
        
        IDecoderTyp _52620 = decoderTypLookup.findByName("52620");
        
        persister.save(new DecoderTypCV(null, _52620, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _52620, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        
        IDecoderTyp _52621 = decoderTypLookup.findByName("52621");
        
        persister.save(new DecoderTypCV(null, _52621, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _52621, 8, "Rückstellen auf Serienwerte", null, null, null, false));
        
        IDecoderTyp _602756 = decoderTypLookup.findByName("602756");
        
        persister.save(new DecoderTypCV(null, _602756, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _602756, 2, "Lautstärke", 1, 63, null, false));
        
        IDecoderTyp _602850 = decoderTypLookup.findByName("602850");
        
        persister.save(new DecoderTypCV(null, _602850, 1, "Adresse", 1, 80, 11, false));
        
        IDecoderTyp _603999 = decoderTypLookup.findByName("603999");
        
        persister.save(new DecoderTypCV(null, _603999, 1, "Adresse", 2, 80, 54, false));
        
        IDecoderTyp _606174 = decoderTypLookup.findByName("606174");
        
        persister.save(new DecoderTypCV(null, _606174, 1, "Adresse", 1, 255, null, false));
        
        IDecoderTyp _606896 = decoderTypLookup.findByName("606896");
        
        persister.save(new DecoderTypCV(null, _606896, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _606896, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _606896, 3, "Anfahrverzögerung", 1, 63, null, false));

        IDecoderTyp _60760 = decoderTypLookup.findByName("60760");
        
        persister.save(new DecoderTypCV(null, _60760, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _60760, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, 16, false));
        persister.save(new DecoderTypCV(null, _60760, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _60760, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        
        IDecoderTyp _608825 = decoderTypLookup.findByName("608825");
        
        persister.save(new DecoderTypCV(null, _608825, 1, "Adresse", 1, 80, 39, false));
        persister.save(new DecoderTypCV(null, _608825, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _608825, 3, "Anfahrverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _608825, 4, "Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _608825, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _608825, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _608825, 63, "Lautstärke", 1, 63, null, false));
        
        IDecoderTyp _608862 = decoderTypLookup.findByName("608862");
        
        persister.save(new DecoderTypCV(null, _608862, 1, "Adresse", 1, 80, 10, false));
        persister.save(new DecoderTypCV(null, _608862, 2, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _608862, 3, "Anfahrverzögerung", 1, 63, null, false));
        
        IDecoderTyp _60902 = decoderTypLookup.findByName("60902");
        
        persister.save(new DecoderTypCV(null, _60902, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _60902, 2, "Anfahrverzögerung", 1, 63, 03, false));
        persister.save(new DecoderTypCV(null, _60902, 3, "Anfahrverzögerung", 1, 63, null, false));
        
        IDecoderTyp _611077 = decoderTypLookup.findByName("611077");
        
        persister.save(new DecoderTypCV(null, _611077, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _611077, 3, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _611077, 4, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _611077, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _611077, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        
        IDecoderTyp _611105 = decoderTypLookup.findByName("611105");
        
        persister.save(new DecoderTypCV(null, _611105, 1, "Adresse", 1, 80, 71, false));
        persister.save(new DecoderTypCV(null, _611105, 2, "Bedienung festgelegt", 0, 1, 0, false));
        persister.save(new DecoderTypCV(null, _611105, 3, "Baggerschaufel Zeitbegrenzung", 0, 1, 0, false));
        
        IDecoderTyp _611754 = decoderTypLookup.findByName("611754");

        persister.save(new DecoderTypCV(null, _611754, 1, "Adresse", 1, 80, null, false));
        persister.save(new DecoderTypCV(null, _611754, 2, "Anfahrverzögerung/Bremsverzögerung", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _611754, 5, "Höchstgeschwindigkeit", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _611754, 8, "Rückstellen auf Serienwerte", null, null, 8, false));

        IDecoderTyp _61600 = decoderTypLookup.findByName("61600");

        persister.save(new DecoderTypCV(null, _61600, 1, "Adresse", 1, 80, 03, false));
        persister.save(new DecoderTypCV(null, _61600, 2, "Anfahrverzögerung", 1, 63, 03, false));
        persister.save(new DecoderTypCV(null, _61600, 3, "Beschleunigungszeit", 1, 63, 16, false));
        persister.save(new DecoderTypCV(null, _61600, 4, "Bremsverzögerung", 1, 63, 12, false));
        persister.save(new DecoderTypCV(null, _61600, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _61600, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _61600, 53, "Regelungsreferenz", 1, 63, 45, false));
        persister.save(new DecoderTypCV(null, _61600, 54, "Lastregelung Param. K", 1, 63, 32, false));
        persister.save(new DecoderTypCV(null, _61600, 55, "Lastregelung Param. L", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _61600, 56, "Regelungseinfluss", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _61600, 73, "Speicheroptionen", 0, 7, 03, false));
        persister.save(new DecoderTypCV(null, _61600, 75, "Märklin Addresse 2", 1, 80, 04, false));
        persister.save(new DecoderTypCV(null, _61600, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        persister.save(new DecoderTypCV(null, _61600, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));
        
        IDecoderTyp _62400 = decoderTypLookup.findByName("62400");

        persister.save(new DecoderTypCV(null, _62400, 1, "Adresse", 1, 80, 03, false));
        persister.save(new DecoderTypCV(null, _62400, 2, "Anfahrverzögerung", 1, 63, 03, false));
        persister.save(new DecoderTypCV(null, _62400, 3, "Beschleunigungszeit", 1, 63, 16, false));
        persister.save(new DecoderTypCV(null, _62400, 4, "Bremsverzögerung", 1, 63, 12, false));
        persister.save(new DecoderTypCV(null, _62400, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _62400, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _62400, 53, "Regelungsreferenz", 1, 63, 56, false));
        persister.save(new DecoderTypCV(null, _62400, 54, "Lastregelung Param. K", 1, 63, 32, false));
        persister.save(new DecoderTypCV(null, _62400, 55, "Lastregelung Param. L", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _62400, 56, "Regelungseinfluss", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _62400, 57, "Geräuschmodus 1", 1, 63, 10, false));
        persister.save(new DecoderTypCV(null, _62400, 58, "Geräuschmodus 2", 1, 63, 58, false));
        persister.save(new DecoderTypCV(null, _62400, 59, "Fahrgeräusch", 1, 63, 32, false));
        persister.save(new DecoderTypCV(null, _62400, 60, "Fahrgeräusch", 1, 63, 55, false));
        persister.save(new DecoderTypCV(null, _62400, 63, "Geräuschlautstärke", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _62400, 64, "Bremssoundschwelle", 1, 63, 07, false));
        persister.save(new DecoderTypCV(null, _62400, 73, "Speicheroptionen", 0, 7, 03, false));
        persister.save(new DecoderTypCV(null, _62400, 74, "Märklin Addresse 2", 1, 63, null, false));
        persister.save(new DecoderTypCV(null, _62400, 75, "Märklin Addresse 2", 1, 80, 04, false));
        persister.save(new DecoderTypCV(null, _62400, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        persister.save(new DecoderTypCV(null, _62400, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        IDecoderTyp _62499 = decoderTypLookup.findByName("62499");
        
        persister.save(new DecoderTypCV(null, _62499, 1, "Adresse", 1, 80, 03, false));
        persister.save(new DecoderTypCV(null, _62499, 2, "Anfahrverzögerung", 1, 63, 03, false));
        persister.save(new DecoderTypCV(null, _62499, 3, "Beschleunigungszeit", 1, 63, 16, false));
        persister.save(new DecoderTypCV(null, _62499, 4, "Bremsverzögerung", 1, 63, 12, false));
        persister.save(new DecoderTypCV(null, _62499, 5, "Höchstgeschwindigkeit", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _62499, 8, "Rückstellen auf Serienwerte", null, null, 8, false));
        persister.save(new DecoderTypCV(null, _62499, 53, "Regelungsreferenz", 1, 63, 56, false));
        persister.save(new DecoderTypCV(null, _62499, 54, "Lastregelung Param. K", 1, 63, 32, false));
        persister.save(new DecoderTypCV(null, _62499, 55, "Lastregelung Param. L", 1, 63, 24, false));
        persister.save(new DecoderTypCV(null, _62499, 56, "Regelungseinfluss", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _62499, 57, "Geräuschmodus 1", 1, 63, 10, false));
        persister.save(new DecoderTypCV(null, _62499, 58, "Geräuschmodus 2", 1, 63, 58, false));
        persister.save(new DecoderTypCV(null, _62499, 59, "Fahrgeräusch", 1, 63, 32, false));
        persister.save(new DecoderTypCV(null, _62499, 60, "Fahrgeräusch", 1, 63, 55, false));
        persister.save(new DecoderTypCV(null, _62499, 63, "Geräuschlautstärke", 1, 63, 63, false));
        persister.save(new DecoderTypCV(null, _62499, 64, "Bremssoundschwelle", 1, 63, 07, false));
        persister.save(new DecoderTypCV(null, _62499, 73, "Speicheroptionen", 0, 7, 03, false));
        persister.save(new DecoderTypCV(null, _62499, 75, "Märklin Addresse 2", 1, 80, 04, false));
        persister.save(new DecoderTypCV(null, _62499, 78, "Anfahrspannung Analog AC", 1, 63, 25, false));
        persister.save(new DecoderTypCV(null, _62499, 79, "Höchstgeschwindigkeit Analog AC", 1, 63, 63, false));

        IDecoderTyp _6603 = decoderTypLookup.findByName("6603");

        persister.save(new DecoderTypCV(null, _6603, 1, "Adresse", 2, 80, null, false));

        IDecoderTyp _66031 = decoderTypLookup.findByName("66031");

        persister.save(new DecoderTypCV(null, _66031, 1, "Adresse", 2, 80, null, false));

        IDecoderTyp _66032 = decoderTypLookup.findByName("66032");

        persister.save(new DecoderTypCV(null, _66032, 1, "Adresse", 2, 80, null, false));

        IDecoderTyp _670040 = decoderTypLookup.findByName("670040");

        persister.save(new DecoderTypCV(null, _670040, 1, "Adresse", 2, 80, 54, false));

        IDecoderTyp _67900 = decoderTypLookup.findByName("67900");

        persister.save(new DecoderTypCV(null, _67900, 1, "Adresse", 1, 127, 3, false));
        persister.save(new DecoderTypCV(null, _67900, 2, "Minimale Geschwindigkeit", 1, 63, 5, false));
        persister.save(new DecoderTypCV(null, _67900, 3, "Anfahrverzögerung", 1, 63, 2, false));
        persister.save(new DecoderTypCV(null, _67900, 4, "Bremsverzögerung", 1, 63, 2, false));
        persister.save(new DecoderTypCV(null, _67900, 5, "Maximale Geschwindigkeit", 1, 63, 20, false));
        persister.save(new DecoderTypCV(null, _67900, 6, "Maximale Motorspannung", 1, 255, 64, false));
        persister.save(new DecoderTypCV(null, _67900, 7, "Softwareversion", null, null, null, false));
        persister.save(new DecoderTypCV(null, _67900, 8, "Herstellerkennung", null, null, 85, false));
        persister.save(new DecoderTypCV(null, _67900, 17, "Lange Lokadresse MSB", 192, 231, 199, false));
        persister.save(new DecoderTypCV(null, _67900, 18, "Lange Lokadresse LSB", 0, 255, 208, false));
        persister.save(new DecoderTypCV(null, _67900, 20, "Konfiguration beider Motoren nach DCC-Norm", 0, 33, 0, false));
        persister.save(new DecoderTypCV(null, _67900, 49, "Decoder-Konfiguration", 0, 195, 0, false));
        persister.save(new DecoderTypCV(null, _67900, 65, "Motorola Programmierung Offset", 0, 255, 0, false));
        persister.save(new DecoderTypCV(null, _67900, 67, "Maximale Geschwindigkeit für Taste 1", 0, 255, 40, false));
        persister.save(new DecoderTypCV(null, _67900, 68, "Maximale Geschwindigkeit für Taste 2", 0, 255, 40, false));
        persister.save(new DecoderTypCV(null, _67900, 69, "Maximale Geschwindigkeit für Taste 3", 0, 255, 50, false));
        persister.save(new DecoderTypCV(null, _67900, 70, "Maximale Geschwindigkeit für Taste 4", 0, 255, 50, false));
        persister.save(new DecoderTypCV(null, _67900, 71, "Anfahrverzögerung für Taste 1", 0, 255, 5, false));
        persister.save(new DecoderTypCV(null, _67900, 72, "Anfahrverzögerung für Taste 2", 0, 255, 5, false));
        persister.save(new DecoderTypCV(null, _67900, 73, "Anfahrverzögerung für Taste 3", 0, 255, 5, false));
        persister.save(new DecoderTypCV(null, _67900, 74, "Anfahrverzögerung für Taste 4", 0, 255, 5, false));
        persister.save(new DecoderTypCV(null, _67900, 75, "Bremsverzögerung für Taste 1", 0, 255, 1, false));
        persister.save(new DecoderTypCV(null, _67900, 76, "Bremsverzögerung für Taste 2", 0, 255, 1, false));
        persister.save(new DecoderTypCV(null, _67900, 77, "Bremsverzögerung für Taste 3", 0, 255, 1, false));
        persister.save(new DecoderTypCV(null, _67900, 78, "Bremsverzögerung für Taste 4", 0, 255, 1, false));
        persister.save(new DecoderTypCV(null, _67900, 79, "Maximale Motorspannung im Analogbetrieb", 0, 255, 180, false));
        persister.save(new DecoderTypCV(null, _67900, 98, "Zeitbegrenztes Einschalten der Ausgänge A1 + A2", 0, 3, 3, false));
        persister.save(new DecoderTypCV(null, _67900, 99, "Maximale Einschaltzeit in Sekunden", 0, 255, 45, false));

        IDecoderTyp _74460 = decoderTypLookup.findByName("74460");

        persister.save(new DecoderTypCV(null, _74460, 1, "Adresse", 1, 255, null, false));

        IDecoderTyp _7687 = decoderTypLookup.findByName("7687");

        persister.save(new DecoderTypCV(null, _7687, 1, "Adresse", 14,15, 14, false));

        IDecoderTyp _DSD2010 = decoderTypLookup.findByName("DSD2010");

        persister.save(new DecoderTypCV(null, _DSD2010, 1, "48 / 24 Positions", 0, 1, 1, false));
        persister.save(new DecoderTypCV(null, _DSD2010, 2, "DCC / Motorola", 0, 1, 1, false));
        persister.save(new DecoderTypCV(null, _DSD2010, 3, "Position specification", 0, 1, 0, false));
    }

    protected void populateDecoderTypFunktion() throws Exception {
        IPersister<DecoderTypFunktion> persister = persisterFactory.createItemPersister(DecoderTypFunktion.class);
        
    }
    
    protected void populateEpoch() throws Exception {
        IPersister<Epoch> persister = persisterFactory.createNamedItemPersister(Epoch.class);

        persister.save(new Epoch(null, "I", "1835 - 1920", false));
        persister.save(new Epoch(null, "II", "1920 - 1950", false));
        persister.save(new Epoch(null, "III", "1949 - 1970", false));
        persister.save(new Epoch(null, "IV", "1965 - 1990", false));
        persister.save(new Epoch(null, "V", "1990 - 2006", false));
        persister.save(new Epoch(null, "VI", "2006 -", false));
        persister.save(new Epoch(null, "Ia", "1835 - 1875", false));
        persister.save(new Epoch(null, "Ib", "1875 - 1895", false));
        persister.save(new Epoch(null, "Ic", "1895 - 1910", false));
        persister.save(new Epoch(null, "Id", "1910 - 1920", false));
        persister.save(new Epoch(null, "IIa", "1920 - 1925", false));
        persister.save(new Epoch(null, "IIb", "1925 - 1937", false));
        persister.save(new Epoch(null, "IIc", "1937 - 1950", false));
        persister.save(new Epoch(null, "IIIa", "1949 - 1956", false));
        persister.save(new Epoch(null, "IIIb", "1956 - 1970", false));
        persister.save(new Epoch(null, "IVa", "1965 - 1970", false));
        persister.save(new Epoch(null, "IVb", "1970 – 1980", false));
        persister.save(new Epoch(null, "IVc", "1980 – 1990", false));
        persister.save(new Epoch(null, "Va", "1990 - 1994", false));
        persister.save(new Epoch(null, "Vb", "1994 - 2000", false));
        persister.save(new Epoch(null, "Vc", "2000 - 2006", false));
    }

    protected void populateGattung() throws Exception {
        IPersister<Gattung> persister = persisterFactory.createNamedItemPersister(Gattung.class);

        persister.save(new Gattung(null, "AB3yge", "AB3yge", false));
        persister.save(new Gattung(null, "ADümh 101", "ADümh 101", false));
        persister.save(new Gattung(null, "ARDümz 106", "ARDümz 106", false));
        persister.save(new Gattung(null, "Apümh 121", "Apümh 121", false));
        persister.save(new Gattung(null, "Avmz 206", "Avmz 206", false));
        persister.save(new Gattung(null, "Avümh 111", "Avümh 111", false));
        persister.save(new Gattung(null, "Aüm 203", "Aüm 203", false));
        persister.save(new Gattung(null, "B3yge", "B3yge", false));
        persister.save(new Gattung(null, "B4yge", "B4yge", false));
        persister.save(new Gattung(null, "BA 115", "BA 115", false));
        persister.save(new Gattung(null, "BD3yge", "BD3yge", false));
        persister.save(new Gattung(null, "BR 03", "BR 03", false));
        persister.save(new Gattung(null, "BR 03.10", "BR 03.10", false));
        persister.save(new Gattung(null, "BR 103", "BR 103", false));
        persister.save(new Gattung(null, "BR 111", "BR 111", false));
        persister.save(new Gattung(null, "BR 151", "BR 151", false));
        persister.save(new Gattung(null, "BR 211", "BR 211", false));
        persister.save(new Gattung(null, "BR 216", "BR 216", false));
        persister.save(new Gattung(null, "BR 220", "BR 220", false));
        persister.save(new Gattung(null, "BR 230", "BR 230", false));
        persister.save(new Gattung(null, "BR 24", "BR 24", false));
        persister.save(new Gattung(null, "BR 260", "BR 260", false));
        persister.save(new Gattung(null, "BR 321", "BR 321", false));
        persister.save(new Gattung(null, "BR 45", "BR 45", false));
        persister.save(new Gattung(null, "BR 50", "BR 50", false));
        persister.save(new Gattung(null, "BR 53", "BR 53", false));
        persister.save(new Gattung(null, "BR 601", "BR 601", false));
        persister.save(new Gattung(null, "BR 64", "BR 64", false));
        persister.save(new Gattung(null, "BR 701", "BR 701", false));
        persister.save(new Gattung(null, "BR 81", "BR 81", false));
        persister.save(new Gattung(null, "BR 85", "BR 85", false));
        persister.save(new Gattung(null, "BR 86", "BR 86", false));
        persister.save(new Gattung(null, "BR 89.0", "BR 89.0", false));
        persister.save(new Gattung(null, "BR 96", "BR 96", false));
        persister.save(new Gattung(null, "BR 98.3", "BR 98.3", false));
        persister.save(new Gattung(null, "BT 10", "BT 10", false));
        persister.save(new Gattung(null, "BTmm 51", "BTmm 51", false));
        persister.save(new Gattung(null, "Bi 18t", "Bi 18t", false));
        persister.save(new Gattung(null, "Bi", "Bi", false));
        persister.save(new Gattung(null, "DByg 546", "DByg 546", false));
        persister.save(new Gattung(null, "DHG 700C", "DHG 700C", false));
        persister.save(new Gattung(null, "ELD4", "ELD4", false));
        persister.save(new Gattung(null, "ET 403", "ET 403", false));
        persister.save(new Gattung(null, "ET 91", "ET 91", false));
        persister.save(new Gattung(null, "El-u 061", "El-u 061", false));
        persister.save(new Gattung(null, "F7", "F7", false));
        persister.save(new Gattung(null, "G 10", "G 10", false));
        persister.save(new Gattung(null, "Gl", "Gl", false));
        persister.save(new Gattung(null, "Gmhs 53", "Gmhs 53", false));
        persister.save(new Gattung(null, "Gr 20", "Gr 20", false));
        persister.save(new Gattung(null, "Gs 210", "Gs 210", false));
        persister.save(new Gattung(null, "H 10", "H 10", false));
        persister.save(new Gattung(null, "H10", "H10", false));
        persister.save(new Gattung(null, "ICR-A10", "ICR-A10", false));
        persister.save(new Gattung(null, "ICR-B10", "ICR-B10", false));
        persister.save(new Gattung(null, "Ibdlps 383", "Ibdlps 383", false));
        persister.save(new Gattung(null, "Ichqrs 377", "Ichqrs 377", false));
        persister.save(new Gattung(null, "Kbs 443", "Kbs 443", false));
        persister.save(new Gattung(null, "Kklm 505", "Kklm 505", false));
        persister.save(new Gattung(null, "Laae 540", "Laae 540", false));
        persister.save(new Gattung(null, "Mannschaftswagen 376", "Mannschaftswagen 376", false));
        persister.save(new Gattung(null, "NS 6400", "NS 6400", false));
        persister.save(new Gattung(null, "OOtz 50", "OOtz 50", false));
        persister.save(new Gattung(null, "Om „Breslau“", "Om „Breslau“", false));
        persister.save(new Gattung(null, "Om 12", "Om 12", false));
        persister.save(new Gattung(null, "Otmm 70", "Otmm 70", false));
        persister.save(new Gattung(null, "Pw 90 HzL", "Pw 90 HzL", false));
        persister.save(new Gattung(null, "Pwg Pr 14", "Pwg Pr 14", false));
        persister.save(new Gattung(null, "Pwg Pr 14", "Pwg Pr 14", false));
        persister.save(new Gattung(null, "Pwgs 41", "Pwgs 41", false));
        persister.save(new Gattung(null, "Pwi 28", "Pwi 28", false));
        persister.save(new Gattung(null, "Pwi Wü 13", "Pwi Wü 13", false));
        persister.save(new Gattung(null, "R 02", "R 02", false));
        persister.save(new Gattung(null, "Rlmmps 651", "Rlmmps 651", false));
        persister.save(new Gattung(null, "Rlmms 58", "Rlmms 58", false));
        persister.save(new Gattung(null, "Rlmms", "Rlmms", false));
        persister.save(new Gattung(null, "Rlmmso 56", "Rlmmso 56", false));
        persister.save(new Gattung(null, "Rs 684", "Rs 684", false));
        persister.save(new Gattung(null, "SSH 71", "SSH 71", false));
        persister.save(new Gattung(null, "SSym „Köln“", "SSym „Köln“", false));
        persister.save(new Gattung(null, "Samms 709", "Samms 709", false));
        persister.save(new Gattung(null, "Schotterwagen 166", "Schotterwagen 166", false));
        persister.save(new Gattung(null, "Sm 24", "Sm 24", false));
        persister.save(new Gattung(null, "Tehs 50", "Tehs 50", false));
        persister.save(new Gattung(null, "Tko 02", "Tko 02", false));
        persister.save(new Gattung(null, "Ucs", "Ucs", false));
        persister.save(new Gattung(null, "V 200", "V 200", false));
        persister.save(new Gattung(null, "V 80", "V 80", false));
        persister.save(new Gattung(null, "VS 98", "VS 98", false));
        persister.save(new Gattung(null, "VT 75", "VT 75", false));
        persister.save(new Gattung(null, "VT 95", "VT 95", false));
        persister.save(new Gattung(null, "VT 98", "VT 98", false));
        persister.save(new Gattung(null, "Viehtransport", "Viehtransport", false));
        persister.save(new Gattung(null, "WGmh 824", "WGmh 824", false));
        persister.save(new Gattung(null, "WRmz 135", "WRmz 135", false));
        persister.save(new Gattung(null, "WRümh 131", "WRümh 131", false));
        persister.save(new Gattung(null, "X05 „Erfurt“", "X05 „Erfurt“", false));
        persister.save(new Gattung(null, "Zces", "Zces", false));
        persister.save(new Gattung(null, "üm 312", "üm 312", false));
        persister.save(new Gattung(null, "üm 313", "üm 313", false));
    }

    protected void populateHersteller() throws Exception {
        IPersister<Hersteller> persister = persisterFactory.createNamedItemPersister(Hersteller.class);

        persister.save(new Hersteller(null, "Avago Technologies", null, null, null, false));
        persister.save(new Hersteller(null, "4MFOR", null, null, null, false));
        persister.save(new Hersteller(null, "Artitec", null, null, null, false));
        persister.save(new Hersteller(null, "Auhagen", null, null, null, false));
        persister.save(new Hersteller(null, "B&K", null, null, null, false));
        persister.save(new Hersteller(null, "Brawa", null, null, null, false));
        persister.save(new Hersteller(null, "Busch", null, null, null, false));
        persister.save(new Hersteller(null, "DCC Supplies", null, null, null, false));
        persister.save(new Hersteller(null, "Deluxe Materials", null, null, null, false));
        persister.save(new Hersteller(null, "Digital-Bahn", null, null, null, false));
        persister.save(new Hersteller(null, "DigiTrain", null, null, null, false));
        persister.save(new Hersteller(null, "Diotec", null, null, null, false));
        persister.save(new Hersteller(null, "Erbert", null, null, null, false));
        persister.save(new Hersteller(null, "ESU", null, null, null, false));
        persister.save(new Hersteller(null, "Evergreen", null, null, null, false));
        persister.save(new Hersteller(null, "Fairchild", null, null, null, false));
        persister.save(new Hersteller(null, "Faller", null, null, null, false));
        persister.save(new Hersteller(null, "Fleischmann", null, null, null, false));
        persister.save(new Hersteller(null, "Gassner", null, null, null, false));
        persister.save(new Hersteller(null, "GaugeMaster", null, null, null, false));
        persister.save(new Hersteller(null, "Heico", null, null, null, false));
        persister.save(new Hersteller(null, "Herkat", null, null, null, false));
        persister.save(new Hersteller(null, "Herpa", null, null, null, false));
        persister.save(new Hersteller(null, "Humbrol", null, null, null, false));
        persister.save(new Hersteller(null, "Jordan", null, null, null, false));
        persister.save(new Hersteller(null, "Kibri", null, null, null, false));
        persister.save(new Hersteller(null, "Kingbright", null, null, null, false));
        persister.save(new Hersteller(null, "KKPMO", null, null, null, false));
        persister.save(new Hersteller(null, "Knipex", null, null, null, false));
        persister.save(new Hersteller(null, "Kühn", null, null, null, false));
        persister.save(new Hersteller(null, "LDT", null, null, null, false));
        persister.save(new Hersteller(null, "Liliput", null, null, null, false));
        persister.save(new Hersteller(null, "Lima", null, null, null, false));
        persister.save(new Hersteller(null, "Littfinski", null, null, null, false));
        persister.save(new Hersteller(null, "LUX-Modellbau", null, null, null, false));
        persister.save(new Hersteller(null, "Maquett", null, null, null, false));
        persister.save(new Hersteller(null, "Märklin", null, null, null, false));
        persister.save(new Hersteller(null, "Mehano", null, null, null, false));
        persister.save(new Hersteller(null, "Merten", null, null, null, false));
        persister.save(new Hersteller(null, "Noch", null, null, null, false));
        persister.save(new Hersteller(null, "Omron", null, null, null, false));
        persister.save(new Hersteller(null, "Preiser", null, null, null, false));
        persister.save(new Hersteller(null, "Proses", null, null, null, false));
        persister.save(new Hersteller(null, "Ratio", null, null, null, false));
        persister.save(new Hersteller(null, "Red Line", null, null, null, false));
        persister.save(new Hersteller(null, "Revell", null, null, null, false));
        persister.save(new Hersteller(null, "Ricko", null, null, null, false));
        persister.save(new Hersteller(null, "Rivarossi", null, null, null, false));
        persister.save(new Hersteller(null, "Roco", null, null, null, false));
        persister.save(new Hersteller(null, "RTS", null, null, null, false));
        persister.save(new Hersteller(null, "Schuco", null, null, null, false));
        persister.save(new Hersteller(null, "Seuthe", null, null, null, false));
        persister.save(new Hersteller(null, "Taiwan Semiconductor", null, null, null, false));
        persister.save(new Hersteller(null, "Tamiya", null, null, null, false));
        persister.save(new Hersteller(null, "Tams", null, null, null, false));
        persister.save(new Hersteller(null, "Tower Pro", null, null, null, false));
        persister.save(new Hersteller(null, "Trix", null, null, null, false));
        persister.save(new Hersteller(null, "TruOpto", null, null, null, false));
        persister.save(new Hersteller(null, "Uhlenbrock", null, null, null, false));
        persister.save(new Hersteller(null, "Unbekant", null, null, null, false));
        persister.save(new Hersteller(null, "Viessmann", null, null, null, false));
        persister.save(new Hersteller(null, "Walthers", null, null, null, false));
        persister.save(new Hersteller(null, "Weinert", null, null, null, false));
        persister.save(new Hersteller(null, "Wiking", null, null, null, false));
        persister.save(new Hersteller(null, "Woodland Scenics", null, null, null, false));
        persister.save(new Hersteller(null, "Zemo", null, null, null, false));
   }

    protected void populateKategorie() throws Exception {
        IPersister<Kategorie> persister = persisterFactory.createNamedItemPersister(Kategorie.class);
        
        persister.save(new Kategorie(null, "Ausgestaltung", "Ausgestaltung", false));
        persister.save(new Kategorie(null, "Beleuchtung", "Beleuchtung", false));
        persister.save(new Kategorie(null, "Decoder", "Decoder", false));
        persister.save(new Kategorie(null, "Ersatzteil", "Ersatzteil", false));
        persister.save(new Kategorie(null, "Fahrzeug", "Fahrzeug", false));
        persister.save(new Kategorie(null, "Gebaüde", "Gebaüde", false));
        persister.save(new Kategorie(null, "Gleismateriel", "Gleismateriel", false));
        persister.save(new Kategorie(null, "Landschaftsbau", "Landschaftsbau", false));
        persister.save(new Kategorie(null, "Lokomotiv", "Lokomotiv", false));
        persister.save(new Kategorie(null, "Oberleitung", "Oberleitung", false));
        persister.save(new Kategorie(null, "Set", "Set", false));
        persister.save(new Kategorie(null, "Signaltechnik", "Signaltechnik", false));
        persister.save(new Kategorie(null, "Sonstiges", "Sonstiges", false));
        persister.save(new Kategorie(null, "Steuerungstechnik", "Steuerungstechnik", false));
        persister.save(new Kategorie(null, "Treibwagen", "Treibwagen", false));
        persister.save(new Kategorie(null, "Wagen", "Wagen", false));
        persister.save(new Kategorie(null, "Werkzeug", "Werkzeug", false));
        persister.save(new Kategorie(null, "Zubehör", "Zubehör", false));
    }

    protected void populateKupplung() throws Exception {
        IPersister<Kupplung> persister = persisterFactory.createNamedItemPersister(Kupplung.class);
    
        persister.save(new Kupplung(null, "Relex", "Relex Kupplung", false));
        persister.save(new Kupplung(null, "KK", "Märklin-Kurzkupplungen mit Drehpunkt", false));
        persister.save(new Kupplung(null, "NEM", "Märklin-Kurzkupplungen in Norm-Aufnahme mit Drehpunkt", false));
        persister.save(new Kupplung(null, "NEM KK", "Märklin-Kurzkupplungen in Norm-Aufnahme mit Kulissenführung", false));
        persister.save(new Kupplung(null, "SF KK", "Märklin-Kurzkupplungen in Norm-Aufnahme mit Stromfürhrender Kulissenführung", false));
        persister.save(new Kupplung(null, "Telex", "Telex Kupplung", false));       
    }
    
    protected void populateLand() throws Exception {
    }
    
    protected void populateLicht() throws Exception {
        IPersister<Licht> persister = persisterFactory.createNamedItemPersister(Licht.class);
        
        persister.save(new Licht(null, "L1V", "Einfach-Spitzensignal vorne", false));
        persister.save(new Licht(null, "L1W", "Einfach-Spitzensignal mit der Fahrtrichtung wechselnd.", false));
        persister.save(new Licht(null, "L2V", "Zweilicht-Spitzensignal vorne", false));
        persister.save(new Licht(null, "L2L2", "Zweilicht-Spitzensignal vorne und hinten", false));
        persister.save(new Licht(null, "L2W", "Zweilicht-Spitzensignal mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "L3V", "Dreilicht-Spitzensignal vorne", false));
        persister.save(new Licht(null, "L3W", "Dreilicht-Spitzensignal mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "L4W", "Vierlicht-Spitzensignal mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "R1H", "Ein rotes Schlusslicht", false));
        persister.save(new Licht(null, "R2H", "Zwei rote Schlusslichter", false));
        persister.save(new Licht(null, "L2R2W", "Zweilicht-Spitzensignal und zwei rote Schlusslichter mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "L3R1W", "Dreilicht-Spitzensignal und ein rotes Schlusslicht mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "L3R2W", "Dreilicht-Spitzensignal und zwei rote Schlusslichter mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "L3L1W", "Dreilicht-Spitzensignal und ein weißes Schlusslicht mit der Fahrtrichtung wechselnd", false));
        persister.save(new Licht(null, "L3L2W", "Dreilicht-Spitzensignal und zwei weißes Schlusslicht mit der Fahrtrichtung wechselnd", false));
    }
    
    protected void populateMassstab() throws Exception {
        IPersister<Massstab> persister = persisterFactory.createNamedItemPersister(Massstab.class);

        persister.save(new Massstab(null, "0", "1:45 32 mm", false));
        persister.save(new Massstab(null, "0e", "1:45 16.5 mm", false));
        persister.save(new Massstab(null, "0i", "1:45 12 mm", false));
        persister.save(new Massstab(null, "0m", "1:45 22.5 mm", false));
        persister.save(new Massstab(null, "0p", "1:45 9 mm", false));
        persister.save(new Massstab(null, "1\"", "1:12 121 mm", false));
        persister.save(new Massstab(null, "1", "1:32 45 mm", false));
        persister.save(new Massstab(null, "1e", "1:32 22.5 mm", false));
        persister.save(new Massstab(null, "1i", "1:32 16.5 mm", false));
        persister.save(new Massstab(null, "1m", "1:32 32 mm", false));
        persister.save(new Massstab(null, "1n3", "1:32 28.6 mm", false));
        persister.save(new Massstab(null, "1p", "1:32 12 mm", false));
        persister.save(new Massstab(null, "F", "1:20.32 70.64 mm", false));
        persister.save(new Massstab(null, "Fn3", "1:20.32 45 mm", false));
        persister.save(new Massstab(null, "H0", "1:87 16.5 mm", false));
        persister.save(new Massstab(null, "H0e", "1:87 9 mm", false));
        persister.save(new Massstab(null, "H0i", "1:87 6.5 mm (H0f)", false));
        persister.save(new Massstab(null, "H0m", "1:87 12 mm", false));
        persister.save(new Massstab(null, "H0p", "1:87 4.5 mm", false));
        persister.save(new Massstab(null, "HOn2", "1:87.1 7 mm", false));
        persister.save(new Massstab(null, "II", "1:22.5 63.5 mm", false));
        persister.save(new Massstab(null, "IIe", "1:22.5 32 mm", false));
        persister.save(new Massstab(null, "Iii (NEM)", "1:22.5 22.5 mm", false));
        persister.save(new Massstab(null, "III", "1:16 89 mm", false));
        persister.save(new Massstab(null, "IIIe", "1:16 45 mm", false));
        persister.save(new Massstab(null, "IIIi (NMRA)", "1:16 32 mm (3/4\")", false));
        persister.save(new Massstab(null, "IIIm", "1:16 63.5 mm", false));
        persister.save(new Massstab(null, "IIIp", "1:16 22.5 mm", false));
        persister.save(new Massstab(null, "IIm", "1:22.5 45 mm", false));
        persister.save(new Massstab(null, "IIp", "1:22.5 16.5 mm", false));
        persister.save(new Massstab(null, "N", "1:160 9 mm", false));
        persister.save(new Massstab(null, "Ne", "1:160 4.5 mm (Nn2)", false));
        persister.save(new Massstab(null, "Nm", "1:160 6.5 mm (Nn3)", false));
        persister.save(new Massstab(null, "O", "1:48 31.75 mm", false));
        persister.save(new Massstab(null, "On2", "1:48 12.7 mm", false));
        persister.save(new Massstab(null, "On3", "1:48 19.4 mm ", false));
        persister.save(new Massstab(null, "On30", "1:48 16.5 mm", false));
        persister.save(new Massstab(null, "OO", "1:76.2 19.05 mm", false));
        persister.save(new Massstab(null, "S", "1:64 22.5 mm", false));
        persister.save(new Massstab(null, "Se", "1:64 12 mm", false));
        persister.save(new Massstab(null, "Si", "1:64 9 mm", false));
        persister.save(new Massstab(null, "Sm", "1:64 16.5 mm", false));
        persister.save(new Massstab(null, "Sn3", "1:64 14.3 mm", false));
        persister.save(new Massstab(null, "Sp", "1:64 6.5 mm", false));
        persister.save(new Massstab(null, "TT", "1:120 12 mm", false));
        persister.save(new Massstab(null, "TTe", "1:120 6.5 mm", false));
        persister.save(new Massstab(null, "TTi", "1:120 4.5 mm", false));
        persister.save(new Massstab(null, "TTm", "1:120 9 mm", false));
        persister.save(new Massstab(null, "V", "1:11 127 mm", false));
        persister.save(new Massstab(null, "Ve", "1:11 63.5 mm", false));
        persister.save(new Massstab(null, "Vi", "1:11 45 mm", false));
        persister.save(new Massstab(null, "VII", "1:8 184 mm", false));
        persister.save(new Massstab(null, "VIIe", "1:8 89 mm", false));
        persister.save(new Massstab(null, "VIIi", "1:8 63.5 mm", false));
        persister.save(new Massstab(null, "VIIm", "1:8 127 mm", false));
        persister.save(new Massstab(null, "VIIp", "1:8 45 mm", false));
        persister.save(new Massstab(null, "Vm", "1:11 89 mm", false));
        persister.save(new Massstab(null, "Vp", "1:11 32 mm", false));
        persister.save(new Massstab(null, "X", "1:5.5 260 mm", false));
        persister.save(new Massstab(null, "Xe", "1:5.5 127 mm", false));
        persister.save(new Massstab(null, "Xi", "1:5.5 89 mm", false));
        persister.save(new Massstab(null, "Xm", "1:5.5 184 mm", false));
        persister.save(new Massstab(null, "Xp", "1:5.5 63.5 mm", false));
        persister.save(new Massstab(null, "Z", "1:220 6.5 mm", false));
        persister.save(new Massstab(null, "Zm", "1:220 4.5 mm", false));
    }
    
    protected void populateMotorTyp() throws Exception {
        IPersister<MotorTyp> persister = persisterFactory.createNamedItemPersister(MotorTyp.class);
        
        persister.save(new MotorTyp(null, "C-Sinus", "C-Sinus", false));
        persister.save(new MotorTyp(null, "DCM", "DCM", false));
        persister.save(new MotorTyp(null, "Glockenanker", "Glockenanker", false));
        persister.save(new MotorTyp(null, "HLM", "HLM", false));
        persister.save(new MotorTyp(null, "HLM MS1-7", "HLM MS1-7", false));
        persister.save(new MotorTyp(null, "HLM MS1-8", "HLM MS1-8", false));
        persister.save(new MotorTyp(null, "HLM MS2-7", "HLM MS2-7", false));
        persister.save(new MotorTyp(null, "HLM MS2-8", "HLM MS2-8", false));
        persister.save(new MotorTyp(null, "HLM S", "HLM S", false));
        persister.save(new MotorTyp(null, "LFCM MS1-7", "LFCM MS1-7", false));
        persister.save(new MotorTyp(null, "LFCM MS1-8", "LFCM MS1-8", false));
        persister.save(new MotorTyp(null, "LFCM MS2-7", "LFCM MS2-7", false));
        persister.save(new MotorTyp(null, "LFCM MS2-8", "LFCM MS2-8", false));
        persister.save(new MotorTyp(null, "SFCM", "SFCM", false));
    }
    
    protected void populateProdukt() throws Exception {
    }
    
    protected void populateProduktTeil() throws Exception {
    }
    
    protected void populateProtokoll() throws Exception {
        IPersister<Protokoll> persister = persisterFactory.createNamedItemPersister(Protokoll.class);
        
        persister.save(new Protokoll(null, "DELTA", "Märklin DELTA", false));
        persister.save(new Protokoll(null, "fx", "Märklin fx", false));
        persister.save(new Protokoll(null, "mfx", "Märklin mfx", false));
        persister.save(new Protokoll(null, "DCC", "DCC", false));
        persister.save(new Protokoll(null, "MM", "Märklin Motorola", false));
        persister.save(new Protokoll(null, "Weiche", "Märklin Motorola Weiche", false));
    }
    
    protected void populateSonderModell() throws Exception {
        IPersister<SonderModell> persister = persisterFactory.createNamedItemPersister(SonderModell.class);

        persister.save(new SonderModell(null, "Märklin Magazin", "Märklin Magazin", false));
        persister.save(new SonderModell(null, "Märklin Insider", "Märklin Insider", false));
        persister.save(new SonderModell(null, "MHI Exclusiv", "Märklin Handler Initiative", false));
        persister.save(new SonderModell(null, "MM Jahreswagen", "Märklin Magazin Jahreswagen", false));
        persister.save(new SonderModell(null, "KC Jahreswagen", "Märklin Kids Club Jahreswagen", false));
        persister.save(new SonderModell(null, "Einmalige Serien", "Einmalige Serien", false));
        persister.save(new SonderModell(null, "Museumswagen", "Museumswagen", false));
        persister.save(new SonderModell(null, "Weihnachtswagen", "Weihnachtswagen", false));
        persister.save(new SonderModell(null, "Sondermodel", "Sondermodel", false));
    }
    
    protected void populateSpurweite() throws Exception {
        IPersister<Spurweite> persister = persisterFactory.createNamedItemPersister(Spurweite.class);
        
        persister.save(new Spurweite(null, "0", "0", false));
        persister.save(new Spurweite(null, "TT", "TT", false));
        persister.save(new Spurweite(null, "H0", "H0", false));
        persister.save(new Spurweite(null, "N", "N", false));
        persister.save(new Spurweite(null, "Z", "Z", false));
        persister.save(new Spurweite(null, "I", "I", false));
        persister.save(new Spurweite(null, "S", "S", false));
        persister.save(new Spurweite(null, "II", "II", false));
        persister.save(new Spurweite(null, "III", "III", false));
        persister.save(new Spurweite(null, "V", "V", false));
        persister.save(new Spurweite(null, "VII", "VII", false));
        persister.save(new Spurweite(null, "X", "X", false));
    }
    
    protected void populateSteuerung() throws Exception {
        IPersister<Steuerung> persister = persisterFactory.createNamedItemPersister(Steuerung.class);

        persister.save(new Steuerung(null, "digital", "digital", false));
        persister.save(new Steuerung(null, "mechanisch", "mechanisch (FRU)", false));
        persister.save(new Steuerung(null, "Umschaltelektronik", "Umschaltelektronik", false));
    }

    protected void populateUnterKategorie() throws Exception {
        IPersister<UnterKategorie> persister = persisterFactory.createNamedItemPersister(UnterKategorie.class);

        INamedItemPersister<Kategorie> lookup = (INamedItemPersister<Kategorie>) persisterFactory.createNamedItemPersister(Kategorie.class);

        IKategorie ausgestaltung = lookup.findByName("Ausgestaltung");
        
        persister.save(new UnterKategorie(null, ausgestaltung, "Ausgestaltung", "Ausgestaltung", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Blühmen", "Blühmen", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Bäume", "Bäume", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Büsche", "Büsche", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Figuren", "Figuren", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Hecken", "Hecken", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Ladegut", "Ladegut", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Pflanzen", "Pflanzen", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Zeichen", "Zeichen", false));
        persister.save(new UnterKategorie(null, ausgestaltung, "Zäune", "Zäune", false));

        IKategorie beleuchtung = lookup.findByName("Beleuchtung");

        persister.save(new UnterKategorie(null, beleuchtung, "Beleuchtung", "Beleuchtung", false));
        persister.save(new UnterKategorie(null, beleuchtung, "Gluehlampe", "Gluehlampe", false));
        persister.save(new UnterKategorie(null, beleuchtung, "Innenbeleuchtung", "Innenbeleuchtung", false));
        persister.save(new UnterKategorie(null, beleuchtung, "Leuchteinsatz", "Leuchteinsatz", false));
        persister.save(new UnterKategorie(null, beleuchtung, "Stromführendekupplungen", "Stromführendekupplungen", false));
        persister.save(new UnterKategorie(null, beleuchtung, "Stromzuführung", "Stromzuführung", false));
        persister.save(new UnterKategorie(null, beleuchtung, "Zugschlussbeleuchtung", "Zugschlussbeleuchtung", false));
        
        IKategorie decoder = lookup.findByName("Decoder");
        
        persister.save(new UnterKategorie(null, decoder, "Decoder", "Decoder", false));
        persister.save(new UnterKategorie(null, decoder, "Lautsprecher", "Lautsprecher", false));
        
        IKategorie ersatzteil = lookup.findByName("Ersatzteil");
        
        persister.save(new UnterKategorie(null, ersatzteil, "Anker", "Anker", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Beschwerung", "Beschwerung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Drehgestellrahmen", "Drehgestellrahmen", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Drehgestell", "Drehgestell", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Entstördrossel", "Entstördrossel", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Ersatzteil", "Ersatzteil", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Feder", "Feder", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Feldmagnet", "Feldmagnet", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Fenster", "Fenster", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Grundplatte", "Grundplatte", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Haftreifen", "Haftreifen", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Halteplatte", "Halteplatte", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Inneneinrichtung", "Inneneinrichtung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Isolierung", "Isolierung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kabel", "Kabel", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kabelklemmen", "Kabelklemmen", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Klappe", "Klappe", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kohlbursten", "Kohlbursten", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kuppelstange", "Kuppelstange", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kupplung", "Kupplung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kupplungsdeichsel", "Kupplungsdeichsel", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kupplungshaken", "Kupplungshaken", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kupplungskinematik", "Kupplungskinematik", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kupplungsschacht", "Kupplungsschacht", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Kurzkupplung", "Kurzkupplung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Leitschaufel", "Leitschaufel", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Lokumbausätze", "Lokumbausätze", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Massefeder", "Massefeder", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Messingblech", "Messingblech", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Motorschild", "Motorschild", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Mutter", "Mutter", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Pantograph", "Pantograph", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Prallplatte", "Prallplatte", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Puffer", "Puffer", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Rad", "Rad", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Relais", "Relais", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Relexkupplung", "Relexkupplung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Schaltsfeder", "Schaltsfeder", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Schleifer", "Schleifer", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Schraube", "Schraube", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Schraubenkupplung", "Schraubenkupplung", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Senkschraube", "Senkschraube", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Stange", "Stange", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Telex", "Telex", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Traeger", "Traeger", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Wagenboden", "Wagenboden", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Weichenfeder", "Weichenfeder", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Zugfeder", "Zugfeder", false));
        persister.save(new UnterKategorie(null, ersatzteil, "Zylinderschraube", "Zylinderschraube", false));
        
        IKategorie fahrzeug = lookup.findByName("Fahrzeug");
        
        persister.save(new UnterKategorie(null, fahrzeug, "Fahrzeug", "Fahrzeug", false));
        
        IKategorie gebaude = lookup.findByName("Gebaüde");
        
        persister.save(new UnterKategorie(null, gebaude, "Bekohlung", "Bekohlung", false));
        persister.save(new UnterKategorie(null, gebaude, "Bockkrän", "Bockkrän", false));
        persister.save(new UnterKategorie(null, gebaude, "Drehscheibe", "Drehscheibe", false));
        persister.save(new UnterKategorie(null, gebaude, "Gebaüde", "Gebaüde", false));
        
        IKategorie gleismateriel = lookup.findByName("Gleismateriel");
        
        persister.save(new UnterKategorie(null, gleismateriel, "Gleismateriel", "Gleismateriel", false));
        
        IKategorie landschaftsbau = lookup.findByName("Landschaftsbau");
        
        persister.save(new UnterKategorie(null, landschaftsbau, "Landschaftsbau", "Landschaftsbau", false));
        
        IKategorie lokomotive = lookup.findByName("Lokomotiv");
        
        persister.save(new UnterKategorie(null, lokomotive, "Lokomotiv", "Lokomotiv", false));
        persister.save(new UnterKategorie(null, lokomotive, "Akku", "Akku", false));
        persister.save(new UnterKategorie(null, lokomotive, "Dampf", "Dampf", false));
        persister.save(new UnterKategorie(null, lokomotive, "Diesel", "Diesel", false));
        persister.save(new UnterKategorie(null, lokomotive, "Elektro", "Elektro", false));
        
        IKategorie oberleitung = lookup.findByName("Oberleitung");
        
        persister.save(new UnterKategorie(null, oberleitung, "Oberleitung", "Oberleitung", false));
        
        IKategorie set = lookup.findByName("Set");
        
        persister.save(new UnterKategorie(null, set, "Set", "Set", false));
        
        IKategorie signaltechnik = lookup.findByName("Signaltechnik");
        
        persister.save(new UnterKategorie(null, signaltechnik, "Signalbirne", "Signalbirne", false));
        persister.save(new UnterKategorie(null, signaltechnik, "Signaltechnik", "Signaltechnik", false));
        
        IKategorie sonstiges = lookup.findByName("Sonstiges");
        
        persister.save(new UnterKategorie(null, sonstiges, "Sonstiges", "Sonstiges", false));
        
        IKategorie steuerungstechnik = lookup.findByName("Steuerungstechnik");
        
        persister.save(new UnterKategorie(null, steuerungstechnik, "Steuerungstechnik", "Steuerungstechnik", false));
        persister.save(new UnterKategorie(null, steuerungstechnik, "Stromversorgung", "Stromversorgung", false));
        
        IKategorie treibwagen = lookup.findByName("Treibwagen");
        
        persister.save(new UnterKategorie(null, treibwagen, "Beiwagen", "Beiwagen", false));
        persister.save(new UnterKategorie(null, treibwagen, "Mittelwagen", "Mittelwagen", false));
        persister.save(new UnterKategorie(null, treibwagen, "Steurwagen", "Steurwagen", false));
        persister.save(new UnterKategorie(null, treibwagen, "Treibwagen", "Treibwagen", false));
        
        IKategorie wagen = lookup.findByName("Wagen");
        
        persister.save(new UnterKategorie(null, wagen, "Abteilwagen", "Abteilwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Aussichtswagen", "Aussichtswagen", false));
        persister.save(new UnterKategorie(null, wagen, "Autotransportwagen", "Autotransportwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Bahndienstwagen", "Bahndienstwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Bananenwagen", "Bananenwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Barwagen", "Barwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Behältertragwagen", "Behältertragwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Bierwagen", "Bierwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Carbid-Flaschenwagen", "Carbid-Flaschenwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Containertragwagen", "Containertragwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Doppelstockwagen", "Doppelstockwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Drehschemelwagen", "Drehschemelwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Fahrradtransportwagen", "Fahrradtransportwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Flachwagen", "Flachwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Gaswagen", "Gaswagen", false));
        persister.save(new UnterKategorie(null, wagen, "Gedeckter Güterwagen", "Gedeckter Güterwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Gepäckwagen", "Gepäckwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Gesellschaftswagen", "Gesellschaftswagen", false));
        persister.save(new UnterKategorie(null, wagen, "Großraumwagen", "Großraumwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Güterwagen", "Güterwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Güterzugbegleitwagen", "Güterzugbegleitwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Hochbordwagen", "Hochbordwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Kesselwagen", "Kesselwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Kränwagen", "Kränwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Kühlwagen", "Kühlwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Mannschaftswagen", "Mannschaftswagen", false));
        persister.save(new UnterKategorie(null, wagen, "Messewagen", "Messewagen", false));
        persister.save(new UnterKategorie(null, wagen, "Nahverkehrswagen", "Nahverkehrswagen", false));
        persister.save(new UnterKategorie(null, wagen, "Niederbordwagen", "Niederbordwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Rolldachwagen", "Rolldachwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Schiebewandwagen", "Schiebewandwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Schneepflug", "Schneepflug", false));
        persister.save(new UnterKategorie(null, wagen, "Schotterwagen", "Schotterwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Schwerlastwagen", "Schwerlastwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Schüttgut-Kippwagen", "Schüttgut-Kippwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Seitenentladewagen", "Seitenentladewagen", false));
        persister.save(new UnterKategorie(null, wagen, "Silowagen", "Silowagen", false));
        persister.save(new UnterKategorie(null, wagen, "Sonderfahrzeug", "Sonderfahrzeug", false));
        persister.save(new UnterKategorie(null, wagen, "Speisewagen", "Speisewagen", false));
        persister.save(new UnterKategorie(null, wagen, "Taschenwagen", "Taschenwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Tiefladewagen", "Tiefladewagen", false));
        persister.save(new UnterKategorie(null, wagen, "Torpedopfannenwagen", "Torpedopfannenwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Umbauwagen", "Umbauwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Verschlagwagen", "Verschlagwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Viehwagen", "Viehwagen", false));
        persister.save(new UnterKategorie(null, wagen, "Wagen", "Wagen", false));
        persister.save(new UnterKategorie(null, wagen, "Weihnachtswagen", "Weihnachtswagen", false));
        persister.save(new UnterKategorie(null, wagen, "Weinwagen", "Weinwagen", false));
        
        IKategorie werkzeug = lookup.findByName("Werkzeug");
        
        persister.save(new UnterKategorie(null, werkzeug, "Bücher", "Bücher", false));
        persister.save(new UnterKategorie(null, werkzeug, "Farbe", "Farbe", false));
        persister.save(new UnterKategorie(null, werkzeug, "Kleb", "Kleb", false));
        persister.save(new UnterKategorie(null, werkzeug, "Werkzeug", "Werkzeug", false));
        
        IKategorie zubehor = lookup.findByName("Zubehör");
        
        persister.save(new UnterKategorie(null, zubehor, "Beschriftigung", "Beschriftigung", false));
        persister.save(new UnterKategorie(null, zubehor, "Zubehör", "Zubehör", false));        
    }

    protected void populateVorbild() throws Exception {
    }
    
    protected void populateWahrung() throws Exception {
        IPersister<Wahrung> persister = persisterFactory.createNamedItemPersister(Wahrung.class);
        
        persister.save(new Wahrung(null, "AUD", "Australian Dollar", 2, false));
        persister.save(new Wahrung(null, "DEM", "Deutschemark", 2, false));
        persister.save(new Wahrung(null, "EUR", "Euro", 2, false));
        persister.save(new Wahrung(null, "GBP", "Pound Serling", 2, false));
        persister.save(new Wahrung(null, "USD", "US Dollar", 2, false));
    }

    protected void populateZug() throws Exception {
    }
    
    protected void populateZugConsist() throws Exception {
    }
    
    protected void populateZugTyp() throws Exception {
        IPersister<ZugTyp> persister = persisterFactory.createNamedItemPersister(ZugTyp.class);
        
        persister.save(new ZugTyp(null, "Gütterzug", "Gütterzug", false));
        persister.save(new ZugTyp(null, "Nahvekerszug", "Nahvekerszug", false));
        persister.save(new ZugTyp(null, "Bahndienstzug", "Bahndienstzug", false));
        persister.save(new ZugTyp(null, "Interregiozug", "Interregiozug", false));
        persister.save(new ZugTyp(null, "Intercityzug", "Intercityzug", false));
        persister.save(new ZugTyp(null, "TEE Zug", "TEE Zug", false));
        persister.save(new ZugTyp(null, "Militär Zug", "Militär Zug", false));
    }
}