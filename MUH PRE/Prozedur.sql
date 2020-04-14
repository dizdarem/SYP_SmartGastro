create or replace PROCEDURE GETGESAMTPREIS 
(
  TABLETID IN NUMBER,
  GESAMTPREIS OUT NUMBER
) AS 
varGesamtpreis NUMBER :=0;
varCurrent NUMBER :=0;
CURSOR curBestellungen IS
SELECT * from bestellung where idTablet = TABLETID  AND bezahlt LIKE 'false';

BEGIN
    FOR varBestellung IN curBestellungen LOOP 
    
        SELECT sum(preis) INTO varCurrent from produkt inner join besteht_aus on produkt.id = besteht_aus.idprodukt
         where besteht_aus.idbestellung = varBestellung.id;
        UPDATE bestellung SET bezahlt = 'true', gesamtpreis = varCurrent where id = varBestellung.id;
         varGesamtpreis := varGesamtpreis + varCurrent;        
    END LOOP;
             DBMS_OUTPUT.PUT_LINE(varGesamtpreis);
    GESAMTPREIS := varGesamtpreis;
END GETGESAMTPREIS;