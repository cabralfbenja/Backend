package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.OrderDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailResponse {
    private Integer orderId;
    private Integer productId;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;

    public static OrderDetailResponse from(OrderDetail aOrderDetail) {
        return OrderDetailResponse.builder()
                .orderId(aOrderDetail.getOrderId())
                .productId(aOrderDetail.getProductId())
                .unitPrice(aOrderDetail.getUnitPrice())
                .quantity(aOrderDetail.getQuantity())
                .discount(aOrderDetail.getDiscount())
                .build();
    }
}
