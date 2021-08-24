package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.Photo;

public interface PhotoDAO extends DataDAO<Photo> {

    Photo get(String title);

    void remove(String title);
}
