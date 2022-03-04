package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.InputProduct;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.InputProductDto;
import uz.pdp.payload.Result;
import uz.pdp.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result create(@RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.create(inputProductDto);
        return result;
    }

    @GetMapping
    public List<InputProduct> getAll() {
        List<InputProduct> inputProductList = inputProductService.getAll();
        return inputProductList;
    }

    @GetMapping("/{id}")
    public InputProduct getOne(@PathVariable Integer id) {
        InputProduct one = inputProductService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        Result edit = inputProductService.edit(id, inputProductDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = inputProductService.delete(id);
        return delete;
    }
}
