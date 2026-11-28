package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.ClienteDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.model.cliente.Estado;
import mx.edu.utez.gimnasio.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crear(ClienteDTO dto) {
        if (clienteRepository.existsByCurp(dto.getCurp())) {
            throw new CustomException("Ya existe un cliente registrado con esa CURP");
        }
        Cliente c = new Cliente();
        c.setCurp(dto.getCurp());
        c.setNombre(dto.getNombre());
        c.setTelefono(dto.getTelefono());
        c.setEmail(dto.getEmail());
        c.setEstado(Estado.ACTIVO);
        return clienteRepository.save(c);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorCurp(String curp) {
        return clienteRepository.findByCurp(curp)
                .orElseThrow(() -> new CustomException("Cliente no encontrado con esa CURP"));
    }
}