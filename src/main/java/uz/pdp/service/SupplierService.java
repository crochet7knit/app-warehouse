package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Supplier;
import uz.pdp.payload.Result;
import uz.pdp.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result create(Supplier supplier) {
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists) {
            return new Result("Bu telefon raqamli ta'minotchi mavjud!", false);
        }
        Supplier supplier1 = new Supplier();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Ta'minotchi saqlandi!", true);
    }

    public List<Supplier> getAll() {
        List<Supplier> all = supplierRepository.findAll();
        return all;
    }

    public Supplier getOne(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Supplier();
        }
        Supplier supplier = optionalSupplier.get();
        return supplier;
    }

    public Result edit(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Result("Ta'minotchi idisi topilmadi!",false);
        }
        Supplier supplier1 = optionalSupplier.get();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Ta'minotchi idisi topilmadi!",true);
    }

    public Result delete(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Result("Ta'minotchi idisi topilmadi!",false);
        }
        supplierRepository.deleteById(id);
        return new Result("Ta'minotchi idisi topilmadi!",true);
    }
}
