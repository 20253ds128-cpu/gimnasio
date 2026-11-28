package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.ContratoDTO;
import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.service.ContratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contratos")
@CrossOrigin("*")
public class ContratoController {
    private final ContratoService servicio;

    public ContratoController(ContratoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Contrato> contratar(@RequestBody @Valid ContratoDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.contratar(payload));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Contrato> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.cancelar(id));
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }
}