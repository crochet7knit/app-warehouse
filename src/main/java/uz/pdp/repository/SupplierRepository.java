package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
