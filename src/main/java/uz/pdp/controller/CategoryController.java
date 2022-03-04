package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Category;
import uz.pdp.payload.CategoryDto;
import uz.pdp.payload.Result;
import uz.pdp.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    @GetMapping
    public List<Category> getAll() {
        List<Category> categoryList = categoryService.getAll();
        return categoryList;
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Integer id) {
        Category one = categoryService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        Result edit = categoryService.edit(id, categoryDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = categoryService.delete(id);
        return delete;
    }

}
