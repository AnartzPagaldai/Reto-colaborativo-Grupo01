-- Crear tabla temporal para emparejamiento
CREATE GLOBAL TEMPORARY TABLE temp_pairings (
  team1 VARCHAR2(50),
  team2 VARCHAR2(50)
);

-- Llenar tabla temporal con todas las posibles combinaciones de equipos
DECLARE
  i INTEGER := 1;
BEGIN
  FOR team1 IN (SELECT team_name FROM teams) LOOP -- "teams" es la tabla que contiene los equipos
    FOR team2 IN (SELECT team_name FROM teams WHERE team_name > team1.team_name) LOOP
      INSERT INTO temp_pairings (team1, team2) VALUES (team1.team_name, team2.team_name);
      i := i + 1;
    END LOOP;
  END LOOP;
END;

-- Generar partidos por ronda
DECLARE
  i INTEGER := 1;
BEGIN
  FOR round IN 1..11 LOOP -- 11 rondas
    DECLARE
      matches_this_round INTEGER := 0;
      team_matches VARCHAR2(50);
    BEGIN
      FOR pairing IN (SELECT * FROM temp_pairings WHERE team1 NOT IN (SELECT team_matches FROM matches WHERE round = round) AND team2 NOT IN (SELECT team_matches FROM matches WHERE round = round)) LOOP
        IF matches_this_round >= 6 THEN -- 6 partidos por ronda
          EXIT;
        END IF;
        
        INSERT INTO matches (home_team, away_team, round)
        VALUES (pairing.team1, pairing.team2, round);
        INSERT INTO matches (home_team, away_team, round)
        VALUES (pairing.team2, pairing.team1, round);
        
        team_matches := pairing.team1;
        INSERT INTO matches (home_team, away_team, round)
        SELECT pairing.team1, pairing.team2, round FROM temp_pairings 
        WHERE pairing.team1 <> team_matches AND pairing.team2 <> team_matches 
        AND pairing.team1 NOT IN (SELECT team_matches FROM matches WHERE round = round) AND pairing.team2 NOT IN (SELECT team_matches FROM matches WHERE round = round) AND ROWNUM = 1;
        
        INSERT INTO matches (home_team, away_team, round)
        SELECT pairing.team2, pairing.team1, round FROM temp_pairings 
        WHERE pairing.team1 <> team_matches AND pairing.team2 <> team_matches 
        AND pairing.team1 NOT IN (SELECT team_matches FROM matches WHERE round = round) AND pairing.team2 NOT IN (SELECT team_matches FROM matches WHERE round = round) AND ROWNUM = 1);
        
        
        matches_this_round := matches_this_round + 3;
      END LOOP;
    END;
  END LOOP;
END;
