package ua.com.vovacoffee.service;

import ua.com.vovacoffee.model.SalePosition;
import ua.com.vovacoffee.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart();

    void add(SalePosition salePosition);

    List getSalePositions();

    void remove(SalePosition salePosition);

    void clear();

    double getPrice();

    int getSize();
}
