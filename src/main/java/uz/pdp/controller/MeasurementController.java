package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Measurement;
import uz.pdp.payload.Result;
import uz.pdp.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @GetMapping
    public List<Measurement> getAll() {
        List<Measurement> all = measurementService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Measurement getOne(@PathVariable Integer id) {
        Measurement serviceOne = measurementService.getOne(id);
        return serviceOne;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Measurement measurement) {
        Result edit = measurementService.edit(id, measurement);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id) {
        Result deleted = measurementService.deleted(id);
        return deleted;
    }
}
