package ar.edu.frc.utn.bda3k4.northwind.services.implementations;

import ar.edu.frc.utn.bda3k4.northwind.entities.Order;
import ar.edu.frc.utn.bda3k4.northwind.repositories.OrderRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(Order entity) {
        return this.orderRepository.save(entity);
    }

    @Override
    public Order update(Integer id, Order entity) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Order not found"));
        order.update(entity);
        return this.orderRepository.save(order);
    }

    @Override
    public Order delete(Integer id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Order not found"));
        this.orderRepository.delete(order);
        return order;
    }

    @Override
    public Order findById(Integer id) {
        return this.orderRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = this.orderRepository.findAll();
        if(orders.isEmpty()){throw new IllegalArgumentException("No orders found");}
        return orders;
    }
}
