package mx.edu.utez.gimnasio.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaseDTO {
    @NotBlank(message = "El nombre de la clase es obligatorio")
    private String nombre;
    @NotNull(message = "El instructorId es obligatorio")
    private Long instructorId;
    @NotBlank(message = "El día de la semana es obligatorio")
    private String diaSemana;
    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;
    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;
    @NotBlank(message = "La sala es obligatoria")
    private String sala;
    @NotNull(message = "El cupo máximo es obligatorio")
    @Min(value = 1, message = "El cupo mínimo es 1")
    private Integer cupoMaximo;
}

