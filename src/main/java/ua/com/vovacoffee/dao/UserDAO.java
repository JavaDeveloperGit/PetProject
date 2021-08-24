package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.model.User;

import java.util.List;

public interface UserDAO extends DataDAO<User> {

    User getByName(String name);

    User getByUsername(String username);

    User getMainAdministrator();

    List<User> getAdministrators();

    List<User> getManagers();

    List<User> getClients();

    User getAuthenticatedUser();

    void remove(String name);

    void remove(Role role);
}
