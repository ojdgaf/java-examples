package com.ojdgaf.examples.hibernateapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.util.List;

public class DAO implements AutoCloseable {
    private SessionFactory factory;

    public DAO(SessionFactory factory) {
        this.factory = factory;
    }

    public <T> List<T> all(Class<T> clazz) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<T> result = session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        transaction.commit();
        return result;
    }

    public <T> T get(Class<T> clazz, Serializable id) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        T result = session.get(clazz, id);
        transaction.commit();
        return result;
    }

    public <T> void saveOrUpdate(T t) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
    }

    public <T> void delete(T t) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
    }

    @Override
    public void close() {
        factory.close();
    }
}
