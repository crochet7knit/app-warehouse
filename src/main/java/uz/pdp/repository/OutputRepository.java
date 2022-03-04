package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {

    boolean existsByCode(String code);
}
