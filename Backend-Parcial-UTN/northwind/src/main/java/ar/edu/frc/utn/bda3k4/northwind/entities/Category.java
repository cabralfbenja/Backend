package ar.edu.frc.utn.bda3k4.northwind.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Categories")
    @TableGenerator(name = "Categories", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Categories",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "CategoryName")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Picture")
    private byte[] picture;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    public Category(Integer id, String name, String description, byte[] picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public void update(Category entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        if (entity.getPicture() != null) this.picture = entity.getPicture();
    }
}
