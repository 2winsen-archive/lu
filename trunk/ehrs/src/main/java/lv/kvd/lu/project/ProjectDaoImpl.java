package lv.kvd.lu.project;

import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of ProjectDao
 * 
 * @author vitalik
 * 
 */
public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	@Override
	public Object getRecord(Long id) {
		return (Project)getHibernateTemplate().get(Project.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from Project order by name asc");
	}

	@Override
	public int removeRecord(Long id) throws BatchUpdateException {
		Object record = getHibernateTemplate().get(Project.class, id);
		try {
			getHibernateTemplate().delete(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void saveRecord(Object obj) {
		Project project = (Project) obj;
		getHibernateTemplate().saveOrUpdate(project);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from Project where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Project where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

}
