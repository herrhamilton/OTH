----- 13 -----
SELECT A.AuftrNr, A.Datum, K.Name KundName, P.Name AS PersName
FROM Auftrag A, Personal P, Kunde K
WHERE A.Kundnr = K.Nr AND A.PersNr = P.PersNr
ORDER BY A.AuftrNr;

SELECT AuftrNr, Datum, Personal.Name AS PersName, Kunde.Name AS KundName
FROM Auftrag INNER JOIN Personal USING (PersNr) INNER JOIN Kunde ON Auftrag.KundNr = Kunde.Nr
ORDER BY AuftrNr;

----- 17 ------
SELECT TRIM(Ort) FROM Personal
UNION
SELECT TRIM(Ort) FROM Kunde
UNION
SELECT TRIM(Ort) FROM Lieferant;

------------------------------------------------------------------------------
----- 18 -----
-- "Die Anf‰nger beiﬂen sich hier bitte nicht die Z‰hne aus"
-- SELECT alle Mitarbeiter
-- SORT by Gehalt
-- PRINT die Mitarbeiter zu den ersten "3" Geh‰ltern

SELECT *
FROM Personal
WHERE Gehalt IN ( SELECT Gehalt--3.Gehalt
                  FROM ( SELECT Gehalt FROM Personal ORDER BY Gehalt DESC)
                  WHERE ROWNUM <= 3 -- !!! Herstellerspezifisch 
                );

-- Eine Lˆsung
SELECT *
FROM Personal P
WHERE -- Anzahl Mehrverdiener < 3 
    (SELECT COUNT(*)
    FROM  Personal Pers
    WHERE Pers.Gehalt > P.Gehalt) < 3;


                  