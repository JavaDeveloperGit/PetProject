package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.vovacoffee.dao.OrderDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Order;
import ua.com.vovacoffee.service.OrderService;

@Service
public class OrderServiceImpl extends MainServiceImpl<Order> implements OrderService {

    private OrderDAO dao;

    @Autowired
    public OrderServiceImpl(OrderDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public Order get(String number) throws WrongInformationException, BadRequestException {
        if (number == null || number.isEmpty()) {
            throw new WrongInformationException("No order number!");
        }

        Order order;

        order = dao.get(number);
        if (order == null) {
            throw new BadRequestException("Can't find order by number " + number + "!");
        }

        return order;
    }

    @Override
    @Transactional
    public void remove(String number) throws WrongInformationException {
        if (number == null || number.isEmpty()) {
            throw new WrongInformationException("No order number!");
        }
        dao.remove(number);
    }
}
