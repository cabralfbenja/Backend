package ar.edu.frc.utn.bda3k4.northwind.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Shippers")
@NoArgsConstructor
@AllArgsConstructor
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Shippers")
    @TableGenerator(name = "Shippers", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Shippers",
            initialValue=1, allocationSize=1)
    @Column(name = "ShipperID")
    private Integer id;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "Phone")
    private String phone;

    @OneToMany(mappedBy = "shipper", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    public void update(Shipper shipper) {
        this.companyName = shipper.getCompanyName();
        this.phone = shipper.getPhone();
    }

    public Shipper(String companyName, String phone) {
        this.companyName = companyName;
        this.phone = phone;
    }
}
