package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.impl.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

class HibernateDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    Object findObject(String hql, String searchValue) throws Exception {
        Object obj;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
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
        List objects = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
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
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            System.out.println(object.toString());
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
        System.out.println(object.toString() + "!!!!!!!!!!");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
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
