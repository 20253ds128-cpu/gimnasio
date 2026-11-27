package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.EntradaDTO;
import mx.edu.utez.gimnasio.model.entrada.Entrada;
import mx.edu.utez.gimnasio.service.EntradaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
@CrossOrigin("*")
public class EntradaController {
    private final EntradaService servicio;

    public EntradaController(EntradaService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Entrada> registrarEntrada(@RequestBody @Valid EntradaDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.registrarEntrada(payload));
    }

    @GetMapping
    public ResponseEntity<List<Entrada>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }
}
