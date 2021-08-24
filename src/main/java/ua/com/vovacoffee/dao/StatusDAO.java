package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.enums.StatusEnum;
import ua.com.vovacoffee.model.Status;

public interface StatusDAO extends DataDAO<Status> {

    void add(StatusEnum title, String description);

    Status get(StatusEnum title);

    Status getDefault();

    void remove(StatusEnum title);
}
