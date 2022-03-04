package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Input;

public interface InputRepository extends JpaRepository<Input,Integer> {

    boolean existsByCode(String code);
}
