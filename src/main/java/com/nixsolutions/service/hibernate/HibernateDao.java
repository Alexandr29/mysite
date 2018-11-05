package com.nixsolutions.service.hibernate;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class HibernateDao {
    public HibernateDao() {
    }

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    Object findObject(String hql, String searchValue) {
        Object obj;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            //query.setParameter("search_factor", searchValue);
            if (query.list().isEmpty()) {
                return null;
            }
            obj = query.getSingleResult();
        } catch (Exception e) {
            logger.error("Exception in findObject()", e, e);
            throw e;
        }
        return obj;
    }

    <T>List<T> findList(String hql) {
        List<T> objects;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            if (query.list().isEmpty()) {
                return null;
            }
            objects = query.list();
        } catch (Exception e) {
            logger.error("Exception in findList()", e, e);
            throw e;
        }
        return objects;
    }

    void createObject(Object object) {
        try {
            sessionFactory.getCurrentSession().save(object);
        } catch (Exception e) {
            logger.error("Exception in createObject()", e, e);
            throw e;
        }
    }

    void updateObject(Object object) {
        try {
            sessionFactory.getCurrentSession().merge(object);
        } catch (Exception e) {
            logger.error("Exception in createObject()", e, e);
            throw e;
        }
    }

    void removeObject(Object object) {
        try {
            sessionFactory.getCurrentSession().remove(object);
        } catch (Exception e) {
            logger.error("Exception in createObject()", e, e);
            throw e;
        }
    }
}