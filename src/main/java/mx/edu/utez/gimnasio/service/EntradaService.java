package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.EntradaDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.model.cliente.Estado;
import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.model.contrato.EstadoContrato;
import mx.edu.utez.gimnasio.model.entrada.Entrada;
import mx.edu.utez.gimnasio.model.usuario.Usuario;
import mx.edu.utez.gimnasio.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntradaService {
    private final EntradaRepository entradaRepository;
    private final ClienteRepository clienteRepository;
    private final ContratoRepository contratoRepository;
    private final PagoRepository pagoRepository;
    private final UsuarioRepository usuarioRepository;

    public EntradaService(EntradaRepository entradaRepository, ClienteRepository clienteRepository, ContratoRepository contratoRepository, PagoRepository pagoRepository, UsuarioRepository usuarioRepository) {
        this.entradaRepository = entradaRepository;
        this.clienteRepository = clienteRepository;
        this.contratoRepository = contratoRepository;
        this.pagoRepository = pagoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Entrada registrarEntrada(EntradaDTO dto){
        Cliente cliente = clienteRepository.findByCurp(dto.getCurp())
                .orElseThrow(() -> new CustomException("Cliente no encontrado con esa CURP"));
        Contrato contrato = contratoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoContrato.ACTIVO)
                .orElseThrow(() -> new CustomException("El cliente no tiene un contrato activo"));
        Usuario recepcion = usuarioRepository.findById(dto.getRegistradoPorId())
                .orElseThrow(() -> new CustomException("Usuario de recepción no encontrado"));

        LocalDate fechaCorte = pagoRepository.findTopByContratoIdOrderByMesCubiertoDesc(contrato.getId())
                .map(p -> p.getMesCubierto().plusMonths(1))
                .orElse(contrato.getFechaInicio());

        if (LocalDate.now().isAfter(fechaCorte.plusDays(3))) {
            cliente.setEstado(Estado.BLOQUEADO);
            cliente.setMotivoBloqueo("Falta de pago tras 3 días de tolerancia");
            clienteRepository.save(cliente);
        }

        if (cliente.getEstado() == Estado.BLOQUEADO) {
            throw new CustomException("Acceso denegado: cliente bloqueado");
        }

        Entrada entrada = new Entrada();
        entrada.setContrato(contrato);
        entrada.setFechaHora(LocalDateTime.now());
        entrada.setRegistradoPor(recepcion);
        return entradaRepository.save(entrada);
    }

    public List<Entrada> listar() {
        return entradaRepository.findAll();    }
}
