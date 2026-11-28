package mx.edu.utez.gimnasio.repository;
import mx.edu.utez.gimnasio.model.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    boolean existsByNombre(String nombre);
}