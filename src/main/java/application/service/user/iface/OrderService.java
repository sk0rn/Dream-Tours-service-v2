package application.service.user.iface;

import application.domain.Order;

import java.util.List;

public interface OrderService {
    boolean add(Order order);

    Order getById(Long id);

    List<Order> getAllByUserId(Long id);

    boolean update(Order order);
}
