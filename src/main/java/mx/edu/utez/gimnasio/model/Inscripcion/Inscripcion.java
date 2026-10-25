package mx.edu.utez.gimnasio.model.Inscripcion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.Clase.Clase;
import mx.edu.utez.gimnasio.model.Cliente.Cliente;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Inscripcion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaInscripcion;
    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estadoInscripcion;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="clase_id")
    private Clase clase;
}
