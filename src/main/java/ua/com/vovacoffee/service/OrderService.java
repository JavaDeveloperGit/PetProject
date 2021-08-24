package ua.com.vovacoffee.service;

import ua.com.vovacoffee.model.Order;

public interface OrderService extends MainService<Order> {

    Order get(String number);

    void remove(String number);
}
