package lv.kvd.lu.skill;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of SkillDao
 * 
 * @author vitalik
 * 
 */
public class SkillDaoImpl extends HibernateDaoSupport implements SkillDao {

	@Override
	public Object getRecord(Long id) {
		return (Skill) getHibernateTemplate().get(Skill.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return getHibernateTemplate().find("from Skill");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String fieldName, String value) {
		return getHibernateTemplate().find("from Skill where " + fieldName + "=?", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords(String[] fieldNames, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Skill where ");
		for (String string : fieldNames) {
			sb.append(string + " like ? and ");
		}
		sb.delete(sb.length()-5, sb.length());
		return getHibernateTemplate().find(sb.toString(), values);
	}

	@Override
	public int removeRecord(Long id) {
		Object record = getHibernateTemplate().get(Skill.class, id);
		getHibernateTemplate().delete(record);
		return 1;
	}

	@Override
	public void saveRecord(Object obj) {
		Skill skill= (Skill) obj;
		getHibernateTemplate().saveOrUpdate(skill);
	}

}
