package com.ojdgaf.examples.hibernateapp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import com.ojdgaf.examples.hibernateapp.logging.*;
import com.ojdgaf.examples.hibernateapp.entities.*;

public class App {
    public static void main(String[] args) {
        Logger logger = new JsonLogger(new FileLogger("log.json"));

        try (DAO dao = new DAO(createSessionFactory())) {
            logger.log(dao.all(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory() throws HibernateException {
        return new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(RecordBook.class)
                .addAnnotatedClass(Phone.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
    }
}