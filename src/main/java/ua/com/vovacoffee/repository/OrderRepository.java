package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.model.Order;

public interface OrderRepository extends MainRepository<Order, Long> {

    Order findByNumber(String number);

    void deleteByNumber(String number);
}
