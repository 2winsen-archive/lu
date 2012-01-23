package lv.lu.mpt.pd2.impl.service;

import java.util.List;

import javax.persistence.Query;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.service.RefereeService;
import lv.lu.mpt.pd2.model.Referee;

public class RefereeServiceImpl extends BaseService implements RefereeService {

	@SuppressWarnings("unchecked")
	@Override
	public Referee getReferee(Referee referee) {
		List<Referee> result = null;
		Query query = null;
		query = getCommonDAO().getEntityManager().createNamedQuery(
				JPQLConst.RefereeJPQL.QUERY_GET_REFEREE);
		query.setParameter("firstName", referee.getFirstName());
		query.setParameter("lastName", referee.getLastName());
		result = (List<Referee>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<Referee> getAllReferees() {
		return (List<Referee>)getCommonDAO().findAll(Referee.class);
	}

}
