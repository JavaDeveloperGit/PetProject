package ua.com.vovacoffee.service;

import ua.com.vovacoffee.model.Category;

public interface CategoryService extends MainService<Category> {

    Category get(String url);

    void remove(String url);
}
