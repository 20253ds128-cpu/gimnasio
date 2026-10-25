package mx.edu.utez.gimnasio.model.pago;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.model.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "pagos",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"contrato_id", "mes_cubierto"})}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @Column(name = "mes_cubierto", nullable = false)
    private LocalDate mesCubierto;

    @Column(nullable = false)
    private Double monto;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    @Column(name = "forma_pago", nullable = false, length = 30)
    private String formaPago;

    @ManyToOne(optional = false)
    @JoinColumn(name = "registrado_por", nullable = false)
    private Usuario registradoPor;
}