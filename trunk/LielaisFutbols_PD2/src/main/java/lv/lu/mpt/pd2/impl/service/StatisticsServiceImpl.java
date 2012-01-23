package lv.lu.mpt.pd2.impl.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import lv.lu.mpt.pd2.constants.QueryConstants;
import lv.lu.mpt.pd2.interfaces.service.StatisticsService;

import org.springframework.jdbc.core.JdbcTemplate;

public class StatisticsServiceImpl implements StatisticsService {
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Object[][] getLeagueTable() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstants.LEAGUE_TABLE_QUERY);

		Object[][] data = new Object[rows.size()][8];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("name");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totalpoints"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totalwins"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totallosses"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totalwinsinextratime"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totallossesinextratime"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totalgoalsscored"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("totalgoalslost"));
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}

	@Override
	public Object[][] getTop10Scorers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstants.TOP_10_SCORERS_QUERY);

		Object[][] data = new Object[rows.size()][5];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("lastname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("name");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("goals"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("assists"));
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}

	@Override
	public Object[][] getTop5GoalKeepers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstants.TOP_5_GOALKEEPERS_QUERY);

		Object[][] data = new Object[rows.size()][4];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("lastname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("name");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToDouble(row.get("averagegoalslostpergame"), "#.#");
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}

	@Override
	public Object[][] getTopAggressivePlayers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstants.TOP_AGGRESSIVE_PLAYERS_QUERY);

		Object[][] data = new Object[rows.size()][4];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("lastname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("name");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("penalties"));
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}

	@Override
	public Object[][] getTopStrictReferees() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstants.TOP_STRICT_REFEREES_QUERY);

		Object[][] data = new Object[rows.size()][3];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("lastname");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("penalties"));
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}
	
	@Override
	public Object[][] getTopMostPopularNames() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstants.TOP_MOST_POPULAR_NAMES_QUERY);

		Object[][] data = new Object[rows.size()][2];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("frequency");
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}
	
	@Override
	public Object[][] getTeamStatistics(String teamName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = 
			"SELECT p.number, " +
			       "p.ROLE, " +
			       "p.firstname, " +
			       "p.lastname, " +
			       "p.gamesplayed, " +
			       "p.gamesplayedinmainlineup, " +
			       "p.minutesplayed, " +
			       "p.goalscount, " +
			       "p.assistscount, " +
			       "IF(p.ROLE = 0, p.goalslostcount, NULL)                     AS " +
			       "goalslostcount, " +
			       "IF(p.ROLE = 0, ( p.goalslostcount / p.gamesplayed ), NULL) AS " +
			       "averagegoalslost, " +
			       "p.yellowcardscount, " +
			       "p.redcardscount " +
			"FROM   player AS p " +
			       "INNER JOIN team AS t " +
			         "ON t.id = p.team_id " +
			"WHERE  t.name = '" + teamName +"' " +
			"ORDER  BY p.ROLE DESC";	
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		Object[][] data = new Object[rows.size()][13];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("number");
			colIndex++;
			data[rowIndex][colIndex] = parseToRole(row.get("ROLE"));
			colIndex++;
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("lastname");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("gamesplayed"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("gamesplayedinmainlineup"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("minutesplayed"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("goalscount"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("assistscount"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsEmpty(row.get("goalslostcount"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsEmpty(row.get("averagegoalslost"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("yellowcardscount"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("redcardscount"));
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}
	
	@Override
	public Object[][] getRefereeStatistics(Long refereeId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = 
			"SELECT pl.number, " +
			       "pl.firstname, " +
			       "pl.lastname, " +
			       "pl.ROLE, " +
			       "t.name as playersTeam, " +
			       "p.minutes, " +
			       "p.seconds, " +
			       "g.DATE, " +
			       "g.place, " +
			       "t1.name as team1Name, " +
			       "t2.name as team2Name " +
			"FROM   referee AS r " +
			       "INNER JOIN game AS g " +
			         "ON g.seniorreferee_id = r.id " +
			       "INNER JOIN penalty AS p " +
			         "ON p.game_id = g.id " +
			       "INNER JOIN player AS pl " +
			         "ON pl.id = p.player_id " +
			       "INNER JOIN team AS t " +
			         "ON pl.team_id = t.id " +
			       "INNER JOIN team AS t1 " +
			         "ON t1.id = g.team1_id " +
			       "INNER JOIN team AS t2 " +
			         "ON t2.id = g.team2_id " +
			"WHERE  r.id = "+ refereeId;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		Object[][] data = new Object[rows.size()][11];
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("number");
			colIndex++;
			data[rowIndex][colIndex] = parseToRole(row.get("ROLE"));
			colIndex++;
			data[rowIndex][colIndex] = row.get("firstname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("lastname");
			colIndex++;
			data[rowIndex][colIndex] = row.get("playersTeam");
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("minutes"));
			colIndex++;
			data[rowIndex][colIndex] = parseNullsToInteger(row.get("seconds"));
			colIndex++;
			data[rowIndex][colIndex] = row.get("DATE");
			colIndex++;
			data[rowIndex][colIndex] = row.get("place");
			colIndex++;
			data[rowIndex][colIndex] = row.get("team1Name");
			colIndex++;
			data[rowIndex][colIndex] = row.get("team2Name");
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}
	
	private Integer parseNullsToInteger(Object value) {
		if (value == null) {
			return 0;
		}
		if (value instanceof BigDecimal) {
			return ((BigDecimal)value).intValue();	
		}
		if (value instanceof Long) {
			return ((Long)value).intValue();	
		}
		return (Integer)value;
	}
	
	private Double parseNullsToDouble(Object value, String formatPattern) {
		if (value == null) {
			return 0D;
		}
		DecimalFormat oneDForm = new DecimalFormat(formatPattern);
        return Double.valueOf(oneDForm.format(value));
	}
	
	private String parseNullsEmpty(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	private String parseToRole(Object value) {
		switch ((Integer)value) {
		case 0: return "Goalkeeper";
		case 1: return "Defender";
		case 2: return "Forward";
		default: return "Not defined";
		}
	}

}
