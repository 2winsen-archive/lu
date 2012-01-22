package lv.lu.mpt.pd2.impl.service;

import java.util.List;

import javax.persistence.Query;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.service.TeamService;
import lv.lu.mpt.pd2.model.Team;

public class TeamServiceImpl extends BaseService implements TeamService {

	@SuppressWarnings("unchecked")
	@Override
	public Team getTeam(Team team) {
		List<Team> result = null;
		Query query = null;
		query = getCommonDAO().getEntityManager().createNamedQuery(
				JPQLConst.TeamJPQL.QUERY_GET_TEAM);
		query.setParameter("name", team.getName());
		result = (List<Team>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllTeamNames() {
		Query query = null;
		query = getCommonDAO().getEntityManager().createNamedQuery(
				JPQLConst.TeamJPQL.QUERY_GET_ALL_TEAM_NAMES);
		return (List<String>)query.getResultList();
	}

}
