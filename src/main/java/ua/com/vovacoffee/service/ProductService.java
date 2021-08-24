package ua.com.vovacoffee.service;

import ua.com.vovacoffee.model.Product;

import java.util.List;

public interface ProductService extends MainService<Product>  {

    Product getByUrl(String url);

    Product getByArticle(int article);

    List<Product> getByCategoryUrl(String url);

    List<Product> getByCategoryId(Long id);

    List<Product> getRandomByCategoryId(int size, Long categoryId, Long differentProductId);

    List<Product> getRandomByCategoryId(int size, Long id);

    List<Product> getRandom(int size);

    void removeByUrl(String url);

    void removeByArticle(int article);

    void removeByCategoryUrl(String url);

    void removeByCategoryId(Long id);
}
