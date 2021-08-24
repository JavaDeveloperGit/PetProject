package ua.com.vovacoffee.service;

import ua.com.vovacoffee.enums.RoleEnum;
import ua.com.vovacoffee.model.Role;

import java.util.List;

public interface RoleService extends MainService<Role> {

    void add(RoleEnum title, String description);

    Role get(RoleEnum title);

    Role getAdministrator();

    Role getManager();

    Role getDefault();

    List<Role> getPersonnel();

    void remove(RoleEnum title);
}
