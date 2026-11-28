package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.VisitaDTO;
import mx.edu.utez.gimnasio.model.visita.Visita;
import mx.edu.utez.gimnasio.service.VisitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitas")
@CrossOrigin("*")
public class VisitaController {
    private final VisitaService servicio;

    public VisitaController(VisitaService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Visita> registrarVisita(@RequestBody @Valid VisitaDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.registrarVisita(payload));
    }

    @GetMapping
    public ResponseEntity<List<Visita>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }
}
