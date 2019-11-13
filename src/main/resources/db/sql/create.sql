create sequence hibernate_sequence start with 1 increment by 1
create table achsfolg (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table anderung (id bigint not null, deleted boolean not null, anderungsdatum date, anderungsId integer, anderungsTyp varchar(255), anmerkung varchar(255), bezeichnung varchar(100), stuck integer, artikel_id bigint not null, primary key (id))
create table antrieb (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table artikel (id bigint not null, deleted boolean not null, abbildung varchar(255), anmerkung varchar(100), artikel_id varchar(50), beladung varchar(100), bezeichnung varchar(100) not null, kaufdatum date, preis decimal(6,2), status varchar(255) not null, stuck integer not null, verbleibende integer not null, decoder_id bigint, kupplung_id bigint, licht_id bigint, motor_typ_id bigint, produkt_id bigint not null, steuerung_id bigint, wahrung_id bigint, primary key (id))
create table aufbau (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), abbildung varchar(255), primary key (id))
create table bahnverwaltung (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table decoder (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, decoder_id varchar(50), fahrstufe integer, status integer, decoder_typ_id bigint not null, protokoll_id bigint not null, primary key (id))
create table decoderAdress (id bigint not null, deleted boolean not null, adress integer not null, adressTyp varchar(10) not null, index integer not null check (index>=1 AND index<=6), decoder_id bigint not null, primary key (id))
create table decoderCV (id bigint not null, deleted boolean not null, wert integer check (wert>=0 AND wert<=255), cv_id bigint not null, decoder_id bigint not null, primary key (id))
create table decoderFunktion (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, decoder_id bigint not null, funktion_id bigint not null, primary key (id))
create table decoderTyp (id bigint not null, deleted boolean not null, bestellNr varchar(255), bezeichnung varchar(100), fahrstufe integer, i_max decimal(6,2), konfiguration varchar(15) not null, sound boolean not null, stecker varchar(10) not null, hersteller_id bigint not null, protokoll_id bigint not null, primary key (id))
create table decoderTypAdress (id bigint not null, deleted boolean not null, werkseinstellung integer, adressTyp integer not null, index integer not null check (index>=1 AND index<=10), span integer not null check (span<=32 AND span>=1), decoder_typ_id bigint not null, primary key (id))
create table decoderTypCV (id bigint not null, deleted boolean not null, bezeichnung varchar(100), cv integer not null check (cv>=1 AND cv<=255), maximal integer check (maximal>=0 AND maximal<=255), minimal integer check (minimal>=0 AND minimal<=255), werkseinstellung integer check (werkseinstellung>=0 AND werkseinstellung<=255), decoder_typ_id bigint not null, primary key (id))
create table decoderTypFunktion (id bigint not null, deleted boolean not null, bezeichnung varchar(100), funktion varchar(4), programmable boolean not null, reihe integer not null check (reihe<=1 AND reihe>=0), decoder_typ_id bigint not null, primary key (id))
create table epoch (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table gattung (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table hersteller (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), telefon varchar(20), url varchar(255), primary key (id))
create table kategorie (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table kupplung (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), abbildung varchar(255), primary key (id))
create table land (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), wahrung_id bigint, primary key (id))
create table licht (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), abbildung varchar(255), primary key (id))
create table massstab (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table motorTyp (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table produkt (id bigint not null, deleted boolean not null, abbildung varchar(255), anleitungen varchar(512), anmerkung varchar(100), bauzeit date, bestellNr varchar(255), betreibsnummer varchar(100), bezeichnung varchar(100), explosionszeichnung varchar(512), lange decimal(6,2), achsfolg_id bigint, aufbau_id bigint, bahnverwaltung_id bigint, decoder_typ_id bigint, epoch_id bigint, gattung_id bigint, hersteller_id bigint not null, kupplung_id bigint, licht_id bigint, massstab_id bigint not null, motor_typ_id bigint, sondermodell_id bigint, spurweite_id bigint not null, steuerung_id bigint, unter_kategorie_id bigint not null, vorbild_id bigint, primary key (id))
create table produktTeil (id bigint not null, deleted boolean not null, anzahl integer not null, produkt_id bigint not null, teilId bigint not null, primary key (id))
create table protokoll (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table sondermodell (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table spurweite (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table steuerung (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
create table unterKategorie (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), kategorie_id bigint not null, primary key (id))
create table vorbild (id bigint not null, deleted boolean not null, abbildung varchar(255), anfahrzugkraft decimal(19,2), anzahl integer, aufbau varchar(100), ausserdienst date, bauzeit date, betreibsnummer varchar(100), bezeichnung varchar(100), dienstgewicht decimal(19,2), dmLaufradHinten decimal(19,2), dmLaufradVorn decimal(19,2), dmTreibrad decimal(19,2), dmZylinder decimal(19,2), drehgestellBauart varchar(100), fahrmotoren integer, geschwindigkeit integer, hersteller varchar(100), kapazitat decimal(19,2), kesseluberdruck decimal(19,2), klasse integer, kolbenhub decimal(19,2), lange decimal(19,2), leistung decimal(19,2), leistungsubertragung varchar(255), mittelwagen integer, motorbauart varchar(100), reichweite decimal(19,2), rostflache decimal(19,2), sitzplatzeKL1 integer, sitzplatzeKL2 integer, sitzplatzeKL3 integer, sitzplatzeKL4 integer, triebkopf integer, uberhitzerflache decimal(19,2), verdampfung decimal(19,2), wasservorrat decimal(19,2), zylinder integer, achsfolg_id bigint not null, antrieb_id bigint not null, bahnverwaltung_id bigint not null, gattung_id bigint not null, unter_kategorie_id bigint not null, primary key (id))
create table wahrung (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), dezimal integer, primary key (id))
create table zug (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), zugTyp_id bigint not null, primary key (id))
create table zugConsist (id bigint not null, deleted boolean not null, position integer not null, artikel_id bigint not null, zug_id bigint not null, primary key (id))
create table zugTyp (id bigint not null, deleted boolean not null, bezeichnung varchar(100) not null, name varchar(50), primary key (id))
alter table achsfolg add constraint UK_fy8u67u46v5hprxwgkqqwb9c3 unique (name)
create index IDXmtw1iy4tkctqef980gn2jdwh4 on anderung (artikel_id)
create index IDXs2oa2cx6on6rp6cmkmcpqdaev on anderung (anderungsId)
alter table anderung add constraint UKklvpcljjsrpg1dniqya0yiaj0 unique (artikel_id, anderungsId)
alter table antrieb add constraint UK_5axptmx387umya26egwt76ch0 unique (name)
create index IDXokqtdqfy3mfgekla9g207t260 on artikel (produkt_id)
create index IDXlam6thwq8bgkxl5e517ntxs5v on artikel (wahrung_id)
create index IDXj127qdaw1004agovbvs54wi3i on artikel (steuerung_id)
create index IDXpicwncs1wm83q896leinby9hx on artikel (motor_typ_id)
create index IDXnrs3x28ggme3df0gf5isv2bu on artikel (licht_id)
create index IDX60n1nwr9syf40r17sism0p75f on artikel (kupplung_id)
create index IDX22er2tp295eas75exjvqtw213 on artikel (decoder_id)
alter table artikel add constraint UK_pi24nctfcda9uwt8i8p8j7388 unique (artikel_id)
alter table aufbau add constraint UK_q500pa6um871vyfr0hgwapu2q unique (name)
alter table bahnverwaltung add constraint UK_r4ffbflr2v4qmwqjxy84d7nua unique (name)
create index IDXnso15a75jg4o124ult2lo0g5s on decoder (decoder_typ_id)
create index IDXguvs26kgyoccjw9qoh5bs2wkd on decoder (protokoll_id)
alter table decoder add constraint UK_ordevywlk5437ft6bxp996jpe unique (decoder_id)
alter table decoderAdress add constraint UKtgvjjb5d91gcbis1bbw9vy42 unique (decoder_id, index)
alter table decoderCV add constraint UKp1kkf3o2n8p7alwjqj47w9oki unique (decoder_id, cv_id)
alter table decoderFunktion add constraint UKlrra6r1in98pabcgn69xhnbrn unique (decoder_id, funktion_id)
create index IDXsdbquvy9cv6ppwuw8n36ef8be on decoderTyp (hersteller_id)
create index IDXfyo4woje0ft7nkr01ee9idbbq on decoderTyp (protokoll_id)
alter table decoderTyp add constraint UKlc5awbqw24xcqelpfx8wlisy5 unique (hersteller_id, bestellNr)
create index IDXmp652u4t68c9ufvnvx4bnyujn on decoderTypAdress (decoder_typ_id)
create index IDXp6p76xba841jqr4hks3581dor on decoderTypAdress (index)
alter table decoderTypAdress add constraint UKjc0ovym0067cueqembauu2adg unique (decoder_typ_id, index)
create index IDX609nlvyhickalyx3tbmkfjd9o on decoderTypCV (decoder_typ_id)
create index IDXnflclk51b5pqw2uoffouoiaay on decoderTypCV (cv)
alter table decoderTypCV add constraint UKdm8w4ffejsjm2c3jte48lqs7 unique (decoder_typ_id, cv)
create index IDX107ujivei25hti1ewu7fu9r6c on decoderTypFunktion (decoder_typ_id)
alter table decoderTypFunktion add constraint UKeqktndh36h99pd08tj1ub6q7b unique (decoder_typ_id, reihe, funktion)
alter table epoch add constraint UK_8g0tjpcecyx7pnu04fxbfve4t unique (name)
alter table gattung add constraint UK_9ke2194x83ufbnag389fc9aeg unique (name)
alter table hersteller add constraint UK_e58qjc2bd88un3bjcraw0nova unique (name)
alter table kategorie add constraint UK_fghh9xt5lba3704of5p0bopg2 unique (name)
alter table kupplung add constraint UK_rjysolh7on8xhrlii99i9x9xk unique (name)
alter table land add constraint UK_74up4cvt5vimfy09m7ovfmxif unique (name)
alter table licht add constraint UK_r4vr916urfvy092fnm2aegepe unique (name)
alter table massstab add constraint UK_jlgpbhqy7s4l0rb348p7xl44e unique (name)
alter table motorTyp add constraint UK_pb9anxl7xn6pusljnhpo2mg1x unique (name)
create index IDX12i1obv4ikigux9lrf1y2aeqv on produkt (hersteller_id)
create index IDX8yyoiqptkuiy6l26nt3gfmw00 on produkt (epoch_id)
create index IDXhv6hmp04pcujr2ivr68pvrhuu on produkt (gattung_id)
create index IDXcfp37ql8m5tpg530hcb0plq00 on produkt (bahnverwaltung_id)
create index IDXf47qq0u21qqo7artmr7t3pqqm on produkt (achsfolg_id)
create index IDX5usyc4ul99fmhoanuqvf464ok on produkt (massstab_id)
create index IDX6h0469ro7mxmr08p6kgs72f2w on produkt (spurweite_id)
create index IDX4d1kc5d3supk2b4mx767449xs on produkt (unter_kategorie_id)
create index IDXfhu4evyycls1a1pnix21sadas on produkt (sondermodell_id)
create index IDXsgfrjch0b5mnsqs2q4lbogwwn on produkt (aufbau_id)
create index IDX9qor9gkvq1auv5hsht899fb7d on produkt (licht_id)
create index IDX9b0kwkkabkd7m1i4cxun6gy4o on produkt (kupplung_id)
create index IDXet8hwm31v3gjl8hwdkjnl57si on produkt (vorbild_id)
create index IDXftpfhtwyc1qfkq9f1f2na8d0w on produkt (steuerung_id)
create index IDX3s2ynwk09qsnscdoybqjr77t5 on produkt (decoder_typ_id)
create index IDXhfjhj2rfi3tc81jvs0r6ls7st on produkt (motor_typ_id)
alter table produkt add constraint UKm6wmhl0r2fiortbpwlm57j79y unique (hersteller_id, bestellNr)
create index IDX3056l5epodek0rn9pbmapg7mg on produktTeil (produkt_id)
create index IDXotrkv30skdhfcsay1cyrb0bu5 on produktTeil (teilId)
alter table produktTeil add constraint UKmmsr9br51n5p6no2i3y8buf33 unique (produkt_id, teilId)
alter table protokoll add constraint UK_pxsao93ktnqoob2u03ndnbmec unique (name)
alter table sondermodell add constraint UK_kcrr5n98ewdsfy22gxh6je1l unique (name)
alter table spurweite add constraint UK_jl307ih43drnqd394se5yb52r unique (name)
alter table steuerung add constraint UK_5u32ddl5aef15b8b1ysdnx9m6 unique (name)
create index IDX8r5s8dml89mynays255hn2uk8 on unterKategorie (kategorie_id)
alter table unterKategorie add constraint UKfot353labb2q7sdshom09if28 unique (kategorie_id, name)
create index IDXotuyidfq5691bf5dwfv9oawr on vorbild (unter_kategorie_id)
create index IDXtix27or32th3n2ha2v21yudol on vorbild (antrieb_id)
create index IDXsdwgf1ggv92cxfguyj8rqyiow on vorbild (achsfolg_id)
alter table vorbild add constraint UKbyxljkr4mdsm3abkka6cos9c unique (gattung_id)
alter table wahrung add constraint UK_eukgdbsrh32kntt88ws56hwmk unique (name)
create index IDXmm97srjskiv22u3rlwtr86ydp on zug (zugTyp_id)
alter table zug add constraint UK_jdyfx82xknxyamxcuoq9bfoma unique (name)
create index IDXhlm70ma0k33u73fi8ldv7taix on zugConsist (zug_id)
create index IDXhodrgfya4lh6sbwymx19rfpv0 on zugConsist (artikel_id)
alter table zugConsist add constraint UKlsdaj509scfcd4jlnem95v39w unique (zug_id, position)
alter table zugTyp add constraint UK_ntr7xdly8uynkyoij55ftce3m unique (name)
alter table anderung add constraint anderung_fk1 foreign key (artikel_id) references artikel
alter table artikel add constraint artikel_fk7 foreign key (decoder_id) references decoder
alter table artikel add constraint artikel_fk6 foreign key (kupplung_id) references kupplung
alter table artikel add constraint artikel_fk5 foreign key (licht_id) references licht
alter table artikel add constraint artikel_fk4 foreign key (motor_typ_id) references motorTyp
alter table artikel add constraint artikel_fk1 foreign key (produkt_id) references produkt
alter table artikel add constraint artikel_fk3 foreign key (steuerung_id) references steuerung
alter table artikel add constraint artikel_fk2 foreign key (wahrung_id) references wahrung
alter table decoder add constraint decoder_fk1 foreign key (decoder_typ_id) references decoderTyp
alter table decoder add constraint decoder_fk2 foreign key (protokoll_id) references protokoll
alter table decoderAdress add constraint decoderAdress_fk1 foreign key (decoder_id) references decoder
alter table decoderCV add constraint decoderCV_fk2 foreign key (cv_id) references decoderTypCV
alter table decoderCV add constraint decoderCV_fk1 foreign key (decoder_id) references decoder
alter table decoderFunktion add constraint decoderFunktion_fk1 foreign key (decoder_id) references decoder
alter table decoderFunktion add constraint decoderFunktion_fk2 foreign key (funktion_id) references decoderTypFunktion
alter table decoderTyp add constraint decoderTyp_fk2 foreign key (hersteller_id) references hersteller
alter table decoderTyp add constraint decoderTyp_fk3 foreign key (protokoll_id) references protokoll
alter table decoderTypAdress add constraint decoderTypAdress_fk1 foreign key (decoder_typ_id) references decoderTyp
alter table decoderTypCV add constraint decoderTypCV_fk1 foreign key (decoder_typ_id) references decoderTyp
alter table decoderTypFunktion add constraint decoderTypFunktion_fk1 foreign key (decoder_typ_id) references decoderTyp
alter table land add constraint FKd7c3agprcparnmed569n76eiv foreign key (wahrung_id) references wahrung
alter table produkt add constraint produkt_fk4 foreign key (achsfolg_id) references achsfolg
alter table produkt add constraint produkt_fk9 foreign key (aufbau_id) references aufbau
alter table produkt add constraint produkt_fk3 foreign key (bahnverwaltung_id) references bahnverwaltung
alter table produkt add constraint produkt_fk14 foreign key (decoder_typ_id) references decoderTyp
alter table produkt add constraint produkt_fk1 foreign key (epoch_id) references epoch
alter table produkt add constraint produkt_fk2 foreign key (gattung_id) references gattung
alter table produkt add constraint produkt_fk16 foreign key (hersteller_id) references hersteller
alter table produkt add constraint produkt_fk11 foreign key (kupplung_id) references kupplung
alter table produkt add constraint produkt_fk10 foreign key (licht_id) references licht
alter table produkt add constraint produkt_fk5 foreign key (massstab_id) references massstab
alter table produkt add constraint produkt_fk15 foreign key (motor_typ_id) references motorTyp
alter table produkt add constraint produkt_fk8 foreign key (sondermodell_id) references sondermodell
alter table produkt add constraint produkt_fk6 foreign key (spurweite_id) references spurweite
alter table produkt add constraint produkt_fk13 foreign key (steuerung_id) references steuerung
alter table produkt add constraint produkt_fk7 foreign key (unter_kategorie_id) references unterKategorie
alter table produkt add constraint produkt_fk12 foreign key (vorbild_id) references vorbild
alter table produktTeil add constraint produktTeil_fk1 foreign key (produkt_id) references produkt
alter table produktTeil add constraint produktTeil_fk2 foreign key (teilId) references produkt
alter table unterKategorie add constraint unterKategorie_fk1 foreign key (kategorie_id) references kategorie
alter table vorbild add constraint vorbild_fk5 foreign key (achsfolg_id) references achsfolg
alter table vorbild add constraint vorbild_fk4 foreign key (antrieb_id) references antrieb
alter table vorbild add constraint vorbild_fk3 foreign key (bahnverwaltung_id) references bahnverwaltung
alter table vorbild add constraint vorbild_fk1 foreign key (gattung_id) references gattung
alter table vorbild add constraint vorbild_fk2 foreign key (unter_kategorie_id) references unterKategorie
alter table zug add constraint zug_fk1 foreign key (zugTyp_id) references zugTyp
alter table zugConsist add constraint zugConsist_fk2 foreign key (artikel_id) references artikel
alter table zugConsist add constraint zugConsist_fk1 foreign key (zug_id) references zug
