package ar.edu.frc.utn.bda3k4.northwind.services.implementations;

import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import ar.edu.frc.utn.bda3k4.northwind.repositories.CategoryRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category entity) {
        return this.categoryRepository.save(entity);
    }

    @Override
    public Category update(Integer id, Category entity) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Category not found"));
        category.update(entity);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category delete(Integer id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Category not found"));
        if(category != null){
            this.categoryRepository.delete(category);
            return category;
        }
        throw new IllegalArgumentException("Category not found");
    }

    @Override
    public Category findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("CategoryId can't be null");
        return this.categoryRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Category not found"));
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = this.categoryRepository.findAll();
        if(categories.isEmpty()){throw new IllegalArgumentException("No categories found");}
        return categories;
    }
}
