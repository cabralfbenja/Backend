package ar.edu.frc.utn.bda3k4.northwind.entities.request;

import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    private byte[] picture;

    public Category toCategory() {
        return new Category(
                null,
                name,
                description,
                picture
        );
    }
}
