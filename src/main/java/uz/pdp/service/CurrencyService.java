package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Currency;
import uz.pdp.payload.Result;
import uz.pdp.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result create(Currency currency) {
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists) {
            return new Result("Bu pul birligi mavjud", false);
        }
        Currency currency1 = new Currency();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Bu pul birligi saqlandi", true);
    }


    public List<Currency> getAll() {
        List<Currency> all = currencyRepository.findAll();
        return all;
    }


    public Currency getOne(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Currency();
        }
        Currency currency = optionalCurrency.get();
        return currency;
    }


    public Result edit(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Result("Berilgan id topilmadi!", false);
        }
        Currency currency1 = optionalCurrency.get();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Berilgan pul birligi o'zgartirildi!", true);
    }

    public Result delete(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Result("Berilgan id topilmadi!", false);
        }
        currencyRepository.deleteById(id);
        return new Result("berilga pul birligi o'chirildi!", true);
    }
}
