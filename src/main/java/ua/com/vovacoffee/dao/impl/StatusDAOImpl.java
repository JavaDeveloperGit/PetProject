package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.StatusDAO;
import ua.com.vovacoffee.enums.StatusEnum;
import ua.com.vovacoffee.model.Status;
import ua.com.vovacoffee.repository.StatusRepository;

@Repository
public class StatusDAOImpl extends DataDAOImpl<Status> implements StatusDAO {

    private StatusRepository repository;

    @Autowired
    public StatusDAOImpl(StatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void add(StatusEnum title, String description) {
        repository.save(new Status(title, description));
    }

    @Override
    public Status get(StatusEnum title) {
        return repository.findByTitle(title);
    }

    @Override
    public Status getDefault() {
        return repository.findOne((long) 1);
    }

    @Override
    public void remove(StatusEnum title) {
        repository.deleteByTitle(title);
    }
}
