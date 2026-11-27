package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.model.contrato.EstadoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    Optional<Contrato> findByClienteIdAndEstado(Long clienteId, EstadoContrato estado);
}