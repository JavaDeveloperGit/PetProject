package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.model.User;

import java.util.List;

public interface UserRepository extends MainRepository<User, Long> {

    User findByName(String name);

    User findByUsername(String username);

    User findByRole(Role role);

    List<User> findAllByRole(Role role);

    void deleteAllByRole(Role role);

    void deleteByName(String name);
}
