package mx.edu.utez.gimnasio.model.inscripcion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.clase.Clase;
import mx.edu.utez.gimnasio.model.cliente.Cliente;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones",
uniqueConstraints = {@UniqueConstraint(columnNames = {"clase_id", "cliente_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDateTime fechaInscripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoInscripcion estadoInscripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;
}