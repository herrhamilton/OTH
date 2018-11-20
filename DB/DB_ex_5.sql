----- UEBUNG 5 -----

CREATE Table Personalalal (
Persnr INT Primary Key,
Name CHAR(25) NOT NULL,
Ort Char(15),
Vorgesetzt INT
    REFERENCES Personal
    ON DELETE SET NULL,
    --ON UPDATE CASCADE, not in Oracle - Never change Keys?
Gehalt NUMERIC(8,2) CHECK(Gehalt BETWEEN 800 AND 9000),
Beurteilung CHAR,
CONSTRAINT MinVerdienst
CHECK(Gehalt >= Coalesce((6-Beurteilung)*400, 800))
);

----- 24 -----
DELETE FROM Personal
WHERE TRIM(Name) LIKE '%Köster' OR Trim(Name) LIKE '%Lambert';

----- 25 -----
UPDATE Personal
SET Gehalt = Gehalt * 1.05
WHERE Beurteilung < 3 OR Beurteilung IS NULL;

----- 26 -----
UPDATE Artikel
SET Preis = Preis+20,
    Steuer = (Preis+20) * 0.19,
    Netto = (Preis+20) - ((Preis+20) * 0.19)
WHERE UPPER(TRIM(Bezeichnung)) LIKE '%RAHMEN%';

----- 27 -----
UPDATE Personal
SET Gehalt = Gehalt*1.02
WHERE TRIM(Aufgabe) = 'Vertreter'
AND   PersNr IN (SELECT PersNr 
                 FROM Personal P
                 WHERE (SELECT SUM(Gesamtpreis)
                        FROM Auftrag INNER JOIN Auftragsposten USING (AuftrNr)
                        WHERE PersNr = P.PersNr) 
                        >= 2000);

----- 28 ----- Any way to automate PersNr?
INSERT INTO Personal(PersNr, NAME,STRASSE,PLZ,ORT,GEBDATUM,STAND,VORGESETZT,GEHALT,BEURTEILUNG,AUFGABE)
VALUES(10,'Bob Rogers', 'Ghetto 1337', 92517, 'Diggakaff', DATE'2003-06-22', 'ledig', 1, 1200, NULL, 'hot mixtapes');

-----
Rollback;