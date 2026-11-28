package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.ClaseDTO;
import mx.edu.utez.gimnasio.model.clase.Clase;
import mx.edu.utez.gimnasio.service.ClaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clases")
@CrossOrigin("*")
public class ClaseController {
    private final ClaseService servicio;

    public ClaseController(ClaseService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Clase> crear(@RequestBody @Valid ClaseDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(payload));
    }

    @GetMapping
    public ResponseEntity<List<Clase>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }


}
