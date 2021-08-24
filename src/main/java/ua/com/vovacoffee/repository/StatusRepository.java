package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.enums.StatusEnum;
import ua.com.vovacoffee.model.Status;

public interface StatusRepository extends MainRepository<Status, Long> {

    Status findByTitle(StatusEnum title);

    void deleteByTitle(StatusEnum title);
}
