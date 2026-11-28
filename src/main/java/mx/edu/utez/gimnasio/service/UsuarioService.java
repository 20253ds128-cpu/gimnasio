package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.UsuarioDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.usuario.Usuario;
import mx.edu.utez.gimnasio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crear(UsuarioDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new CustomException("El username ya está en uso");
        }
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setUsername(dto.getUsername());
        u.setPasswordHash(dto.getPassword());
        u.setRol(dto.getRol());
        u.setActivo(true);
        return usuarioRepository.save(u);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario cambiarEstado(Long id, boolean activo) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));
        u.setActivo(activo);
        return usuarioRepository.save(u);
    }
}