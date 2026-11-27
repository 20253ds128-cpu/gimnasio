package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.InscripcionDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.clase.Clase;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.model.cliente.Estado;
import mx.edu.utez.gimnasio.model.inscripcion.EstadoInscripcion;
import mx.edu.utez.gimnasio.model.inscripcion.Inscripcion;
import mx.edu.utez.gimnasio.repository.ClaseRepository;
import mx.edu.utez.gimnasio.repository.ClienteRepository;
import mx.edu.utez.gimnasio.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final ClaseRepository claseRepository;
    private final ClienteRepository clienteRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository, ClaseRepository claseRepository,
                              ClienteRepository clienteRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.claseRepository = claseRepository;
        this.clienteRepository = clienteRepository;
    }

    public Inscripcion inscribir(InscripcionDTO dto) {
        Clase clase = claseRepository.findById(dto.getClaseId())
                .orElseThrow(() -> new CustomException("Clase no encontrada"));
        Cliente cliente = clienteRepository.findByCurp(dto.getCurp())
                .orElseThrow(() -> new CustomException("Cliente no encontrado"));

        if (cliente.getEstado() == Estado.BLOQUEADO) {
            throw new CustomException("El cliente está bloqueado");
        }
        if (inscripcionRepository.existsByClaseIdAndClienteIdAndEstadoInscripcion(clase.getId(), cliente.getId(), EstadoInscripcion.ACTIVA)) {
            throw new CustomException("El cliente ya está inscrito en esta clase");
        }
        long inscritosActuales = inscripcionRepository.countByClaseIdAndEstadoInscripcion(clase.getId(), EstadoInscripcion.ACTIVA);
        if (inscritosActuales >= clase.getCupoMaximo()) {
            throw new CustomException("El cupo de la clase está lleno");
        }

        Inscripcion ins = new Inscripcion();
        ins.setClase(clase);
        ins.setCliente(cliente);
        ins.setFechaInscripcion(LocalDateTime.now());
        ins.setEstadoInscripcion(EstadoInscripcion.ACTIVA);
        return inscripcionRepository.save(ins);
    }

    public List<Inscripcion> consultarInscritosPorClase(Long claseId) {
        if (!claseRepository.existsById(claseId)) {
            throw new CustomException("Clase no encontrada");
        }
        return inscripcionRepository.findByClaseIdAndEstadoInscripcion(claseId, EstadoInscripcion.ACTIVA);
    }
}