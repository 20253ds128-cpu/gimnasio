package mx.edu.utez.gimnasio.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.instructor.Especialidad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La especialidad es obligatoria")
    private Especialidad especialidad;
}
