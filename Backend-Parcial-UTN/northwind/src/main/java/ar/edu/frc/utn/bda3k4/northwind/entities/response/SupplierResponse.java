package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Supplier;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierResponse {
    private Integer id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    private String homePage;

    public static SupplierResponse from(Supplier aSupplier){
        return SupplierResponse.builder()
                .id(aSupplier.getId())
                .companyName(aSupplier.getCompanyName())
                .contactName(aSupplier.getContactName())
                .contactTitle(aSupplier.getContactTitle())
                .address(aSupplier.getAddress())
                .city(aSupplier.getCity())
                .region(aSupplier.getRegion())
                .postalCode(aSupplier.getPostalCode())
                .country(aSupplier.getCountry())
                .phone(aSupplier.getPhone())
                .fax(aSupplier.getFax())
                .homePage(aSupplier.getHomePage())
                .build();
    }
}
