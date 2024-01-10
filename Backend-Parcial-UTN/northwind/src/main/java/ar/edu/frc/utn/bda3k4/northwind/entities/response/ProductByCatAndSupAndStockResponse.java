package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductByCatAndSupAndStockResponse {
    //ProductId, ProductName, StockFuturo (UnitsInStock + UnitsOnOrder)
    //y UnitPrice, ordenados de menor a mayor por StockFuturo.

    private Integer id;
    private String name;
    private Integer stockFuturo;
    private Double unitPrice;

    public static ProductByCatAndSupAndStockResponse from(Product aProduct) {
        return ProductByCatAndSupAndStockResponse.builder()
                .id(aProduct.getId())
                .name(aProduct.getName())
                .stockFuturo(aProduct.getUnitsInStock()+aProduct.getUnitsOnOrder())
                .unitPrice(aProduct.getUnitPrice())
                .build();
    }
}
