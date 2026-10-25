package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.promocion.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocionRepository extends JpaRepository<Promocion, Long> {
}
