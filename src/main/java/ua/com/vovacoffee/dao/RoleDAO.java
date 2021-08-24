package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.enums.RoleEnum;
import ua.com.vovacoffee.model.Role;

public interface RoleDAO extends DataDAO<Role> {

    void add(RoleEnum title, String description);

    Role get(RoleEnum title);

    Role getDefault();

    void remove(RoleEnum title);
}
