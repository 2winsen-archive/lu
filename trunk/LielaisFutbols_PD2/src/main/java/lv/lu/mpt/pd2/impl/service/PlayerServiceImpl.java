package lv.lu.mpt.pd2.impl.service;

import java.util.List;

import javax.persistence.Query;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.service.PlayerService;
import lv.lu.mpt.pd2.model.Player;

public class PlayerServiceImpl extends BaseService implements PlayerService {

	@SuppressWarnings("unchecked")
	@Override
	public Player getPlayer(Player player) {
		List<Player> result = null;
		Query query = null;
		query = getCommonDAO().getEntityManager().createNamedQuery(
				JPQLConst.PlayerJPQL.QUERY_GET_PLAYER);
		query.setParameter("number", player.getNumber());
		query.setParameter("teamId", player.getTeam().getId());
		result = (List<Player>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

}
