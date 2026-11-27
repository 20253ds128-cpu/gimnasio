package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.PagoDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.model.cliente.Estado;
import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.model.contrato.EstadoContrato;
import mx.edu.utez.gimnasio.model.pago.Pago;
import mx.edu.utez.gimnasio.model.usuario.Usuario;
import mx.edu.utez.gimnasio.repository.ClienteRepository;
import mx.edu.utez.gimnasio.repository.ContratoRepository;
import mx.edu.utez.gimnasio.repository.PagoRepository;
import mx.edu.utez.gimnasio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;
    private final ContratoRepository contratoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public PagoService(PagoRepository pagoRepository, ContratoRepository contratoRepository, ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.pagoRepository = pagoRepository;
        this.contratoRepository = contratoRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pago registrarPago(PagoDTO dto){
        Contrato contrato = contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new CustomException("Cobtrato no encontrado"));
        if(contrato.getEstado() == EstadoContrato.CANCELADO){
            throw new CustomException("El contrato está cancelado");
        }

        Usuario recepcion = usuarioRepository.findById(dto.getRegistradoPorId())
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));

        Pago pago = new Pago();
        pago.setContrato(contrato);
        pago.setMesCubierto(dto.getMesCubierto());
        pago.setMonto(contrato.getPrecioAcordado());
        pago.setFechaPago(LocalDateTime.now());
        pago.setRegistradoPor(recepcion);

        Cliente cliente = new Cliente();
        cliente.setEstado(Estado.ACTIVO);
        cliente.setMotivoBloqueo(null);
        clienteRepository.save(cliente);

        return pagoRepository.save(pago);
    }

    public List<Pago> listar(){
        return pagoRepository.findAll();
    }
}
