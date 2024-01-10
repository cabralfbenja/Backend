package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Shipper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipperResponse {
    private Integer id;
    private String companyName;
    private String phone;

    public static ShipperResponse from(Shipper aShipper){
            return ShipperResponse.builder()
                .id(aShipper.getId())
                .companyName(aShipper.getCompanyName())
                .phone(aShipper.getPhone())
                .build();
    }
}
