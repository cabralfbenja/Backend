package ar.edu.frc.utn.bda3k4.northwind.repositories;

import ar.edu.frc.utn.bda3k4.northwind.entities.Customer;
import ar.edu.frc.utn.bda3k4.northwind.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.customer c " +
            "JOIN FETCH o.orderDetails od " +
            "WHERE c.id = ?1 AND od.product.id = ?2")
    List<Order> findOrdersWithProduct(String customerID, Integer productID);

}
