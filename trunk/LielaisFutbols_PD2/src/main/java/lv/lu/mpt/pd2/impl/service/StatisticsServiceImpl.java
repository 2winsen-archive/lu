package lv.lu.mpt.pd2.impl.service;

import java.math.BigDecimal;
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

		Object[][] data = new Object[rows.size()][8];;
		int rowIndex = 0;
		int colIndex = 0;
		for (Map<String, Object> row : rows) {
			data[rowIndex][colIndex] = row.get("name");
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totalpoints"));
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totalwins"));
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totallosses"));
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totalwinsinextratime"));
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totallossesinextratime"));
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totalgoalsscored"));
			colIndex++;
			data[rowIndex][colIndex] = parseNulls(row.get("totalgoalslost"));
			rowIndex++;
			colIndex = 0;
		}
		return data;
	}

	@Override
	public Object[][] getTop10Scorers() {
		return null;
	}

	@Override
	public Object[][] getTop5GoalKeepers() {
		return null;
	}

	@Override
	public Object[][] getTopAggressivePlayers() {
		return null;
	}

	@Override
	public Object[][] getTopStrictReferees() {
		return null;
	}
	
	private Integer parseNulls(Object value) {
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

}
