package com.nixsolutions.service.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class HibernateDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    <T> T findObject(String hql, String searchValue) throws Exception {
        T obj;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("search_factor", searchValue);
            if (query.list().isEmpty()) {
                return null;
            }
            obj = (T) query.getSingleResult();
            transaction.rollback();
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        return obj;
    }

    <T> List<T> findList(String hql) throws Exception {
        List<T> objects;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            objects = query.list();
            transaction.rollback();
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        return objects;
    }

    <T> void createObject(T object) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e.getCause());
        }
    }

    <T> void updateObject(T object) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e.getCause());
        }
    }

    <T> void removeObject(T object) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();

            session.remove(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(e.getCause());
        }
    }
}
