package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Input;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.InputDto;
import uz.pdp.payload.Result;
import uz.pdp.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result create(@RequestBody InputDto inputDto) {
        Result result = inputService.create(inputDto);
        return result;
    }

    @GetMapping
    public List<Input> getAll() {
        List<Input> all = inputService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Input getOne(@PathVariable Integer id) {
        Input one = inputService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        Result edit = inputService.edit(id, inputDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = inputService.delete(id);
        return delete;
    }
}
