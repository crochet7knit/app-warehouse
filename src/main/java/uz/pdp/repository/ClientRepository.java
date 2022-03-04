package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
