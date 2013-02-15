package lv.kvd.lu.office;

import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of OfficeDao
 * 
 * @author vitalik
 * 
 */
public class OfficeDaoImpl extends HibernateDaoSupport implements OfficeDao {

	@Override
	public Object getRecord(Long id) {
		return (Office)getHibernateTemplate().get(Office.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from Office order by name asc");
	}

	@Override
	public int removeRecord(Long id) throws BatchUpdateException {
		Object record = getHibernateTemplate().get(Office.class, id);
		try {
			getHibernateTemplate().delete(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void saveRecord(Object obj) {
		Office office = (Office) obj;
		getHibernateTemplate().saveOrUpdate(office);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from Office where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Office where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

}
