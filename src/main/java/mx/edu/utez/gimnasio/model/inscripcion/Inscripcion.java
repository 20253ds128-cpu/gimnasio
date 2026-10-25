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
