package mx.edu.utez.gimnasio.controller;

import jakarta.validation.Valid;
import mx.edu.utez.gimnasio.controller.dto.PagoDTO;
import mx.edu.utez.gimnasio.model.pago.Pago;
import mx.edu.utez.gimnasio.service.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@CrossOrigin("*")
public class PagoController {
    private final PagoService servicio;

    public PagoController(PagoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody @Valid PagoDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.registrarPago(payload));
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }
}

