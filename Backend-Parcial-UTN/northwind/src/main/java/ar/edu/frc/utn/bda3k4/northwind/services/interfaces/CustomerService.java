package ar.edu.frc.utn.bda3k4.northwind.services.interfaces;

import ar.edu.frc.utn.bda3k4.northwind.entities.Customer;
import ar.edu.frc.utn.bda3k4.northwind.entities.Order;

import java.util.List;

public interface CustomerService extends Service<Customer, String>{
    List<Order> findOrdersWithProduct(String customerID, Integer productID);
}
