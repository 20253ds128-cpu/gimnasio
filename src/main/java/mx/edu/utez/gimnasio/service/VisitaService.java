package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.VisitaDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.cliente.Estado;
import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.model.usuario.Usuario;
import mx.edu.utez.gimnasio.model.visita.TipoVisita;
import mx.edu.utez.gimnasio.model.visita.Visita;
import mx.edu.utez.gimnasio.repository.ClienteRepository;
import mx.edu.utez.gimnasio.repository.ContratoRepository;
import mx.edu.utez.gimnasio.repository.UsuarioRepository;
import mx.edu.utez.gimnasio.repository.VisitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitaService {
    private final VisitaRepository visitaRepository;
    private final ClienteRepository clienteRepository;
    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;

    public VisitaService(VisitaRepository visitaRepository, ClienteRepository clienteRepository, ContratoRepository contratoRepository, UsuarioRepository usuarioRepository) {
        this.visitaRepository = visitaRepository;
        this.clienteRepository = clienteRepository;
        this.contratoRepository = contratoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Visita registrarVisita(VisitaDTO dto){
        clienteRepository.findByCurp(dto.getCurp()).ifPresent(cliente -> {
            if (cliente.getEstado() == Estado.BLOQUEADO){
                throw new CustomException("La CURP es de un cliente bloqueado");
            }
        });

        Usuario recepcion = usuarioRepository.findById(dto.getRegistradoPorId())
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));

        Contrato contrato = null;
        if (dto.getTipo() == TipoVisita.GRATIS_PLAN){
            if (dto.getContratoId() == null){
                throw new CustomException("Se debe de indicar el id del contrato para la visita gratis");
            }
            contrato = contratoRepository.findById(dto.getContratoId())
                    .orElseThrow(()-> new CustomException("Contrato no encontrado"));

            LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
            LocalDateTime finMes = LocalDate.now().withDayOfMonth(LocalDate.now().getDayOfMonth()).atTime(23, 59, 59);
            long usadas = visitaRepository.countByContratoIdAndTipoAndFechaHoraBetween(contrato.getId(), TipoVisita.GRATIS_PLAN, inicioMes, finMes);

            if (usadas >= contrato.getVisitasGratisMensuales()){
                throw new CustomException("Las visitas gratis ya están agotadas");
            }
        }

        Visita v = new Visita();
        v.setCurp(dto.getCurp());
        v.setNombreVisitante(dto.getNombreVisitante());
        v.setTipo(dto.getTipo());
        v.setContrato(contrato);
        v.setFechaHora(LocalDateTime.now());
        v.setRegistradoPor(recepcion);
        return visitaRepository.save(v);
    }

    public List<Visita> listar(){
        return visitaRepository.findAll();
    }
}
