package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
