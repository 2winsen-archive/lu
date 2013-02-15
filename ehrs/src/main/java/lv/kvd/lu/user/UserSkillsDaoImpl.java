package lv.kvd.lu.user;

import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of UserSkillsDao
 * 
 * @author vitalik
 * 
 */
public class UserSkillsDaoImpl extends HibernateDaoSupport implements UserSkillsDao {

	@Override
	public Object getRecord(Long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from UserSkills where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from UserSkills where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

	@Override
	public int removeRecord(Long id) throws BatchUpdateException {
		Object record = getHibernateTemplate().get(UserSkills.class, id);
		getHibernateTemplate().delete(record);
		return 1;
	}
	
	@Override
	public int removeRecord(Object id) throws BatchUpdateException {
		Long longId = new Long(id.toString());
		Object record = getHibernateTemplate().get(UserSkills.class, longId);
		getHibernateTemplate().delete(record);
		return 1;
	}

	@Override
	public void saveRecord(Object obj) {
		UserSkills userSkills = (UserSkills) obj;
		getHibernateTemplate().saveOrUpdate(userSkills);
	}

}
