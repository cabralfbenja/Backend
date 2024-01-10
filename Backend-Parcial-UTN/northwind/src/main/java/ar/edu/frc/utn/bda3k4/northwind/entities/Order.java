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
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Orders")
    @TableGenerator(name = "Orders", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Orders",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "OrderDate")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDate orderDate;

    @Column(name = "RequiredDate")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDate requiredDate;

    @Column(name = "ShippedDate")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDate shippedDate;

    @Column(name = "Freight")
    private Double freight;

    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "ShipAddress")
    private String shipAddress;

    @Column(name = "ShipCity")
    private String shipCity;

    @Column(name = "ShipRegion")
    private String shipRegion;

    @Column(name = "ShipPostalCode")
    private String shipPostalCode;

    @Column(name = "ShipCountry")
    private String shipCountry;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "ShipVia")
    private Shipper shipper;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    public void update(Order order) {
        /*NO SE ACTUALIZA CUSTOMER NI EMPLOYEE*/
        this.orderDate = order.orderDate;
        this.requiredDate = order.requiredDate;
        this.shippedDate = order.shippedDate; // SHIPPEDDATE PODRÍA LLEGAR A CAMBIAR O A SER NULL SI SE CANCELA EL ENVÍO
        this.shipper = order.shipper; // SHIPPER PODRÍA LLEGAR A CAMBIAR O A SER NULL SI SE CANCELA EL ENVÍO
        this.freight = order.freight;
        this.shipName = order.shipName;
        this.shipAddress = order.shipAddress;
        this.shipCity = order.shipCity;
        this.shipRegion = order.shipRegion;
        this.shipPostalCode = order.shipPostalCode;
        this.shipCountry = order.shipCountry;
    }

    public Order(Integer id, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, Double freight,
                 String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode,
                 String shipCountry) {
        this.id = id;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }
}
