package com.nixsolutions.service.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class HibernateDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    Object findObject(String hql, String searchValue) throws Exception {
        Object obj;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("search_factor", searchValue);
            if (query.list().isEmpty()) {
                return null;
            }
            obj = query.getSingleResult();
            transaction.rollback();
        } catch (Exception e) {
            logger.error("Exception in findObject()", e, e);
            throw e;
        }
        return obj;
    }

    List findList(String hql) throws Exception {
        List objects;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            System.out.println("session     " + session);
            Transaction transaction = session.beginTransaction();
            System.out.println("transact    " + transaction);
            Query query = session.createQuery(hql);
            System.out.println("query   " + query);
            if (query.list().isEmpty()) {
                return null;
            }
            objects = query.list();
            transaction.rollback();
        } catch (Exception e) {
            logger.error("Exception in findList()", e, e);
            throw e;
        }
        return objects;
    }

    void createObject(Object object) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Exception in createObject()", e, e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    void updateObject(Object object) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Exception in updateObject()", e, e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    void removeObject(Object object) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            session.remove(object);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Exception in removeObject()", e, e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
