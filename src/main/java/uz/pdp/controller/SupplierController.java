package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.Result;
import uz.pdp.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result create(@RequestBody Supplier supplier) {
        Result result = supplierService.create(supplier);
        return result;
    }

    @GetMapping
    public List<Supplier> getAll() {
        List<Supplier> all = supplierService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Supplier getOne(@PathVariable Integer id) {
        Supplier one = supplierService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Supplier supplier) {
        Result edit = supplierService.edit(id, supplier);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = supplierService.delete(id);
        return delete;
    }
}
