package lv.kvd.lu.country;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of CountryDao
 * @author vitalik
 *
 */
public class CountryDaoImpl extends HibernateDaoSupport implements CountryDao {

	@Override
	public Object getRecord(Long id) {
		return (Country) getHibernateTemplate().get(Country.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from Country order by name asc");
	}

	@Override
	public int removeRecord(Long id) {
		return 1;
	}

	@Override
	public void saveRecord(Object obj) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		return null;
	}

}
