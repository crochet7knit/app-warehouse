package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
}
