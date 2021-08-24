package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.model.Category;

public interface CategoryRepository extends MainRepository<Category, Long>  {

    Category findByUrl(String url);

    void deleteByUrl(String url);
}
