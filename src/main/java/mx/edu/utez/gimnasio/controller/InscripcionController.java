package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.InscripcionDTO;
import mx.edu.utez.gimnasio.model.inscripcion.Inscripcion;
import mx.edu.utez.gimnasio.service.InscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin("*")
public class InscripcionController {
    private final InscripcionService servicio;

    public InscripcionController(InscripcionService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Inscripcion> inscribir(@RequestBody @Valid InscripcionDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.inscribir(payload));
    }

    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<Inscripcion>> consultarInscritos(@PathVariable Long claseId) {
        return ResponseEntity.ok(servicio.consultarInscritosPorClase(claseId));
    }
}