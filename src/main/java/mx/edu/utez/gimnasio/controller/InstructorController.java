package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.InstructorDTO;
import mx.edu.utez.gimnasio.model.instructor.Instructor;
import mx.edu.utez.gimnasio.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructores")
@CrossOrigin("*")
public class InstructorController {
    private final InstructorService servicio;

    public InstructorController(InstructorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Instructor> crear(@RequestBody @Valid InstructorDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(payload));
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Instructor> cambiarEstado(@PathVariable Long id, @RequestParam boolean activo) {
        return ResponseEntity.ok(servicio.cambiarEstado(id, activo));
    }
}
