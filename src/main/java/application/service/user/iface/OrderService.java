package application.service.user.iface;

import application.domain.Order;

import java.util.List;

public interface OrderService {
    boolean add(Order order);

    Order getById(Integer id);

    List<Order> getAllByUserId(Integer id);

    boolean update(Order order);
}
