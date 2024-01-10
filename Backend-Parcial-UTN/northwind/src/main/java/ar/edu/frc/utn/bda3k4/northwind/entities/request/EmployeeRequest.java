package ar.edu.frc.utn.bda3k4.northwind.entities.request;

import ar.edu.frc.utn.bda3k4.northwind.entities.Employee;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "titleOfCourtesy is mandatory")
    private String titleOfCourtesy;
    @NotBlank(message = "birthDate is mandatory")
    private String birthDate;
    @NotBlank(message = "hireDate is mandatory")
    private String hireDate;
    @NotBlank(message = "address is mandatory")
    private String address;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "region is mandatory")
    private String region;
    @NotBlank(message = "postalCode is mandatory")
    private String postalCode;
    @NotBlank(message = "country is mandatory")
    private String country;
    @NotBlank(message = "homePhone is mandatory")
    private String homePhone;
    @NotBlank(message = "extension is mandatory")
    private String extension;
    private byte[] photo;
    private String notes;
    private Integer reportsTo;
    private String photoPath;

    public Employee toEmployee() {
        LocalDateTimeAttributeConverter converter = new LocalDateTimeAttributeConverter();
        LocalDate bDate = converter.convertToEntityAttribute(this.birthDate);
        LocalDate hDate = converter.convertToEntityAttribute(this.hireDate);
        return new Employee(
                lastName,
                firstName,
                title,
                titleOfCourtesy,
                bDate,
                hDate,
                address,
                city,
                region,
                postalCode,
                country,
                homePhone,
                extension,
                photo,
                notes,
                photoPath
        );
    }
}
