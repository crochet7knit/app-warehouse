package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
}
