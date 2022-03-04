package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.Result;
import uz.pdp.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result create(@RequestBody Warehouse warehouse) {
        Result result = warehouseService.create(warehouse);
        return result;
    }

    @GetMapping
    public List<Warehouse> getAll() {
        List<Warehouse> all = warehouseService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Warehouse getOne(@PathVariable Integer id) {
        Warehouse one = warehouseService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        Result edit = warehouseService.edit(id, warehouse);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = warehouseService.delete(id);
        return delete;
    }
}
