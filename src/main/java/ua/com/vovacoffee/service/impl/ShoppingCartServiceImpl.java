package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.ShoppingCartDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.model.SalePosition;
import ua.com.vovacoffee.model.ShoppingCart;
import ua.com.vovacoffee.service.ShoppingCartService;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartDAO shoppingCartDAO;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() throws BadRequestException {
        ShoppingCart shoppingCart = shoppingCartDAO.get();
        if (shoppingCart == null) {
            throw new BadRequestException("Can't find shopping cart!");
        }
        return shoppingCart;
    }

    @Override
    @Transactional
    public void add(SalePosition salePosition) {
        if (salePosition != null) {
            shoppingCartDAO.addSalePosition(salePosition);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalePosition> getSalePositions() {
        return shoppingCartDAO.getSalePositions();
    }

    @Override
    @Transactional
    public void remove(SalePosition salePosition) {
        if (salePosition != null) {
            shoppingCartDAO.removeSalePosition(salePosition);
        }
    }

    @Override
    @Transactional
    public void clear() {
        shoppingCartDAO.clearSalePositions();
    }

    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return shoppingCartDAO.getPrice();
    }

    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return shoppingCartDAO.getSize();
    }
}
