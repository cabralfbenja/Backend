package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Employee;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeResponse {
    private Integer employeeId;
    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private String birthDate;
    private String hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String extension;
    private byte[] photo;
    private String notes;
    private Integer reportsTo;
    private String photoPath;

    public static EmployeeResponse from(Employee anEmployee) {
        LocalDateTimeAttributeConverter converter = new LocalDateTimeAttributeConverter();
        String bDate = converter.convertToDatabaseColumn(anEmployee.getBirthDate());
        String hDate = converter.convertToDatabaseColumn(anEmployee.getHireDate());
        Integer reportsTo;
        try {reportsTo = anEmployee.getReportsTo().getId();}
        catch (Exception e) {reportsTo = null;}
        return EmployeeResponse.builder()
                .employeeId(anEmployee.getId())
                .lastName(anEmployee.getLastName())
                .firstName(anEmployee.getFirstName())
                .title(anEmployee.getTitle())
                .titleOfCourtesy(anEmployee.getTitleOfCourtesy())
                .birthDate(bDate)
                .hireDate(hDate)
                .address(anEmployee.getAddress())
                .city(anEmployee.getCity())
                .region(anEmployee.getRegion())
                .postalCode(anEmployee.getPostalCode())
                .country(anEmployee.getCountry())
                .homePhone(anEmployee.getHomePhone())
                .extension(anEmployee.getExtension())
                .photo(anEmployee.getPhoto())
                .notes(anEmployee.getNotes())
                .reportsTo(reportsTo)
                .photoPath(anEmployee.getPhotoPath())
                .build();
    }
}
