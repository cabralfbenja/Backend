package ar.edu.frc.utn.bda3k4.northwind.entities.request.update;

import ar.edu.frc.utn.bda3k4.northwind.entities.OrderDetail;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailUpdateRequest {
    @NotBlank(message = "unitPrice is mandatory")
    private Double unitPrice;
    @NotBlank(message = "quantity is mandatory")
    private Integer quantity;
    @NotBlank(message = "discount is mandatory")
    private Double discount;

    public OrderDetail toOrderDetail() {
        return new OrderDetail(
                null,
                null,
                unitPrice,
                quantity,
                discount
        );
    }
}
