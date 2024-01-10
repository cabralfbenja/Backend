package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private Integer supplierId;
    private Integer categoryId;
    private String quantityPerUnit;
    private Double unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer reorderLevel;
    private Boolean discontinued;

    public static ProductResponse from(Product aProduct) {
        Integer supplierId = null;
        if(aProduct.getSupplier() != null) supplierId = aProduct.getSupplier().getId();
        return ProductResponse.builder()
                .id(aProduct.getId())
                .name(aProduct.getName())
                .supplierId(supplierId)
                .categoryId(aProduct.getCategory().getId())
                .quantityPerUnit(aProduct.getQuantityPerUnit())
                .unitPrice(aProduct.getUnitPrice())
                .unitsInStock(aProduct.getUnitsInStock())
                .unitsOnOrder(aProduct.getUnitsOnOrder())
                .reorderLevel(aProduct.getReorderLevel())
                .discontinued(aProduct.getDiscontinued())
                .build();
    }
}
