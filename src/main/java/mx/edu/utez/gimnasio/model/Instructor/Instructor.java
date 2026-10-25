package mx.edu.utez.gimnasio.model.Instructor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gimnasio.model.Usuario.Usuario;

@Entity
@Table(name = "Instructor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Falta la llave foranea
    private String nombre;

   @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
   private boolean activo;

   //Llave foranea
    @OneToOne
    @JoinColumn(name="Usuario_id")
    private Usuario usuario;



}
