package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.UserDAO;
import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.model.User;
import ua.com.vovacoffee.repository.RoleRepository;
import ua.com.vovacoffee.repository.UserRepository;

import java.util.List;

@Repository
public class UserDAOImpl extends DataDAOImpl<User> implements UserDAO {

    private static Long CLIENT_ROLE_ID = 1L;

    private static Long ADMIN_ROLE_ID = 2L;

    private static Long MANAGER_ROLE_ID = 3L;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getMainAdministrator() {
        Role admin = roleRepository.findOne(ADMIN_ROLE_ID);
        return userRepository.findAllByRole(admin).get(0);
    }

    @Override
    public List<User> getAdministrators() {
        Role admin = roleRepository.findOne(ADMIN_ROLE_ID);
        return userRepository.findAllByRole(admin);
    }

    @Override
    public List<User> getManagers() {
        Role manager = roleRepository.findOne(MANAGER_ROLE_ID);
        return userRepository.findAllByRole(manager);
    }

    @Override
    public List<User> getClients() {
        Role client = roleRepository.findOne(CLIENT_ROLE_ID);
        return userRepository.findAllByRole(client);
    }

    @Override
    public User getAuthenticatedUser() {
        User user;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (Exception ex) {
            //ex.printStackTrace();
            user = new User();
        }
        return user;
    }

    @Override
    public void remove(String name) {
        userRepository.deleteByName(name);
    }

    @Override
    public void remove(Role role) {
        userRepository.deleteAllByRole(role);
    }
}
