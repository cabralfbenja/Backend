package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.CategoryController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.CategoryRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.CategoryRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

public class CategoryControllerTest {
    private CategoryController categoryController;
    private CategoryRepository categoryRepository;
    private final Category CATEGORY = new Category(1, "Beverages", "Soft drinks, coffees, teas, beers, and ales", null);

    @BeforeEach
    public void setup(){
        categoryRepository = Mockito.mock(CategoryRepository.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    void testFindAll(){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(CATEGORY);
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        Assertions.assertEquals(
                HttpStatus.OK,
                categoryController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty(){
        Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                categoryController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Assertions.assertEquals(
                HttpStatus.OK,
                categoryController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                categoryController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testAdd(){
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(CATEGORY);
        Mockito.when(categoryRepository.saveAndFlush(any(Category.class))).thenReturn(CATEGORY);
        Assertions.assertEquals(
                HttpStatus.OK,
                categoryController.add(new CategoryRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdate(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Mockito.when(categoryRepository.save(CATEGORY)).thenReturn(CATEGORY);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                categoryController.update(1, new CategoryRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                categoryController.update(1, new CategoryRequest()).getStatusCode()
        );
    }

    @Test
    void testDelete(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Assertions.assertEquals(
                HttpStatus.OK,
                categoryController.delete(1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                categoryController.delete(1).getStatusCode()
        );
    }
}
