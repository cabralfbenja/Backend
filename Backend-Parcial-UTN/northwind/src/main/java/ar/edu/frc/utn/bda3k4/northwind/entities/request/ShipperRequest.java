package ar.edu.frc.utn.bda3k4.northwind.entities.request;

import ar.edu.frc.utn.bda3k4.northwind.entities.Shipper;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShipperRequest {
    @NotBlank(message = "companyName is mandatory")
    private String companyName;
    @NotBlank(message = "phone is mandatory")
    private String phone;

    public Shipper toShipper() {
        return new Shipper(
                companyName,
                phone
        );
    }
}
