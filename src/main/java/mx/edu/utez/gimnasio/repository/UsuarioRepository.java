package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
