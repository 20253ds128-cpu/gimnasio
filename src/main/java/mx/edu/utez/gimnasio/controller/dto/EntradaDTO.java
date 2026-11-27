package mx.edu.utez.gimnasio.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntradaDTO {
    @NotBlank(message = "La CURP es obligatoria")
    private String curp;
    @NotNull(message = "El id del usuario de recepción es obligatorio")
    private Long registradoPorId;
}
