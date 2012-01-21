package lv.lu.mpt.pd2.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDAOImpl {

    protected EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
