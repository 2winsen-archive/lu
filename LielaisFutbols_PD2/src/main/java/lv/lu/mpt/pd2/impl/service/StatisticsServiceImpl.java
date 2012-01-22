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

}
