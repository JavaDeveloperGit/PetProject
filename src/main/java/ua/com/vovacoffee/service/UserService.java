package ua.com.vovacoffee.service;

import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.model.User;

import java.util.List;

public interface UserService extends MainService<User> {

    User getByName(String name);

    User getByUsername(String username);

    User getMainAdministrator();

    List<User> getAdministrators();

    List<User> getManagers();

    List<User> getClients();

    List<User> getPersonnel();

    User getAuthenticatedUser();

    void removeByName(String name);

    void removeByRole(Role role);

    void removePersonnel();
}
