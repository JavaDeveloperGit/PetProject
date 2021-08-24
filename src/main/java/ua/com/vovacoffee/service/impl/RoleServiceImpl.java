package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.RoleDAO;
import ua.com.vovacoffee.enums.RoleEnum;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.DuplicateException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Role;
import ua.com.vovacoffee.service.RoleService;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl extends MainServiceImpl<Role> implements RoleService {

    private RoleDAO dao;

    @Autowired
    public RoleServiceImpl(RoleDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(RoleEnum title, String description) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }

        if (dao.get(title) != null) {
            throw new DuplicateException("Duplicate role with title  " + title + "!");
        }
        dao.add(new Role(title, description));
    }

    @Override
    @Transactional(readOnly = true)
    public Role get(RoleEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }

        Role role = dao.get(title);
        if (role == null) {
            throw new BadRequestException("Can't find role by title " + title + "!");
        }

        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getAdministrator() throws BadRequestException {
        Role role = dao.get(RoleEnum.ADMIN);
        if (role == null) {
            throw new BadRequestException("Can't find role \"administrator\"!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getManager() throws BadRequestException {
        Role role = dao.get(RoleEnum.MANAGER);
        if (role == null) {
            throw new BadRequestException("Can't find role \"manager\"!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getDefault() throws BadRequestException {
        Role role = dao.getDefault();
        if (role == null) {
            throw new BadRequestException("Can't find default role!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getPersonnel() {
        List<Role> roles = dao.getAll();
        if (roles.isEmpty()) {
            return Collections.emptyList();
        }

        roles.remove(getDefault());
        return roles;
    }

    @Override
    @Transactional
    public void remove(RoleEnum title) throws WrongInformationException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }
        dao.remove(title);
    }
}
