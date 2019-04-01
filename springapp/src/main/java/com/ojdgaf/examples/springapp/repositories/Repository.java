package com.ojdgaf.examples.springapp.repositories;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Serializable> {
    public List<T> all();

    public T get(Serializable id);

    public void saveOrUpdate(T t);

    public void delete(T t);
}
