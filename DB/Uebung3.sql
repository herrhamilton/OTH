SELECT *
FROM Personal P1
WHERE EXISTS (SELECT *
                FROM Personal
                WHERE Persnr = 3
                AND Gehalt > P1.Gehalt);
                
SELECT Ort, COUNT (*) AS Anzahl
FROM Personal
GROUP BY Ort;

SELECT Ort, COUNT (*) AS Anzahl
FROM Personal
GROUP BY Ort
HAVING COUNT (*)>1;

SELECT *
FROM (SELECT Ort, COUNT (*) AS Anzahl
    FROM Personal
    GROUP BY Ort) Zwischentabelle
WHERE Anzahl > 1;

SELECT 'Mittleres Auftragsvolumen:', AVG(Auftragsvolumen)
FROM (SELECT SUM(Gesamtpreis) AS Auftragsvolumen
        FROM Auftragsposten
        GROUP BY Auftrnr) Auftragspreis;
        
SELECT Ort FROM Personal
UNION
SELECT Ort FROM Kunde;
-- UNION ALL viel performanter als UNION
SELECT Ort FROM Personal
UNION ALL
SELECT Ort FROM Kunde;

-- Oracle warnt vor Natural Inner Join, der Optimizer macht hier gerne Fehler
SELECT *
FROM Auftrag NATURAL INNER JOIN Personal;

SELECT *
FROM Auftrag INNER JOIN Personal USING (Persnr);

SELECT AuftrNr, Datum, Kundnr, Personal.*
FROM Auftrag INNER JOIN Personal ON Auftrag.Persnr = Personal.PersNr;
-- "INNER JOIN" zu Komma & "ON" zu "WHERE" --> Äquivalent
SELECT Auftrnr, Datum, Kundnr, Personal.*
FROM Auftrag, Personal
WHERE Auftrag.Persnr = Personal.Persnr;