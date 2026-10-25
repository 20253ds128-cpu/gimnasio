package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.Clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseRepository extends JpaRepository<Clase,Long> {
}
