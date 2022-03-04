package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    
    boolean existsByName(String name);

}
