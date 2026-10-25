package mx.edu.utez.gimnasio.model.clase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.instructor.Instructor;


import java.time.LocalTime;

@Entity
@Table(name = "clases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "dia_semana", nullable = false, length = 15)
    private String diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false, length = 50)
    private String sala;

    @Column(name = "cupo_maximo", nullable = false)
    private int cupoMaximo;

    @Column(nullable = false)
    private boolean activa = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
}