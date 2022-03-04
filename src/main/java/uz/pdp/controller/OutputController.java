package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Output;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.OutputDto;
import uz.pdp.payload.Result;
import uz.pdp.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result create(@RequestBody OutputDto outputDto) {
        Result result = outputService.create(outputDto);
        return result;
    }

    @GetMapping
    public List<Output> getAll() {
        List<Output> all = outputService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Output getOne(@PathVariable Integer id) {
        Output one = outputService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        Result edit = outputService.edit(id, outputDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = outputService.delete(id);
        return delete;
    }
}
