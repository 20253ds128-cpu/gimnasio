package mx.edu.utez.gimnasio.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    @NotNull(message = "El contratoId es obligatorio")
    private Long contratoId;
    @NotNull(message = "El mes cubierto es obligatorio")
    private LocalDate mesCubierto;
    @NotBlank(message = "La forma de pago es obligatoria")
    private String formaPago;
    @NotNull(message = "El id del usuario que registra es obligatorio")
    private Long registradoPorId;
}
