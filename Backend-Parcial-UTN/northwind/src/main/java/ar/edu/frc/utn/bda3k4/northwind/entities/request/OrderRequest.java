package ar.edu.frc.utn.bda3k4.northwind.entities.request;

import ar.edu.frc.utn.bda3k4.northwind.entities.Order;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderRequest {
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    @NotBlank(message = "Employee Id is mandatory")
    private Integer employeeId;
    @NotBlank(message = "Order Date is mandatory")
    private String orderDate;
    @NotBlank(message = "Required Date is mandatory")
    private String requiredDate;
    private String shippedDate;
    private Integer shipVia;
    @NotBlank(message = "Freight is mandatory")
    private Double freight;
    @NotBlank(message = "Ship Name is mandatory")
    private String shipName;
    @NotBlank(message = "Ship Address is mandatory")
    private String shipAddress;
    @NotBlank(message = "Ship City is mandatory")
    private String shipCity;
    @NotBlank(message = "Ship Region is mandatory")
    private String shipRegion;
    @NotBlank(message = "Ship Postal Code is mandatory")
    private String shipPostalCode;
    @NotBlank(message = "Ship Country is mandatory")
    private String shipCountry;

    public Order toOrder() {
        LocalDateTimeAttributeConverter converter = new LocalDateTimeAttributeConverter();
        LocalDate oDate = converter.convertToEntityAttribute(this.orderDate);
        LocalDate rDate = converter.convertToEntityAttribute(this.requiredDate);
        LocalDate sDate = null;
        if (!this.shippedDate.isBlank()) {sDate = converter.convertToEntityAttribute(this.shippedDate);}
        return new Order(
                null,
                oDate,
                rDate,
                sDate,
                freight,
                shipName,
                shipAddress,
                shipCity,
                shipRegion,
                shipPostalCode,
                shipCountry
        );
    }
}
