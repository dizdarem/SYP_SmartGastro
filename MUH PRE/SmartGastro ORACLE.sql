drop table tisch cascade constraints;
drop table tablet cascade constraints;
drop table produkt cascade constraints;
drop table bestellung cascade constraints;
drop table besteht_aus cascade constraints;

create table tisch(
id INTEGER,
CONSTRAINT pkTisch PRIMARY KEY(id)
);

create table tablet(
id INTEGER,
CONSTRAINT pkTablet PRIMARY KEY(id)
);

create table produkt(
id INTEGER,
bezeichnung VARCHAR2(50),
preis float,
typ VARCHAR2(10),
CONSTRAINT pkProdukt PRIMARY KEY(id)
);

create table bestellung(
id INTEGER,
zeitstempel DATE,
idTisch INTEGER,
idTablet INTEGER,
gesamtpreis float,
CONSTRAINT pkBestellung PRIMARY KEY(id),
CONSTRAINT fkBestellungTisch FOREIGN KEY(idTisch) references tisch(id),
CONSTRAINT fkBestellungTablet FOREIGN KEY(idTablet) references tablet(id),
CONSTRAINT uqZeit UNIQUE(zeitstempel, idTisch, idTablet)
);

create table besteht_aus(
idBestellung INTEGER,
idPosten INTEGER,
idProdukt INTEGER,
CONSTRAINT pkBesteht_Aus PRIMARY KEY(idBestellung, idPosten, idProdukt),
CONSTRAINT pkBesteht_Aus_Bestellung FOREIGN KEY(idBestellung) references bestellung(id),
CONSTRAINT pkBesteht_Aus_Produkt FOREIGN KEY(idProdukt) references produkt(id)
);


insert into tisch VALUES (1);
insert into tisch VALUES (2);
insert into tisch VALUES (3);
insert into tisch VALUES (4);
insert into tisch VALUES (5);
insert into tisch VALUES (6);
insert into tisch VALUES (7);
insert into tisch VALUES (8);
insert into tisch VALUES (9);
insert into tisch VALUES (10);

insert into tablet VALUES (1);
insert into tablet VALUES (2);
insert into tablet VALUES (3);
insert into tablet VALUES (4);
insert into tablet VALUES (5);
insert into tablet VALUES (6);
insert into tablet VALUES (7);
insert into tablet VALUES (8);
insert into tablet VALUES (9);
insert into tablet VALUES (10);
insert into tablet VALUES (11);
insert into tablet VALUES (12);
insert into tablet VALUES (13);
insert into tablet VALUES (14);

insert into produkt VALUES (1,'Coca-Cola 0.33 l', 2.80,'getraenk');
insert into produkt VALUES (2,'Fanta 0.33 l', 2.80,'getraenk');
insert into produkt VALUES (3,'Sprite 0.33 l', 2.80,'getraenk');
insert into produkt VALUES (4,'Almdudler 0.33 l', 2.80,'getraenk');
insert into produkt VALUES (5,'Eistee Pfirsich 0.33 l', 2.80,'getraenk');
insert into produkt VALUES (6,'Eistee Pfirsich 0.5 l', 3.80,'getraenk');
insert into produkt VALUES (7,'Eistee Zitrone 0.33 l', 2.80,'getraenk');
insert into produkt VALUES (8,'Eistee Zitrone 0.5 l', 3.80,'getraenk');
insert into produkt VALUES (9,'Himbeer-Zitrone Soda 0.5 l', 3.40,'getraenk');
insert into produkt VALUES (10,'Himbeer-Zitrone Wasser 0.5 l', 3.20,'getraenk');
insert into produkt VALUES (11,'Holunder Soda 0.5 l', 3.40,'getraenk');
insert into produkt VALUES (12,'Holunder Wasser 0.5 l', 3.20,'getraenk');
insert into produkt VALUES (13,'Erdbeer Pago 0.5 l', 3.90,'getraenk');
insert into produkt VALUES (14,'Mango Pago 0.5 l', 3.90,'getraenk');
insert into produkt VALUES (15,'Marille Pago 0.5 l', 3.90,'getraenk');
insert into produkt VALUES (16,'Orange Pago 0.5 l', 3.90,'getraenk');
insert into produkt VALUES (17,'Villacher Bier 0.5 l', 3.90,'getraenk');


insert into produkt VALUES (17,'Schnitzel Schwein', 6.90,'gericht');
insert into produkt VALUES (18,'Schnitzel Pute', 7.90,'gericht');
insert into produkt VALUES (19,'Schnitzel Kids Pute mit Pommes', 4.90,'gericht');
insert into produkt VALUES (20,'Spaghetti Bolognese 100% Rind', 8.50,'gericht');
insert into produkt VALUES (21,'Spaghetti al Pomodoro', 7.50,'gericht');
insert into produkt VALUES (22,'Penne Arrabiata', 7.90,'gericht');
insert into produkt VALUES (23,'Schlemmerburger 100% Rind mit Potato Wedges', 7.50,'gericht');
insert into produkt VALUES (24,'Putenstreifensalat', 7.20,'gericht');
insert into produkt VALUES (25,'Käsespätzle', 5.70,'gericht');

insert into produkt VALUES(26,'Pommes',2.90,'beilage');
insert into produkt VALUES(27,'Potato Wedges',3.00,'beilage');
insert into produkt VALUES(28,'Ketchup',0.90,'beilage');
insert into produkt VALUES(29,'Mayonnaise',0.90,'beilage');
insert into produkt VALUES(30,'Reis',1.90,'beilage');
insert into produkt VALUES(31,'Kartoffelsalat',2.90,'beilage');
insert into produkt VALUES(32,'Gemüse',1.90,'beilage');
insert into produkt VALUES(33,'Kleiner Salat',2.90,'beilage');
insert into produkt VALUES(34,'Grosser Salat',3.90,'beilage');


insert into produkt VALUES(35,'Hausgemachtes Tiramisu',4.90,'dessert');
insert into produkt VALUES(36,'Pudding',2.90,'dessert');
insert into produkt VALUES(37,'Palatschinken',5.90,'dessert');


insert into bestellung VALUES(1,TO_DATE('26.10.2019 18:42','dd.mm.yyyy hh24.mi'),1,1,0); /*tisch 1 tablet 1*/
insert into bestellung VALUES(2,TO_DATE('26.10.2019 18:40','dd.mm.yyyy hh24.mi'),1,2,0);
insert into bestellung VALUES(3,TO_DATE('26.10.2019 18:41','dd.mm.yyyy hh24.mi'),1,3,0);
insert into bestellung VALUES(4,TO_DATE('26.10.2019 18:40','dd.mm.yyyy hh24.mi'),1,4,0);

insert into bestellung VALUES(5,TO_DATE('26.10.2019 18:42','dd.mm.yyyy hh24.mi'),3,5,0); /*tisch 1 tablet 1*/
insert into bestellung VALUES(6,TO_DATE('26.10.2019 18:40','dd.mm.yyyy hh24.mi'),3,6,0);
insert into bestellung VALUES(7,TO_DATE('26.10.2019 18:41','dd.mm.yyyy hh24.mi'),3,7,0);
insert into bestellung VALUES(8,TO_DATE('26.10.2019 18:40','dd.mm.yyyy hh24.mi'),3,8 ,0);

insert into besteht_aus VALUES(1,1,1);
insert into besteht_aus VALUES(1,2,18);
insert into besteht_aus VALUES(1,3,26);
insert into besteht_aus VALUES(1,4,26);

insert into besteht_aus VALUES(2,1,2);
insert into besteht_aus VALUES(2,2,20);
insert into besteht_aus VALUES(2,3,37);

insert into besteht_aus VALUES(3,1,34);
insert into besteht_aus VALUES(3,2,4);

insert into besteht_aus VALUES(4,1,25);
insert into besteht_aus VALUES(4,2,27);
insert into besteht_aus VALUES(4,3,4);

insert into besteht_aus VALUES(5,1,35);
insert into besteht_aus VALUES(5,2,10);

insert into besteht_aus VALUES(6,1,24);
insert into besteht_aus VALUES(6,2,16);

insert into besteht_aus VALUES(7,1,3);
insert into besteht_aus VALUES(7,2,23);
insert into besteht_aus VALUES(7,3,28);

insert into besteht_aus VALUES(8,1,11);
insert into besteht_aus VALUES(8,2,18);
insert into besteht_aus VALUES(8,3,37);



commit;

select idBestellung,idTisch,to_char(zeitstempel,'dd.mm.yyyy hh24.mi'),idPosten,idProdukt,bezeichnung,preis,typ from besteht_aus
inner join bestellung on besteht_aus.idBestellung = bestellung.id
inner join produkt on besteht_aus.idProdukt = produkt.id
order by idBestellung, idPosten;

select idBestellung,idTisch,to_char(zeitstempel,'dd.mm.yyyy hh24.mi'),bezeichnung,preis,typ from besteht_aus
inner join bestellung on besteht_aus.idBestellung = bestellung.id
inner join produkt on besteht_aus.idProdukt = produkt.id
where produkt.typ = 'gericht' or produkt.typ = 'beilage' or produkt.typ = 'dessert'
order by idBestellung, idPosten;

select idBestellung,idTisch,to_char(zeitstempel,'dd.mm.yyyy hh24.mi'),bezeichnung,preis,typ from besteht_aus
inner join bestellung on besteht_aus.idBestellung = bestellung.id
inner join produkt on besteht_aus.idProdukt = produkt.id
where produkt.typ = 'getraenk'
order by idBestellung, idPosten;


