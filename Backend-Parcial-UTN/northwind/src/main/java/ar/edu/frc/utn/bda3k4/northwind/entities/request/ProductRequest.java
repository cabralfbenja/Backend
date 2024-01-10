package ar.edu.frc.utn.bda3k4.northwind.entities.request;

import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Product Name is mandatory")
    private String name;
    @NotBlank(message = "Quantity Per Unit is mandatory")
    private String quantityPerUnit;
    @NotBlank(message = "Unit Price is mandatory")
    private Double unitPrice;
    @NotBlank(message = "Units In Stock is mandatory")
    private Integer unitsInStock;
    @NotBlank(message = "Units On Order is mandatory")
    private Integer unitsOnOrder;
    @NotBlank(message = "Reorder Level is mandatory")
    private Integer reorderLevel;
    @NotBlank(message = "Discontinued is mandatory")
    private Boolean discontinued;
    private Integer supplierId;
    @NotBlank(message = "Category is mandatory")
    private Integer categoryId;

    public Product toProduct() {
        return new Product(
                null,
                name,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued
        );
    }
}
