package ar.edu.frc.utn.bda3k4.northwind.support;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OrderDetailPK implements Serializable {
    @Id
    @Column(name = "OrderId")
    private Integer orderId;
    @Id
    @Column(name = "ProductId")
    private Integer productId;

    public OrderDetailPK(Integer orderId, Integer productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailPK that = (OrderDetailPK) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
