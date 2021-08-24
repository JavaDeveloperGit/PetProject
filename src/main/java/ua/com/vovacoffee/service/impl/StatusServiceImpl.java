package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.StatusDAO;
import ua.com.vovacoffee.enums.StatusEnum;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.DuplicateException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Status;
import ua.com.vovacoffee.service.StatusService;

@Service
public class StatusServiceImpl extends MainServiceImpl<Status> implements StatusService {

    private StatusDAO dao;

    @Autowired
    public StatusServiceImpl(StatusDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(StatusEnum title, String description) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        if (dao.get(title) != null) {
            throw new DuplicateException("Duplicate status with title  " + title + "!");
        }
        dao.add(new Status(title, description));
    }

    @Override
    @Transactional(readOnly = true)
    public Status get(StatusEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }

        Status status = dao.get(title);
        if (status == null) {
            throw new BadRequestException("Can't find status by title " + title + "!");
        }

        return status;
    }

    @Override
    @Transactional(readOnly = true)
    public Status getDefault() throws BadRequestException {
        Status status = dao.getDefault();
        if (status == null) {
            throw new BadRequestException("Can't find default status!");
        }
        return status;
    }

    @Override
    @Transactional
    public void remove(StatusEnum title) throws WrongInformationException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        dao.remove(title);
    }
}
