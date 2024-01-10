package ar.edu.frc.utn.bda3k4.northwind.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "CustomerID")
    private String id;

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

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    public Customer(String id, String companyName, String contactName, String contactTitle, String address,
                    String city, String region, String postalCode, String country, String phone, String fax) {
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
    }

    public void update(Customer customer){
        this.companyName = customer.getCompanyName();
        this.contactName = customer.getContactName();
        this.contactTitle = customer.getContactTitle();
        this.address = customer.getAddress();
        this.city = customer.getCity();
        this.region = customer.getRegion();
        this.postalCode = customer.getPostalCode();
        this.country = customer.getCountry();
        this.phone = customer.getPhone();
        this.fax = customer.getFax();
    }
}
