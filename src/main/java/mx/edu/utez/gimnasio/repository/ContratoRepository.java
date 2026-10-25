package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
