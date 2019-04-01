package com.ojdgaf.examples.springapp.repositories;

import java.io.Serializable;
import java.util.List;
import com.ojdgaf.examples.springapp.entities.Faculty;
import org.hibernate.SessionFactory;

public class FacultyRepository implements Repository<Faculty> {
    private SessionFactory sessionFactory;

    public FacultyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Faculty> all() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Faculty order by id", Faculty.class)
                .getResultList();
    }

    @Override
    public Faculty get(Serializable id) {
        return sessionFactory.getCurrentSession().get(Faculty.class, id);
    }

    @Override
    public void saveOrUpdate(Faculty faculty) {
        sessionFactory.getCurrentSession().saveOrUpdate(faculty);
    }

    @Override
    public void delete(Faculty faculty) {
        sessionFactory.getCurrentSession().delete(faculty);
    }
}
