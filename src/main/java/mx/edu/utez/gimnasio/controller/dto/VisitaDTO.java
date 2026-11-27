package mx.edu.utez.gimnasio.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.visita.TipoVisita;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitaDTO {
    @NotBlank(message = "La CURP es obligatoria")
    @Size(min = 18, max = 18, message = "La CURP debe tener 18 caracteres")
    private String curp;
    @NotBlank(message = "El nombre del visitante es obligatorio")
    private String nombreVisitante;
    @NotNull(message = "El tipo de visita es obligatorio")
    private TipoVisita tipo;
    private Long contratoId;
    @NotNull(message = "El id del usuario de recepción es obligatorio")
    private Long registradoPorId;
}
