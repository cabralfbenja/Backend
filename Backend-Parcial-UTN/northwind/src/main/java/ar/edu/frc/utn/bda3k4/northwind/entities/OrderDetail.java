package ar.edu.frc.utn.bda3k4.northwind.entities;

import ar.edu.frc.utn.bda3k4.northwind.support.OrderDetailPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Order Details")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    @Id
    @Column(name = "OrderId")
    private Integer orderId;

    @Id
    @Column(name = "ProductId")
    private Integer productId;

    @Column(name = "UnitPrice")
    private Double unitPrice;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Discount")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;

    public OrderDetail(Integer orderId, Integer productId, Double unitPrice, Integer quantity, Double discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public void update(OrderDetail orderDetail) {
        this.unitPrice = orderDetail.unitPrice;
        this.quantity = orderDetail.quantity;
        this.discount = orderDetail.discount;
    }
}
