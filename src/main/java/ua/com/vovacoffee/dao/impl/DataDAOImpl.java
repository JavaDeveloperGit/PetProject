package ua.com.vovacoffee.dao.impl;

import ua.com.vovacoffee.dao.DataDAO;
import ua.com.vovacoffee.model.Model;
import ua.com.vovacoffee.repository.MainRepository;

import java.util.Collection;
import java.util.List;

public abstract class DataDAOImpl <T extends Model> implements DataDAO<T> {

    private MainRepository<T, Long> repository;

    public DataDAOImpl(MainRepository<T, Long> repository) {
        super();
        this.repository = repository;
    }

    @Override
    public void add(T model) {
        repository.save(model);
    }

    @Override
    public void add(Collection<T> models) {
        repository.save(models);
    }

    @Override
    public void update(T model) {
        repository.save(model);
    }

    @Override
    public T get(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void remove(T model) {
        repository.delete(model);
    }

    @Override
    public void remove(Long id) {
        repository.delete(id);
    }

    @Override
    public void remove(Collection<T> models) {
        repository.delete(models);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
