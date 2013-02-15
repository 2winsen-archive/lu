package lv.kvd.lu.message;

import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of MessageDao
 * 
 * @author vitalik
 * 
 */
public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {

	@Override
	public Object getRecord(Long id) {
		return (Message) getHibernateTemplate().get(Message.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from Message order by timestamp asc");
	}

	@Override
	public int removeRecord(Long id) throws BatchUpdateException {
		Object record = getHibernateTemplate().get(Message.class, id);
		try {
			getHibernateTemplate().delete(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void saveRecord(Object obj) {
		Message message = (Message) obj;
		getHibernateTemplate().saveOrUpdate(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from Message where " + fieldName + "=? order by timestamp desc", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Message where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String symbol, String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Message where ");
		for (String string : fieldNames) {
			sb.append(string + symbol + "? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

}
