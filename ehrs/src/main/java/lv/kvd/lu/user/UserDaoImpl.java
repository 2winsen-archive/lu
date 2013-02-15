package lv.kvd.lu.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of UserDao
 * 
 * @author vitalik
 * 
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User getRecord(Long id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from User");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from User where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from User where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

	@Override
	public void saveRecord(Object obj) {
		User user = (User) obj;
		getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public int removeRecord(Long id) {
		Object record = getHibernateTemplate().get(User.class, id);
		getHibernateTemplate().delete(record);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(boolean equals, String fieldName, String value) {
		if(equals) {
			return getHibernateTemplate().find("from User where " + fieldName + "=?", value);
		} else {
			return getHibernateTemplate().find("from User where " + fieldName + "!=?", value);
		}
	}

}
