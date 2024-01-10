package ar.edu.frc.utn.bda3k4.northwind.entities.response;

import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;
    private byte[] picture;

    public static CategoryResponse from(Category aCategory) {
        return CategoryResponse.builder()
                .id(aCategory.getId())
                .name(aCategory.getName())
                .description(aCategory.getDescription())
                .picture(aCategory.getPicture())
                .build();
    }
}
