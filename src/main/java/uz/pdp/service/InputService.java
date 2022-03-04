package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Input;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.InputDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result create(InputDto inputDto) {
        boolean exists = inputRepository.existsByCode(inputDto.getCode());
        if (exists) {
            return new Result("Bu kod mavjud", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bu omborxona mavjud emas", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Bu ta'minotchi mavjud emas", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bu pul birligi mavjud emas", false);
        }

        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(inputDto.getCode());

        inputRepository.save(input);
        return new Result("Mahsulot saqlandi", true);
    }

    public List<Input> getAll() {
        List<Input> all = inputRepository.findAll();
        return all;
    }

    public Input getOne(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Input();
        }
        Input input = optionalInput.get();
        return input;
    }

    public Result edit(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Result("Bu id mavjud", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bu omborxona mavjud emas", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Bu ta'minotchi mavjud emas", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bu pul birligi mavjud emas", false);
        }

        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(inputDto.getCode());

        inputRepository.save(input);
        return new Result("Mahsulot o'zgartirildi", true);
    }

    public Result delete(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Result("Bu id mavjud emas", false);
        }
        inputRepository.deleteById(id);
        return new Result("Mahsulot o'chirildi", true);
    }
}
