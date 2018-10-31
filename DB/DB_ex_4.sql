-- Alle Mitarbeiter fehlen, die nie was verkauft haben
SELECT Persnr, Name, COUNT (*) as AnzahlAuftrag
FROM Personal NATURAL INNER JOIN Auftrag
GROUP BY Persnr, Name;

-- korrekte Variante
SELECT Persnr, Name, COUNT (AuftrNr) as AnzahlAuftrag
FROM Personal NATURAL LEFT OUTER JOIN Auftrag
GROUP BY Persnr, Name;

-- ohne Outer Join: "Im Kommerziellen Bereich: Nicht!" &  "verwenden Sie den Full Outer Join NICHT."
SELECT Persnr, Name, COUNT (*) as AnzahlAuftrag
FROM Personal NATURAL INNER JOIN Auftrag
GROUP BY Persnr, Name
UNION
SELECT Persnr, Name, 0
FROM Personal
WHERE Persnr NOT IN (SELECT Persnr
                    FROM Auftrag);
                    
-- FEHLER bei NULL Werten! (Azubi-Bewertung)
SELECT SUM(12 * Gehalt + 1000 * (6-Beurteilung)) AS Jahrespersonalkosten
FROM Personal;

-- Funktion Coalesce: Liefert Alternative (2.Param) bei NULL-Werten
SELECT SUM(12 * Gehalt + COALESCE(1000 * (6-Beurteilung), 1000)) AS Jahrespersonalkosten
FROM Personal;

-- etwas komplexer
SELECT Persnr, Name, COALESCE(SUM(Gesamtpreis),0) AS Summe
FROM Personal NATURAL LEFT OUTER JOIN (Auftrag NATURAL INNER JOIN Auftragsposten)
GROUP BY Persnr, Name;


----- 19 -----
SELECT P.Name, V.Gehalt-P.Gehalt Diff
FROM Personal P INNER JOIN Personal V ON P.Vorgesetzt = V.PersNr;
-- NULL Werte bei Vorgesetzt

----- 20 -----
SELECT P.Name, COALESCE(V.Gehalt-P.Gehalt, 0) Diff
FROM Personal P LEFT OUTER JOIN Personal V ON P.Vorgesetzt = V.PersNr;

----- 21 -----
ACCEPT Input PROMPT 'Name des Mitarbeiters: ';
SELECT P.Name, COALESCE(V.Gehalt-P.Gehalt, 0) Diff
FROM Personal P LEFT OUTER JOIN Personal V ON P.Vorgesetzt = V.PersNr
WHERE UPPER(TRIM(P.Name)) = UPPER(TRIM('&Input'));