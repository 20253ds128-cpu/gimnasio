package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.pago.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
