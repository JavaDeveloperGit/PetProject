package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.CategoryDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Category;
import ua.com.vovacoffee.service.CategoryService;

@Service
public class CategoryServiceImpl extends MainServiceImpl<Category> implements CategoryService {

    private CategoryDAO dao;

    @Autowired
    public CategoryServiceImpl(CategoryDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public Category get(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }

        Category category = dao.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }

        return category;
    }

    @Override
    @Transactional
    public void remove(String url) throws WrongInformationException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        dao.remove(url);
    }
}
