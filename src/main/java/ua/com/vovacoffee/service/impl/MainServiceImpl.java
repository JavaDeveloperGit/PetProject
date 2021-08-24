package ua.com.vovacoffee.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.DataDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Model;
import ua.com.vovacoffee.service.MainService;

import java.util.List;

public abstract class MainServiceImpl <T extends Model> implements MainService<T> {

    private DataDAO<T> dao;

    public MainServiceImpl(DataDAO<T> dao) {
        super();
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(T model) {
        if (model != null) {
            dao.add(model);
        }
    }

    @Override
    @Transactional
    public void add(List<T> models) {
        if (models != null && !models.isEmpty()) {
            dao.add(models);
        }
    }

    @Override
    @Transactional
    public void update(T model) {
        if (model != null) {
            dao.update(model);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public T get(Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }

        T model = dao.get(id);
        if (model == null) {
            throw new BadRequestException("Can't find model by id " + id + "!");
        }
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void remove(T model) {
        if (model != null) {
            dao.remove(model);
        }
    }

    @Override
    @Transactional
    public void remove(Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        dao.remove(id);
    }

    @Override
    @Transactional
    public void remove(List<T> models) {
        if (models != null && !models.isEmpty()) {
            dao.remove(models);
        }
    }

    @Override
    @Transactional
    public void removeAll() {
        dao.removeAll();
    }
}
