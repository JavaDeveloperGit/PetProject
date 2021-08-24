package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.RoleDAO;
import ua.com.vovacoffee.enums.RoleEnum;
import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.repository.RoleRepository;

@Repository
public class RoleDAOImpl extends DataDAOImpl<Role> implements RoleDAO {

    private RoleRepository repository;

    @Autowired
    public RoleDAOImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void add(RoleEnum title, String description) {
        repository.save(new Role(title, description));
    }

    @Override
    public Role get(RoleEnum title) {
        return repository.findByTitle(title);
    }

    @Override
    public Role getDefault() {
        return repository.findOne((long) 1);
    }

    @Override
    public void remove(RoleEnum title) {
        repository.deleteByTitle(title);
    }
}
