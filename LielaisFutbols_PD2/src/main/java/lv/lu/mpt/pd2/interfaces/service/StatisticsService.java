package lv.lu.mpt.pd2.interfaces.service;

import java.util.List;

import javax.sql.DataSource;


public interface StatisticsService {
	
	DataSource getDataSource();
	
	List<?> getLeagueTable();
	
	List<?> getTop10Scorers();
	
	List<?> getTop5GoalKeepers();
	
	List<?> getTopAggressivePlayers();
	
	List<?> getTopStrictReferees();
}
