package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.SalePosition;
import ua.com.vovacoffee.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {

    List<SalePosition> getSalePositions();

    void addSalePosition(SalePosition salePosition);

    void removeSalePosition(SalePosition salePosition);

    void clearSalePositions();

    ShoppingCart get();

    int getSize();

    double getPrice();
}
