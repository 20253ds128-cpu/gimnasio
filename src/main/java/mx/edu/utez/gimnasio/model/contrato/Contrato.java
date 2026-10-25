package mx.edu.utez.gimnasio.model.contrato;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.model.plan.Plan;
import mx.edu.utez.gimnasio.model.promocion.Promocion;

import java.time.LocalDate;

@Entity
@Table(name = "contratos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_cancelacion")
    private LocalDate fechaCancelacion;

    @Column(name = "precio_acordado", nullable = false)
    private Double precioAcordado;

    @Column(name = "meses_fidelidad", nullable = false)
    private int mesesFidelidad;

    @Column(name = "porcentaje_penalizacion", nullable = false)
    private Double porcentajePenalizacion;

    @Column(name = "visitas_gratis_mensuales", nullable = false)
    private int visitasGratisMensuales;

    @Column(name = "penalizacion_cobrada", nullable = false)
    private Double penalizacionCobrada = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoContrato estado;
}