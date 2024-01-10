package ar.edu.frc.utn.bda3k4.northwind.entities;

import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Employees")
    @TableGenerator(name = "Employees", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Employees",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Title")
    private String title;

    @Column(name = "TitleOfCourtesy")
    private String titleOfCourtesy;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "HireDate")
    private LocalDate hireDate;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "Region")
    private String region;

    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Country")
    private String country;

    @Column(name = "HomePhone")
    private String homePhone;

    @Column(name = "Extension")
    private String extension;

    @Column(name = "Photo")
    private byte[] photo;

    @Column(name = "Notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "ReportsTo")
    private Employee reportsTo;

    @Column(name = "PhotoPath")
    private String photoPath;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    public void update(Employee employee){
        lastName = employee.getLastName();
        firstName = employee.getFirstName();
        title = employee.getTitle();
        titleOfCourtesy = employee.getTitleOfCourtesy();
        birthDate = employee.getBirthDate();
        hireDate = employee.getHireDate();
        address = employee.getAddress();
        city = employee.getCity();
        region = employee.getRegion();
        postalCode = employee.getPostalCode();
        country = employee.getCountry();
        homePhone = employee.getHomePhone();
        extension = employee.getExtension();
        notes = employee.getNotes();
        reportsTo = employee.getReportsTo();
        photoPath = employee.getPhotoPath();
        if(employee.getPhoto() != null){photo = employee.getPhoto();}
    }

    public Employee(String lastName, String firstName, String title, String titleOfCourtesy,
                    LocalDate birthDate, LocalDate hireDate, String address, String city,
                    String region, String postalCode, String country, String homePhone,
                    String extension, byte[] photo, String notes, String photoPath) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.photo = photo;
        this.notes = notes;
        this.photoPath = photoPath;
    }
}
