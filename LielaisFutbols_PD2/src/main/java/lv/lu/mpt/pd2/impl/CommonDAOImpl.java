package lv.lu.mpt.pd2.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lv.lu.mpt.pd2.interfaces.CommonDAO;
import lv.lu.mpt.pd2.interfaces.PersistentEntity;

/**
 * Common DAO implementation.
 * For JavaDocs see CommonDAO interface.
 */
public class CommonDAOImpl implements CommonDAO {

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	public void save(PersistentEntity object) {
		if (object != null) {
			if (object.getId() == null) {
				getEntityManager().persist(object);
			} else {
				getEntityManager().merge(object);
			}
		}
	}
	
	public void saveAll(Collection<? extends PersistentEntity> objects) {
		for (PersistentEntity object: objects){
			save(object);
		}
	}

	public <T extends PersistentEntity> T getById(Class<T> clazz, Long id) {
		return getEntityManager().find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentEntity> List<T> findAll(Class<T> clazz) {
		String entity = clazz.getSimpleName();
		return getEntityManager().createQuery("select e FROM " + entity + " e").getResultList();
	}

	public void delete(PersistentEntity object) {
		getEntityManager().remove(object);
	}

}
