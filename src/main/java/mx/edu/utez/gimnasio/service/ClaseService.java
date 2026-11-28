package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.ClaseDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.clase.Clase;
import mx.edu.utez.gimnasio.model.instructor.Instructor;
import mx.edu.utez.gimnasio.repository.ClaseRepository;
import mx.edu.utez.gimnasio.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {
    private final ClaseRepository claseRepository;
    private final InstructorRepository instructorRepository;

    public ClaseService(ClaseRepository claseRepository, InstructorRepository instructorRepository) {
        this.claseRepository = claseRepository;
        this.instructorRepository = instructorRepository;
    }

    public Clase crear(ClaseDTO dto){
        Instructor inst = instructorRepository.findById(dto.getInstructorId())
                .orElseThrow(() -> new CustomException("Instructor no encontrado"));
        if(!inst.isActivo()){
            throw new CustomException("Instructor no encontrado");
        }
        Clase c = new Clase();
        c.setNombre(dto.getNombre());
        c.setInstructor(inst);
        c.setHoraInicio(dto.getHoraInicio());
        c.setHoraFin(dto.getHoraFin());
        c.setSala(dto.getSala());
        c.setCupoMaximo(dto.getCupoMaximo());
        c.setActiva(true);
        c.setInstructor(inst);
        return claseRepository.save(c);
    }

    public List<Clase>listar(){
        return claseRepository.findAll();
    }
}
