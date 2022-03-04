package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Client;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Output;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.OutputDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.ClientRepository;
import uz.pdp.repository.CurrencyRepository;
import uz.pdp.repository.OutputRepository;
import uz.pdp.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result create(OutputDto outputDto) {
        boolean exists = outputRepository.existsByCode(outputDto.getCode());
        if (exists) {
            return new Result("Kod mavjud!", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bu omborxona mavjud!", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) {
            return new Result("Bu mijoz mavjud!", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bu pul birligi mavjud!", false);
        }
        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(outputDto.getCode());
        outputRepository.save(output);
        return new Result("Chiqim saqlandi!", true);
    }

    public List<Output> getAll() {
        List<Output> all = outputRepository.findAll();
        return all;
    }

    public Output getOne(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Output();
        }
        Output output = optionalOutput.get();
        return output;
    }

    public Result edit(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Result("Bu mahsulot mavjud emas", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bu omborxona mavjud!", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) {
            return new Result("Bu mijoz mavjud!", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bu pul birligi mavjud!", false);
        }
        Output output = optionalOutput.get();
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(outputDto.getCode());
        outputRepository.save(output);
        return new Result("Bu mahsulot sotildi", true);

    }

    public Result delete(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Result("Bu mahsulot mavjud emas", false);
        }
        outputRepository.deleteById(id);
        return new Result("Mahsulot o'chirildi",true);
    }
}
