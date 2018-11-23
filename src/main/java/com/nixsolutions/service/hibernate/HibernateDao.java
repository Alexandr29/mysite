package com.nixsolutions.service.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class HibernateDao {
    public HibernateDao() {
    }

    @Autowired
    private SessionFactory sessionFactory;

    <T> void updateObject(T object) {
        try {
            sessionFactory.getCurrentSession().clear();
            sessionFactory.getCurrentSession().update(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    <T> List<T> findList(String hql) {
        List<T> objects;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            if (query.list().isEmpty()) {
                return null;
            }
            objects = query.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    <T> void createObject(T object) {
        try {
            sessionFactory.getCurrentSession().save(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    <T> void removeObject(T object) {
        try {
            sessionFactory.getCurrentSession().clear();
            sessionFactory.getCurrentSession().remove(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    Object findORoleById(String hql, Long id) {
        Object obj;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("search_factor", id);
            if (query.list().isEmpty()) {
                return null;
            }
            obj = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    Object findObject(String hql, String searchValue) {
        Object obj;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("search_factor", searchValue);
            if (query.list().isEmpty()) {
                return null;
            }
            obj = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}