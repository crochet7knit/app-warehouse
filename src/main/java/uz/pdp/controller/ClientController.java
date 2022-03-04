package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Client;
import uz.pdp.payload.Result;
import uz.pdp.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result create(@RequestBody Client client) {
        Result result = clientService.create(client);
        return result;
    }

    @GetMapping
    public List<Client> getAll() {
        List<Client> all = clientService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Integer id) {
        Client one = clientService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Client client) {
        Result edit = clientService.edit(id, client);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = clientService.delete(id);
        return delete;
    }
}
