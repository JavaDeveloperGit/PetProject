package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.Category;

public interface CategoryDAO extends DataDAO<Category>  {

    Category get(String url);

    void remove(String url);
}
