package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.PromocionDTO;
import mx.edu.utez.gimnasio.model.promocion.Promocion;
import mx.edu.utez.gimnasio.service.PromocionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/promociones")
@CrossOrigin("*")
public class PromocionController {
    private final PromocionService servicio;

    public PromocionController(PromocionService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Promocion> crear(@RequestBody @Valid PromocionDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(payload));
    }

    @GetMapping
    public ResponseEntity<List<Promocion>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }
}