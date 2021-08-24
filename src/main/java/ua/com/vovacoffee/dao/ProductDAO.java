package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.Product;

import java.util.List;

public interface ProductDAO extends DataDAO<Product> {

    Product getByUrl(String url);

    Product getByArticle(int article);

    void removeByUrl(String url);

    void removeByArticle(int article);

    void removeByCategoryId(long id);

    List<Product> getListByCategoryId(long id);
}
