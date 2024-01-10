package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Order;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Integer id;
    private String customerId;
    private Integer employeeId;
    private String orderDate;
    private String requiredDate;
    private String shippedDate;
    private Integer shipVia;
    private Double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    public static OrderResponse from(Order anOrder) {
        LocalDateTimeAttributeConverter converter = new LocalDateTimeAttributeConverter();
        String oDate = converter.convertToDatabaseColumn(anOrder.getOrderDate());
        String rDate = converter.convertToDatabaseColumn(anOrder.getRequiredDate());
        String sDate = converter.convertToDatabaseColumn(anOrder.getShippedDate());
        Integer shipper = null;
        if (anOrder.getShipper() != null) {shipper = anOrder.getShipper().getId();}
        return OrderResponse.builder()
                .id(anOrder.getId())
                .customerId(anOrder.getCustomer().getId())
                .employeeId(anOrder.getEmployee().getId())
                .orderDate(oDate)
                .requiredDate(rDate)
                .shippedDate(sDate)
                .shipVia(shipper)
                .freight(anOrder.getFreight())
                .shipName(anOrder.getShipName())
                .shipAddress(anOrder.getShipAddress())
                .shipCity(anOrder.getShipCity())
                .shipRegion(anOrder.getShipRegion())
                .shipPostalCode(anOrder.getShipPostalCode())
                .shipCountry(anOrder.getShipCountry())
                .build();
    }
}
