package ar.edu.frc.utn.bda3k4.northwind.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Products")
    @TableGenerator(name = "Products", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Products",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "ProductName")
    private String name;

    @Column(name = "QuantityPerUnit")
    private String quantityPerUnit;

    @Column(name = "UnitPrice")
    private Double unitPrice;

    @Column(name = "UnitsInStock")
    private Integer unitsInStock;

    @Column(name = "UnitsOnOrder")
    private Integer unitsOnOrder;

    @Column(name = "ReorderLevel")
    private Integer reorderLevel;

    @Column(name = "Discontinued")
    private Boolean discontinued;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CategoryID")
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    public Product(Integer id, String name, String quantityPerUnit, Double unitPrice, Integer unitsInStock, Integer unitsOnOrder, Integer reorderLevel, Boolean discontinued) {
        this.id = id;
        this.name = name;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    public void update(Product product) {
        this.name = product.getName();
        this.supplier = product.getSupplier();
        this.quantityPerUnit = product.getQuantityPerUnit();
        this.unitPrice = product.getUnitPrice();
        this.unitsInStock = product.getUnitsInStock();
        this.unitsOnOrder = product.getUnitsOnOrder();
        this.reorderLevel = product.getReorderLevel();
        this.discontinued = product.getDiscontinued();
    }
}


