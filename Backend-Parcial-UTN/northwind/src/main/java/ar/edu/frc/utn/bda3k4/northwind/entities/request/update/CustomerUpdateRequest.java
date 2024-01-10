package ar.edu.frc.utn.bda3k4.northwind.entities.request.update;

import ar.edu.frc.utn.bda3k4.northwind.entities.Customer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
//@FieldDefaults(level = AccessLevel.PRIVATE) no sé si hace falta
public class CustomerUpdateRequest {
    @NotBlank(message = "Company Name is mandatory")
    private String companyName;
    @NotBlank(message = "Contact Name is mandatory")
    private String contactName;
    @NotBlank(message = "Contact Title is mandatory")
    private String contactTitle;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotBlank(message = "Region is mandatory")
    private String region;
    @NotBlank(message = "Postal Code is mandatory")
    private String postalCode;
    @NotBlank(message = "Country is mandatory")
    private String country;
    @NotBlank(message = "Phone is mandatory")
    private String phone;
    @NotBlank(message = "Fax is mandatory")
    private String fax;

    public Customer toCustomer() {
        return new Customer(
                null,
                companyName,
                contactName,
                contactTitle,
                address,
                city,
                region,
                postalCode,
                country,
                phone,
                fax
        );
    }
}


