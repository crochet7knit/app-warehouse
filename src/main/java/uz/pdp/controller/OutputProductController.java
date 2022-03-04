package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.OutputProduct;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.OutputProductDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.OutputProductRepository;
import uz.pdp.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result create(@RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.create(outputProductDto);
        return result;
    }

    @GetMapping
    public List<OutputProduct> getAll() {
        List<OutputProduct> all = outputProductService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public OutputProduct getOne(@PathVariable Integer id) {
        OutputProduct one = outputProductService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        Result edit = outputProductService.edit(id, outputProductDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = outputProductService.delete(id);
        return delete;
    }
}
