package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.model.Photo;

public interface PhotoRepository extends MainRepository<Photo, Long>  {

    Photo findByTitle(String title);

    void deleteByTitle(String title);
}
