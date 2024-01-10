package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    private String id;
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

    public static CustomerResponse from(Customer aCustomer) {
        return CustomerResponse.builder()
                .id(aCustomer.getId())
                .companyName(aCustomer.getCompanyName())
                .contactName(aCustomer.getContactName())
                .contactTitle(aCustomer.getContactTitle())
                .address(aCustomer.getAddress())
                .city(aCustomer.getCity())
                .region(aCustomer.getRegion())
                .postalCode(aCustomer.getPostalCode())
                .country(aCustomer.getCountry())
                .phone(aCustomer.getPhone())
                .fax(aCustomer.getFax())
                .build();
    }

}
