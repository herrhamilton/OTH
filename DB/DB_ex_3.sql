-- 7a
SELECT Name
FROM Kunde
WHERE Nr = (SELECT Kundnr
            FROM Auftrag
            WHERE AuftrNr = 1);
-- 7b            
SELECT Name
FROM Personal
WHERE Persnr = (SELECT Persnr
                FROM Auftrag
                WHERE AuftrNr = 1);
            
-- 7c
SELECT Anr Nummer, Bezeichnung
FROM Artikel
WHERE ANr IN (SELECT ArtNr
            FROM Auftragsposten INNER JOIN Auftrag USING (AuftrNr)
            WHERE KundNr = (SELECT Nr
                            FROM Kunde
                            WHERE Nr = (SELECT Kundnr
                                        FROM Auftrag
                                        WHERE AuftrNr = 1)));                                        
                
-- 8
SELECT Name, GebDatum, Gehalt
FROM Personal
WHERE PersNr = ANY (SELECT PersNr
                    FROM Auftrag);
        
-- 9
SELECT PersNr, Name, Gehalt, Aufgabe
FROM Personal
WHERE Gehalt >= ALL (Select Gehalt
                    FROM Personal)
OR Gehalt IN (Select MIN(Gehalt)
            FROM Personal);
                
-- 10
SELECT Name, Aufgabe, Gehalt, Beurteilung
FROM Personal
WHERE PersNr = ANY (SELECT PersNr
                    FROM Auftrag
                    WHERE AuftrNr = ANY (SELECT AuftrNr
                                        FROM Auftragsposten
                                        WHERE Anzahl <= ANY (SELECT MAX(Gesamtpreis/Anzahl)
                                                            FROM Auftragsposten)));

SELECT Name, Aufgabe, Gehalt, Beurteilung
FROM Personal
WHERE PersNr = ANY (SELECT PersNr
                FROM Auftrag
                WHERE AuftrNr = ANY (SELECT AuftrNr
                                FROM Auftragsposten
                                WHERE Anzahl <= ANY (SELECT MAX(Gesamtpreis/Anzahl)
                                                    FROM Auftragsposten)));
                                                    
SELECT Name, Aufgabe, Gehalt, Beurteilung
FROM Personal INNER JOIN (Auftrag INNER JOIN Auftragsposten USING (AuftrNr)) USING(PersNr)
WHERE GesamtPreis/Anzahl = (SELECT MAX(GesamtPreis/Anzahl)
                            FROM Auftragsposten);
                            
-- 11
SELECT PersNr, Name, Gehalt, Vorgesetzt
FROM Personal
WHERE Gehalt = (SELECT MAX(Gehalt)
                FROM Personal
                WHERE Vorgesetzt IS NOT NULL);
                
-- 12
--?