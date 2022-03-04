package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Client;
import uz.pdp.payload.Result;
import uz.pdp.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result create(@RequestBody Client client) {
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists) {
            return new Result("Bu telefon raqam mavjud", false);
        }
        Client client1 = new Client();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Mijoz saqlandi", true);
    }

    public List<Client> getAll() {
        List<Client> clientList = clientRepository.findAll();
        return clientList;
    }

    public Client getOne(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Client();
        }
        return optionalClient.get();
    }

    public Result edit(@PathVariable Integer id, @RequestBody Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Result("berilgan id topilmadi", false);
        }
        Client editClient = optionalClient.get();
        editClient.setName(client.getName());
        editClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editClient);
        return new Result("Mijoz o'zgartirildi", true);
    }

    public Result delete(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Result("berilgan id topilmadi", false);
        }
        clientRepository.deleteById(id);
        return new Result("Mijoz o'chirildi", true);
    }
}
