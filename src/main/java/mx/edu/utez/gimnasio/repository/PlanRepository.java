package mx.edu.utez.gimnasio.repository;

import mx.edu.utez.gimnasio.model.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
