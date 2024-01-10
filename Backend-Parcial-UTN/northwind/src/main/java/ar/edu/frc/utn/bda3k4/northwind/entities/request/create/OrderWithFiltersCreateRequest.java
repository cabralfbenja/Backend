package ar.edu.frc.utn.bda3k4.northwind.entities.request.create;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderWithFiltersCreateRequest {
    @NotBlank(message = "Supplier Id is mandatory")
    private Integer supplierId;
    @NotBlank(message = "Category Id is mandatory")
    private Integer categoryId;
    @NotBlank(message = "Required Stock is mandatory")
    private Integer requiredStock;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;

    @NotBlank(message = "Employee Id is mandatory")
    private Integer employeeId;
    @NotBlank(message = "Shipper Id is mandatory")
    private Integer shipperId;
}
