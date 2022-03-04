package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Product;
import uz.pdp.payload.ProductDto;
import uz.pdp.payload.Result;
import uz.pdp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        Result result = productService.addProduct(productDto);
        return result;
    }

    @GetMapping
    public List<Product> getAll() {
        List<Product> all = productService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Integer id) {
        Product one = productService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Result edit = productService.edit(id, productDto);
        return edit;
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result delete = productService.delete(id);
        return delete;
    }

}
