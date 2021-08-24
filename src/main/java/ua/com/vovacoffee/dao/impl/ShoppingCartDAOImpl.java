package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.ShoppingCartDAO;
import ua.com.vovacoffee.model.SalePosition;
import ua.com.vovacoffee.model.ShoppingCart;

import java.util.List;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    private ShoppingCart shoppingCart;

    @Autowired
    public ShoppingCartDAOImpl(ShoppingCart shoppingCart) {
        super();
        this.shoppingCart = shoppingCart;
    }

    @Override
    public List<SalePosition> getSalePositions() {
        return shoppingCart.getSalePositions();
    }

    @Override
    public void addSalePosition(SalePosition salePosition) {
        shoppingCart.addSalePosition(salePosition);
    }

    @Override
    public void removeSalePosition(SalePosition salePosition) {
        shoppingCart.removeSalePosition(salePosition);
    }

    @Override
    public void clearSalePositions() {
        shoppingCart.clearSalePositions();
    }

    @Override
    public ShoppingCart get() {
        return shoppingCart;
    }

    @Override
    public int getSize() {
        return shoppingCart.getSize();
    }

    @Override
    public double getPrice() {
        return shoppingCart.getPrice();
    }
}
