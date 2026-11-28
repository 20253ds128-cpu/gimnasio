package mx.edu.utez.gimnasio.controller.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PromocionDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El porcentaje de descuento es obligatorio")
    private BigDecimal porcentajeDescuento;
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;
    private List<Long> planesIds;
}
