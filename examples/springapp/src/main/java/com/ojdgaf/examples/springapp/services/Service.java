package com.ojdgaf.examples.springapp.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T extends Serializable> {
    public List<T> all();

    public T get(Serializable id);

    public void saveOrUpdate(T t);

    public void delete(T t);
}
