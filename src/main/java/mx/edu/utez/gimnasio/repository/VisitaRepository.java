package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.visita.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
}
