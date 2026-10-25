package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.instructor.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
