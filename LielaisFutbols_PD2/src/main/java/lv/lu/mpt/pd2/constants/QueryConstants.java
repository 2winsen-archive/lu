package lv.lu.mpt.pd2.constants;

public class QueryConstants {
	public static final String LEAGUE_TABLE_QUERY = 		
		"SELECT allteamsquery.id, " +
		       "allteamsquery.name, " +
		       "totalpointsquery.totalpoints, " +
		       "totalwinsquery.totalwins, " +
		       "totallossesquery.totallosses, " +
		       "totalwinsinextratimequery.totalwinsinextratime, " +
		       "totallossesinextratimequery.totallossesinextratime, " +
		       "totalgoalsscoredquery.totalgoalsscored, " +
		       "totalgoalslostquery.totalgoalslost " +
		"FROM   team AS allteamsquery " +
		       "LEFT JOIN ( " +
		                 "SELECT id, " +
		                        "name, " +
		                        "SUM(temppoints) AS totalpoints " +
		                  "FROM   (SELECT t.id, " +
		                                 "t.name, " +
		                                 "SUM(g.team1points) AS temppoints " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON ( t.id = g.team1_id ) " +
		                          "GROUP  BY t.name " +
		                          "UNION " +
		                          "SELECT t.id, " +
		                                 "t.name, " +
		                                 "SUM(g.team2points) AS temppoints " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON ( t.id = g.team2_id ) " +
		                          "GROUP  BY t.name) AS querytotalpoints " +
		                  "GROUP  BY name) AS totalpointsquery " +
		         "ON allteamsquery.id = totalpointsquery.id " +
		       "LEFT JOIN ( " +
		                 "SELECT id, " +
		                        "name, " +
		                        "SUM(wins) AS totalwins " +
		                  "FROM   (SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS wins " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team2_id " +
		                          "WHERE  g.team1points < team2points " +
		                          "GROUP  BY t.name " +
		                          "UNION " +
		                          "SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS wins " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team1_id " +
		                          "WHERE  g.team1points > team2points " +
		                          "GROUP  BY t.name) AS querytotalwins " +
		                  "GROUP  BY name) AS totalwinsquery " +
		         "ON allteamsquery.id = totalwinsquery.id " +
		       "LEFT JOIN ( " +
		                 "SELECT id, " +
		                        "name, " +
		                        "SUM(losses) AS totallosses " +
		                  "FROM   (SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS losses " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team2_id " +
		                          "WHERE  g.team1points > team2points " +
		                          "GROUP  BY t.name " +
		                          "UNION " +
		                          "SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS losses " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team1_id " +
		                          "WHERE  g.team1points < team2points " +
		                          "GROUP  BY t.name) AS querytotalwins " +
		                  "GROUP  BY name) AS totallossesquery " +
		         "ON allteamsquery.id = totallossesquery.id " +
		       "LEFT JOIN ( " +
		                 "SELECT id, " +
		                        "name, " +
		                        "SUM(wins) AS totalwinsinextratime " +
		                  "FROM   (SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS wins " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team2_id " +
		                          "WHERE  g.team1points < team2points " +
		                                 "AND g.extratime = 1 " +
		                          "GROUP  BY t.name " +
		                          "UNION " +
		                          "SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS wins " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team1_id " +
		                          "WHERE  g.team1points > team2points " +
		                                 "AND g.extratime = 1 " +
		                          "GROUP  BY t.name) AS querytotalwins " +
		                  "GROUP  BY name) AS totalwinsinextratimequery " +
		         "ON allteamsquery.id = totalwinsinextratimequery.id " +
		       "LEFT JOIN ( " +
		                 "SELECT id, " +
		                        "name, " +
		                        "SUM(losses) AS totallossesinextratime " +
		                  "FROM   (SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS losses " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team2_id " +
		                          "WHERE  g.team1points > team2points " +
		                                 "AND g.extratime = 1 " +
		                          "GROUP  BY t.name " +
		                          "UNION " +
		                          "SELECT t.id, " +
		                                 "t.name, " +
		                                 "COUNT(*) AS losses " +
		                          "FROM   team AS t " +
		                                 "INNER JOIN game AS g " +
		                                   "ON t.id = g.team1_id " +
		                          "WHERE  g.team1points < team2points " +
		                                 "AND g.extratime = 1 " +
		                          "GROUP  BY t.name) AS querytotalwins " +
		                  "GROUP  BY name) AS totallossesinextratimequery " +
		         "ON allteamsquery.id = totallossesinextratimequery.id " +
		       "LEFT JOIN ( " +
		                 "SELECT t.id, " +
		                        "t.name, " +
		                        "COUNT(*) AS totalgoalsscored " +
		                  "FROM   team AS t " +
		                         "INNER JOIN goal AS g " +
		                           "ON g.teamscored_id = t.id " +
		                  "GROUP  BY t.name) AS totalgoalsscoredquery " +
		         "ON allteamsquery.id = totalgoalsscoredquery.id " +
		       "LEFT JOIN ( " +
		                 "SELECT t.id, " +
		                        "t.name, " +
		                        "COUNT(*) AS totalgoalslost " +
		                  "FROM   team AS t " +
		                         "INNER JOIN goal AS g " +
		                           "ON g.teamlost_id = t.id " +
		                  "GROUP  BY t.name) AS totalgoalslostquery " +
		         "ON allteamsquery.id = totalgoalslostquery.id  " +
         "ORDER BY totalpoints DESC";
	
	public static final String TOP_10_SCORERS_QUERY =
		"SELECT players.id, " +
		    "players.firstname, " +
		    "players.lastname, " +
		    "t.name, " +
		    "scorersquery.goals, " +
		    "assistantsquery.assists " +
		"FROM   player AS players " +
		    "JOIN team AS t " +
		      "ON t.id = players.team_id " +
		    "LEFT JOIN (SELECT p.id, " +
		                      "p.firstname, " +
		                      "p.lastname, " +
		                      "COUNT(*) AS goals " +
		               "FROM   player AS p " +
		                      "INNER JOIN goal AS g " +
		                        "ON p.id = g.player_id " +
		               "GROUP  BY p.id " +
		               "ORDER  BY goals DESC) AS scorersquery " +
		      "ON players.id = scorersquery.id " +
		    "LEFT JOIN (SELECT p.id, " +
		                      "p.firstname, " +
		                      "p.lastname, " +
		                      "COUNT(*) AS assists " +
		               "FROM   player AS p " +
		                      "INNER JOIN goal_assistant AS ga " +
		                        "ON ga.player_id = p.id " +
		               "GROUP  BY p.id) AS assistantsquery " +
		      "ON players.id = assistantsquery.id " +
		"GROUP  BY players.id " +
		"ORDER  BY scorersquery.goals DESC, " +
		       "assistantsquery.assists DESC " +
		"LIMIT  10";
	
	public static final String TOP_5_GOALKEEPERS_QUERY =
		"SELECT query.id, " +
		       "query.firstname, " +
		       "query.lastname, " +
		       "query.name, " +
		       "( goalslostquery.goalslost / query.gamesplayed ) AS " +
		       "averagegoalslostpergame " +
		"FROM   (SELECT p.id, " +
		               "p.firstname, " +
		               "p.lastname, " +
		               "t.name, " +
		               "p.gamesplayed " +
		        "FROM   player AS p " +
		               "LEFT JOIN team AS t " +
		                 "ON p.team_id = t.id " +
		               "LEFT JOIN goal AS g " +
		                 "ON g.goalkeeperlost_id = p.id " +
		        "WHERE  p.ROLE = 0 " +
		               "AND gamesplayed IS NOT NULL " +
		        "GROUP  BY p.id) AS query " +
		       "LEFT JOIN (SELECT p.id, " +
		                         "p.firstname, " +
		                         "p.lastname, " +
		                         "COUNT(*) AS goalslost " +
		                  "FROM   player AS p " +
		                         "INNER JOIN goal AS g " +
		                           "ON g.goalkeeperlost_id = p.id " +
		                  "WHERE  p.ROLE = 0 " +
		                         "AND gamesplayed IS NOT NULL " +
		                  "GROUP  BY p.id) AS goalslostquery " +
		         "ON query.id = goalslostquery.id " +
		"ORDER  BY averagegoalslostpergame " +
		"LIMIT  5";

	public static final String TOP_AGGRESSIVE_PLAYERS_QUERY =
		"SELECT pla.id, " +
			"pla.firstname, " +
		    "pla.lastname, " +
		    "t.name, " +
		    "COUNT(*) AS penalties " +
		"FROM   penalty AS pen " +
		    "INNER JOIN player AS pla " +
		      "ON pen.player_id = pla.id " +
		    "INNER JOIN team AS t " +
		      "ON pla.team_id = t.id " +
		"GROUP  BY pen.id " +
		"ORDER  BY penalties"; 	
	
	public static final String TOP_STRICT_REFEREES_QUERY =
		"SELECT r.id," +
			"r.firstname, " +
		    "r.lastname, " +
		    "COUNT(*) AS penalties " +
		"FROM   referee AS r " +
		    "INNER JOIN game AS g " +
		      "ON g.seniorreferee_id = r.id " +
		    "INNER JOIN penalty AS p " +
		      "ON p.game_id = g.id " +
		"GROUP  BY r.id " +
		"ORDER  BY penalties DESC";  	
}
