package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.visita.TipoVisita;
import mx.edu.utez.gimnasio.model.visita.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {
    long countByContratoIdAndTipoAndFechaHoraBetween(Long contratoId, TipoVisita tipo, LocalDateTime inicio, LocalDateTime fin);
}
