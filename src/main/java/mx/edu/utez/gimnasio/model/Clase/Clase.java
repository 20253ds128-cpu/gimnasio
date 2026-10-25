package mx.edu.utez.gimnasio.model.Clase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.Instructor.Instructor;


import java.time.LocalTime;

@Entity
@Table(name = "Clase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dia_semana;
    private LocalTime hora_Inicio;
    private LocalTime hora_Fin;
    private String sala;
    private int cupoMaximo;
    private boolean Activa;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;
}
