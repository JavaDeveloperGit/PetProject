package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.UserDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.model.User;
import ua.com.vovacoffee.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends MainServiceImpl<User> implements UserService, UserDetailsService {

    private UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) throws WrongInformationException, BadRequestException {
        if (name == null || name.isEmpty()) {
            throw new WrongInformationException("No user name!");
        }

        User user = dao.getByName(name);
        if (user == null) {
            throw new BadRequestException("Can't find user by name " + name + "!");
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) throws WrongInformationException, BadRequestException {
        if (username == null || username.isEmpty()) {
            throw new WrongInformationException("No username!");
        }

        User user = dao.getByUsername(username);
        if (user == null) {
            throw new BadRequestException("Can't find user by username " + username + "!");
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws BadRequestException {
        User user = dao.getMainAdministrator();
        if (user == null) {
            throw new BadRequestException("Can't find administrator!");
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAdministrators() {
        return dao.getAdministrators();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getManagers() {
        return dao.getManagers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getClients() {
        return dao.getClients();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getPersonnel() {
        List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() throws BadRequestException {
        User user = dao.getAuthenticatedUser();
        if (user == null) {
            throw new BadRequestException("Can't find authenticated user!");
        }
        return user;
    }

    @Override
    @Transactional
    public void removeByName(String name) throws WrongInformationException {
        if (name == null || name.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        dao.remove(name);
    }

    @Override
    @Transactional
    public void removeByRole(Role role) throws WrongInformationException {
        if (role == null) {
            throw new WrongInformationException("No user role!");
        }
        dao.remove(role);
    }

    @Override
    @Transactional
    public void removePersonnel() {
        List<User> personnel = getPersonnel();
        if (personnel.isEmpty()) {
            return;
        }
        personnel.remove(getMainAdministrator());
        dao.remove(personnel);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
