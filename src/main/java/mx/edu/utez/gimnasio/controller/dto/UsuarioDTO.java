package mx.edu.utez.gimnasio.controller.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.utez.gimnasio.model.usuario.Rol;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El username es obligatorio")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    @NotNull(message = "El rol es obligatorio")
    private Rol rol;
}