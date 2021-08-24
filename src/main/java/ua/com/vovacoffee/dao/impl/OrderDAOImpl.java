package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.OrderDAO;
import ua.com.vovacoffee.model.Order;
import ua.com.vovacoffee.repository.OrderRepository;

@Repository
public class OrderDAOImpl extends DataDAOImpl<Order> implements OrderDAO {

    private OrderRepository repository;

    @Autowired
    public OrderDAOImpl(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Order get(String number) {
        return repository.findByNumber(number);
    }

    @Override
    public void remove(String number) {
        repository.deleteByNumber(number);
    }
}
