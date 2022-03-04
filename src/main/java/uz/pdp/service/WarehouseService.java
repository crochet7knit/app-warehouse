package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.Result;
import uz.pdp.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result create(Warehouse warehouse) {
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (exists) {
            return new Result("Bu Ombor mavjud", false);
        }
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Ombor saqlandi", true);
    }

    public List<Warehouse> getAll() {
        List<Warehouse> all = warehouseRepository.findAll();
        return all;
    }

    public Warehouse getOne(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return new Warehouse();
        }
        Warehouse warehouse = optionalWarehouse.get();
        return warehouse;
    }

    public Result edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return new Result("Ombor id si topilmadi", false);
        }
        Warehouse warehouse1 = optionalWarehouse.get();
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Ombor o'zgartirildi", true);
    }

    public Result delete(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return new Result("Ombor id si topilmadi", false);
        }
        warehouseRepository.deleteById(id);
        return new Result("Ombor o'chirildi", true);
    }
}
