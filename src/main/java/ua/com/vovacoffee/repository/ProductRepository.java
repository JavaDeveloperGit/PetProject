package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.model.Product;

import java.util.List;

public interface ProductRepository extends MainRepository<Product, Long>  {

    Product findByUrl(String url);

    Product findByArticle(int article);

    void deleteByUrl(String url);

    void deleteByArticle(int article);

    List<Product> findByCategoryId(long id);
}
