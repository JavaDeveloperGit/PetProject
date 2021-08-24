package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.Model;

import java.util.Collection;
import java.util.List;

public interface DataDAO <T extends Model> {

    void add(T model);

    void add(Collection<T> models);

    void update(T model);

    T get(Long id);

    List<T> getAll();

    void remove(T model);

    void remove(Long id);

    void remove(Collection<T> models);

    void removeAll();
}
