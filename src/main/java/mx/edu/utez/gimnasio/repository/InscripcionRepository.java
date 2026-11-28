package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.inscripcion.EstadoInscripcion;
import mx.edu.utez.gimnasio.model.inscripcion.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    long countByClaseIdAndEstadoInscripcion(Long claseId, EstadoInscripcion estadoInscripcion);
    boolean existsByClaseIdAndClienteIdAndEstadoInscripcion(Long claseId, Long clienteId, EstadoInscripcion estadoInscripcion);
    List<Inscripcion> findByClaseIdAndEstadoInscripcion(Long claseId, EstadoInscripcion estadoInscripcion);
}
