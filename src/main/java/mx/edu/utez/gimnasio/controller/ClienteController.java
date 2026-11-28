package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.ClienteDTO;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {
    private final ClienteService servicio;

    public ClienteController(ClienteService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody @Valid ClienteDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(payload));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{curp}")
    public ResponseEntity<Cliente> buscarPorCurp(@PathVariable String curp) {
        return ResponseEntity.ok(servicio.buscarPorCurp(curp));
    }
}