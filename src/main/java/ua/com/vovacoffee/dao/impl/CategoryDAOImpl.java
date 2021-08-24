package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.CategoryDAO;
import ua.com.vovacoffee.model.Category;
import ua.com.vovacoffee.repository.CategoryRepository;

@Repository
public class CategoryDAOImpl extends DataDAOImpl<Category> implements CategoryDAO {

    private CategoryRepository repository;

    @Autowired
    public CategoryDAOImpl(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Category get(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public void remove(String url) {
        repository.deleteByUrl(url);
    }
}
