package ar.edu.frc.utn.bda3k4.northwind.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Suppliers")
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @Column(name = "SupplierID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Suppliers")
    @TableGenerator(name = "Suppliers", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Suppliers",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "ContactName")
    private String contactName;

    @Column(name = "ContactTitle")
    private String contactTitle;

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

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Fax")
    private String fax;

    @Column(name = "HomePage")
    private String homePage;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    public Supplier(Integer id, String companyName, String contactName, String contactTitle, String address, String city, String region, String postalCode, String country, String phone, String fax, String homePage) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
        this.homePage = homePage;

    }

    public void update(Supplier supplier){
        this.companyName = supplier.companyName;
        this.contactName = supplier.contactName;
        this.contactTitle = supplier.contactTitle;
        this.address = supplier.address;
        this.city = supplier.city;
        this.region = supplier.region;
        this.postalCode = supplier.postalCode;
        this.country = supplier.country;
        this.phone = supplier.phone;
        this.fax = supplier.fax;
        this.homePage = supplier.homePage;
    }
}