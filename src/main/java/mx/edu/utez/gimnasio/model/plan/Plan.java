package mx.edu.utez.gimnasio.model.plan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.promocion.Promocion;

import java.util.List;

@Entity
@Table(name = "planes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "precio_mensual", nullable = false)
    private Double precioMensual;

    @Column(name = "meses_fidelidad", nullable = false)
    private int mesesFidelidad = 0;

    @Column(name = "porcentaje_penalizacion", nullable = false)
    private int porcentajePenalizacion;

    @Column(name = "visitas_gratis_mensuales")
    private Integer visitasGratisMensuales;

    @Column(nullable = false)
    private boolean activo = true;

    // Genera automáticamente la relación y tabla intermedia plan_promocion
    @ManyToMany
    @JoinTable(
            name = "plan_promocion",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id")
    )
    private List<Promocion> promociones;
}