package lv.lu.mpt.pd2.interfaces.service;

import java.util.List;

import lv.lu.mpt.pd2.model.Team;

public interface TeamService {

	Team getTeam(Team team);
	
	List<String> getAllTeamNames();
}
