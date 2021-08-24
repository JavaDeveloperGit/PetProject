package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.ProductDAO;
import ua.com.vovacoffee.model.Product;
import ua.com.vovacoffee.repository.ProductRepository;

import java.util.List;

@Repository
public class ProductDAOImpl extends DataDAOImpl<Product> implements ProductDAO {

    private ProductRepository repository;

    @Autowired
    public ProductDAOImpl(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Product getByUrl(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public Product getByArticle(int article) {
        return repository.findByArticle(article);
    }

    @Override
    public void removeByUrl(String url) {
        repository.deleteByUrl(url);
    }

    @Override
    public void removeByArticle(int article) {
        repository.deleteByArticle(article);
    }

    @Override
    public void removeByCategoryId(long id) {
        List<Product> productList = repository.findByCategoryId(id);
        repository.delete(productList);
    }

    @Override
    public List<Product> getListByCategoryId(long id) {
        return repository.findByCategoryId(id);
    }
}
