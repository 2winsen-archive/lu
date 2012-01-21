package lv.lu.mpt.pd2.impl.service;

import java.util.List;

import javax.persistence.Query;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.service.GameService;
import lv.lu.mpt.pd2.model.Game;

public class GameServiceImpl extends BaseService implements GameService {

	@SuppressWarnings("unchecked")
	@Override
	public Game getGame(Game game) {
		List<Game> result = null;
		Query query = null;
		query = getCommonDAO().getEntityManager().createNamedQuery(
				JPQLConst.GameJPQL.QUERY_GET_GAME);
		query.setParameter("date", game.getDate());
		query.setParameter("place", game.getPlace());
		query.setParameter("audience", game.getAudience());
		result = (List<Game>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

}
