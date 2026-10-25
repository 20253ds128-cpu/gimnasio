package mx.edu.utez.gimnasio.model.Cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String curp;
    private String nombre;
    private String email;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String motivoBloqueo;

}
