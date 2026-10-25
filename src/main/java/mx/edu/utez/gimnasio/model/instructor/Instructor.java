package mx.edu.utez.gimnasio.model.instructor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.usuario.Usuario;

@Entity
@Table(name = "instructores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Especialidad especialidad;

    @Column(nullable = false)
    private boolean activo = true;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;
}