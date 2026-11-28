package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.PlanDTO;
import mx.edu.utez.gimnasio.model.plan.Plan;
import mx.edu.utez.gimnasio.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/planes")
@CrossOrigin("*")
public class PlanController {
    private final PlanService servicio;

    public PlanController(PlanService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Plan> crear(@RequestBody @Valid PlanDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(payload));
    }

    @GetMapping
    public ResponseEntity<List<Plan>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }
}