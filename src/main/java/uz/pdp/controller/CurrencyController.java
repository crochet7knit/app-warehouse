package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.Result;
import uz.pdp.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result create(@RequestBody Currency currency) {
        Result result = currencyService.create(currency);
        return result;
    }

    @GetMapping
    public List<Currency> getAll() {
        List<Currency> all = currencyService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Currency getOne(@PathVariable Integer id) {
        Currency one = currencyService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency) {
        Result edit = currencyService.edit(id, currency);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = currencyService.delete(id);
        return delete;
    }
}
