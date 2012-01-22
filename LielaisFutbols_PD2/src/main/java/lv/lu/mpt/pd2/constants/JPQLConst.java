package lv.lu.mpt.pd2.constants;

/**
 * Put names for named JPQL queries here
 */
public final class JPQLConst {

	public interface TeamJPQL {
		String QUERY_GET_TEAM = "getTeam";
		String QUERY_GET_ALL_TEAM_NAMES = "getAllTeamNames";
	}
	
	public interface GameJPQL {
		String QUERY_GET_GAME = "getGame";
	}

	public interface PlayerJPQL {
		String QUERY_GET_PLAYER = "getPlayer";
	}
	
	public interface RefereeJPQL {
		String QUERY_GET_REFEREE = "getReferee";
	}

}
