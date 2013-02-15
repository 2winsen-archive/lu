package lv.kvd.lu.position;

import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of PositionDao
 * 
 * @author vitalik
 * 
 */
public class PositionDaoImpl extends HibernateDaoSupport implements PositionDao {

    public Position getRecord(Long id) {
        return (Position) getHibernateTemplate().get(Position.class, id);
    }

    @SuppressWarnings("unchecked")
	public List getRecords() {
        return getHibernateTemplate().find("from Position order by name asc");
    }

    public void saveRecord(Position position) {
        getHibernateTemplate().saveOrUpdate(position);
    }

    public int removeRecord(Long id) throws BatchUpdateException {
		Object record = getHibernateTemplate().get(Position.class, id);
		try {
			getHibernateTemplate().delete(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
    }

	@Override
	public void saveRecord(Object obj) {
		Position position = (Position) obj;
		getHibernateTemplate().saveOrUpdate(position);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from Position where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Position where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

}
