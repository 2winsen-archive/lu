package lv.kvd.lu.group;

import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of GroupDao
 * 
 * @author vitalik
 * 
 */
public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {

	@Override
	public Object getRecord(Long id) {
		return (Group) getHibernateTemplate().get(Group.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from Group order by name asc");
	}

	@Override
	public int removeRecord(Long id) throws BatchUpdateException {
		Object record = getHibernateTemplate().get(Group.class, id);
		try {
			getHibernateTemplate().delete(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void saveRecord(Object obj) {
		Group group = (Group) obj;
		getHibernateTemplate().saveOrUpdate(group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from Group where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Group where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

}
