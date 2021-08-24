package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.PhotoDAO;
import ua.com.vovacoffee.model.Photo;
import ua.com.vovacoffee.repository.PhotoRepository;

@Repository
public class PhotoDAOImpl extends DataDAOImpl<Photo> implements PhotoDAO {

    private PhotoRepository repository;

    @Autowired
    public PhotoDAOImpl(PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Photo get(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public void remove(String title) {
        repository.deleteByTitle(title);
    }
}
