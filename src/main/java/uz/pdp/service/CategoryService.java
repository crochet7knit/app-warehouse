package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.payload.CategoryDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent())
                return new Result("BUnday categoriya mavjud emas!", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("muvaffaqiyatli saqlandi", true);
    }

    public List<Category> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getOne(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new Category();
        }
        Category category = optionalCategory.get();
        return category;
    }

    public Result edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new Result("BU KATEGORIYA IDSI TOPILMADI!", false);
        }
        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optionalParentCategory.isPresent()) {
            return new Result("BU PARENT KATEGORIYA IDSI TOPILMADI!", false);
        }
        Category editCategory = optionalCategory.get();
        editCategory.setName(categoryDto.getName());
        editCategory.setParentCategory(optionalParentCategory.get());
        categoryRepository.save(editCategory);
        return new Result("BU KATEGORIYA O'ZGARTIRILDI!", true);
    }

    public Result delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new Result("BU KATEGORIYA IDSI TOPILMADI!", false);
        }
        categoryRepository.deleteById(id);
        return new Result("BU KATEGORIYA O'CHIRILDI!", true);
    }
}
