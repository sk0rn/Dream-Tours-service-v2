package application.service.user;

import application.domain.Order;
import application.domain.User;
import application.repository.OrderRepository;
import application.repository.UserRepository;
import application.service.user.iface.OrderService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean add(Order order) {
        return ServiceHelper.save(orderRepository, order);
    }

    @Override
    public Order getById(Long id) {
        return ServiceHelper.getById(orderRepository, id);
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        User user;
        return (user = ServiceHelper.getById(userRepository, userId)) != null ?
                new ArrayList<>(user.getOrders()) :
                Collections.emptyList();
    }

    @Override
    public boolean update(Order order) {
        return ServiceHelper.save(orderRepository, order);
    }
}
