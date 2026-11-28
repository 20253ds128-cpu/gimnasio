package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.InstructorDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.instructor.Instructor;
import mx.edu.utez.gimnasio.model.usuario.Rol;
import mx.edu.utez.gimnasio.model.usuario.Usuario;
import mx.edu.utez.gimnasio.repository.InstructorRepository;
import mx.edu.utez.gimnasio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private InstructorRepository instructorRepository;
    private UsuarioRepository usuarioRepository;

    public InstructorService(InstructorRepository instructorRepository, UsuarioRepository usuarioRepository) {
        this.instructorRepository = instructorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Instructor crear(InstructorDTO dto){
        Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));
        if(u.getRol() != Rol.INSTRUCTOR){
            throw new CustomException("El usuario debe tener rol INSTRUCTOR");
        }

        Instructor inst = new Instructor();
        inst.setNombre(dto.getNombre());
        inst.setEspecialidad(dto.getEspecialidad());
        inst.setActivo(true);
        inst.setUsuario(u);
        return instructorRepository.save(inst);

    }

    public List<Instructor>listar(){
        return instructorRepository.findAll();
    }

    public Instructor cambiarEstado(Long id, boolean activo){
        Instructor inst = instructorRepository.findById(id)
                .orElseThrow(() -> new CustomException("El usuario no encontrado"));
        inst.setActivo(activo);
        return instructorRepository.save(inst);
    }
}
