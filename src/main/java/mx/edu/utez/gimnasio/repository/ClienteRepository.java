package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCurp(String curp);
    boolean existsByCurp(String curp);
}
