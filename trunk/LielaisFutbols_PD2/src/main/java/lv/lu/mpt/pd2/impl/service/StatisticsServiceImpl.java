package lv.lu.mpt.pd2.impl.service;

import java.util.List;

import javax.sql.DataSource;

import lv.lu.mpt.pd2.interfaces.service.StatisticsService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class StatisticsServiceImpl implements StatisticsService {
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<?> getLeagueTable() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select * from team";
//			"SELECT name," +
//			       "SUM(tempsum)" +
//			"FROM   (SELECT t.name," +
//			               "SUM(g.team1points) AS tempsum" +
//			        "FROM   team AS t"+
//			               "INNER JOIN game AS g" +
//			                 "ON ( t.id = g.team1_id )" +
//			        "GROUP  BY t.name" +
//			        "UNION" +
//			        "SELECT t.name," +
//			               "SUM(g.team2points)" +
//			        "FROM   team AS t" +
//			               "INNER JOIN game AS g" +
//			                 "ON ( t.id = g.team2_id )" +
//			        "GROUP  BY t.name) AS query" +
//			"GROUP  BY name";
			
		
		SqlRowSet rowSet = select.queryForRowSet(query);
		return null;
	}

	@Override
	public List<?> getTop10Scorers() {
		return null;
	}

	@Override
	public List<?> getTop5GoalKeepers() {
		return null;
	}

	@Override
	public List<?> getTopAggressivePlayers() {
		return null;
	}

	@Override
	public List<?> getTopStrictReferees() {
		return null;
	}

}
