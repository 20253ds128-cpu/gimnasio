package mx.edu.utez.gimnasio.controller.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PlanDTO {
    @NotBlank(message = "El nombre del plan es obligatorio")
    private String nombre;
    @NotNull(message = "El precio mensual es obligatorio") @Min(value = 1, message = "El precio debe ser mayor a 0")
    private Double precioMensual;
    @NotNull(message = "Los meses de fidelidad son obligatorios") @Min(value = 0, message = "Mínimo 0 meses")
    private int mesesFidelidad;
    @NotNull(message = "El porcentaje de penalización es obligatorio") @Min(value = 0, message = "Mínimo 0")
    private int porcentajePenalizacion;
    @Min(value = 0, message = "Mínimo 0 visitas")
    private Integer visitasGratisMensuales;
}