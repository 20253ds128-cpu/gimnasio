package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.Inscripcion.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
}
