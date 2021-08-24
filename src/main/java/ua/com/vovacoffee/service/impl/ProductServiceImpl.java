package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.CategoryDAO;
import ua.com.vovacoffee.dao.ProductDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Category;
import ua.com.vovacoffee.model.Product;
import ua.com.vovacoffee.service.ProductService;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl extends MainServiceImpl<Product> implements ProductService {

    private ProductDAO productDAO;

    private CategoryDAO categoryDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        super(productDAO);
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByUrl(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No product URL!");
        }

        Product product = productDAO.getByUrl(url);
        if (product == null) {
            throw new BadRequestException("Can't find product by url " + url + "!");
        }

        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByArticle(int article) throws BadRequestException {
        Product product = productDAO.getByArticle(article);
        if (product == null) {
            throw new BadRequestException("Can't find product by article " + article + "!");
        }
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryUrl(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }

        Category category = categoryDAO.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }

        return productDAO.getListByCategoryId(category.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryId(Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No category id!");
        }
        return productDAO.getListByCategoryId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(int size, Long id) {
        return getRandomByCategoryId(size, id, -1L);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(int size, Long categoryId, Long differentProductId) throws WrongInformationException {
        if (categoryId == null || differentProductId == null) {
            throw new WrongInformationException("No category or product id!");
        }

        List<Product> products = productDAO.getListByCategoryId(categoryId);
        if (products.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        products.remove(productDAO.get(differentProductId));
        return getShuffleSubList(products, 0, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandom(int size) {
        List<Product> products = productDAO.getAll();
        if (products.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return getShuffleSubList(products, 0, size);
    }

    @Override
    @Transactional
    public void removeByUrl(String url) throws WrongInformationException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No product URL!");
        }
        productDAO.removeByUrl(url);
    }

    @Override
    @Transactional
    public void removeByArticle(int article) {
        productDAO.removeByArticle(article);
    }

    @Override
    @Transactional
    public void removeByCategoryUrl(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }

        Category category = categoryDAO.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }

        productDAO.removeByCategoryId(category.getId());
    }

    @Override
    @Transactional
    public void removeByCategoryId(Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }

        if (categoryDAO.get(id) == null) {
            throw new BadRequestException("Can't find category by id " + id + "!");
        }

        productDAO.removeByCategoryId(id);
    }

    private static List<Product> getShuffleSubList(List<Product> products, int start, int end) {
        if (products == null || products.isEmpty() || start > products.size() || start > end || start < 0 || end < 0) {
            return Collections.EMPTY_LIST;
        }

        if (end > products.size()) {
            end = products.size();
        }

        Collections.shuffle(products);

        return products.subList(start, end);
    }
}
