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
}
