package ua.com.vovacoffee.service;

import ua.com.vovacoffee.enums.StatusEnum;
import ua.com.vovacoffee.model.Status;

public interface StatusService extends MainService<Status> {

    void add(StatusEnum title, String description);

    Status get(StatusEnum title);

    Status getDefault();

    void remove(StatusEnum title);
}
