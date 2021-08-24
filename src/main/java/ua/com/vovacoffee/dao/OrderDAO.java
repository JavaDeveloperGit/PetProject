package ua.com.vovacoffee.dao;

import ua.com.vovacoffee.model.Order;

public interface OrderDAO extends DataDAO<Order> {

    Order get(String number);

    void remove(String number);
}
