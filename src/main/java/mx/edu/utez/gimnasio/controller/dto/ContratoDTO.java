package mx.edu.utez.gimnasio.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {
    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;
    @NotNull(message = "El planId es obligatorio")
    private Long planId;
    private Long promocionId; // Opcional
}
