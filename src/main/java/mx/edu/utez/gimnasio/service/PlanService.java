package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.PlanDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.plan.Plan;
import mx.edu.utez.gimnasio.repository.PlanRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Plan crear(PlanDTO dto) {
        if (planRepository.existsByNombre(dto.getNombre())) {
            throw new CustomException("Ya existe un plan con ese nombre");
        }
        Plan p = new Plan();
        p.setNombre(dto.getNombre());
        p.setPrecioMensual(dto.getPrecioMensual());
        p.setMesesFidelidad(dto.getMesesFidelidad());
        p.setPorcentajePenalizacion(dto.getPorcentajePenalizacion());
        p.setVisitasGratisMensuales(dto.getVisitasGratisMensuales());
        p.setActivo(true);
        p.setPromociones(new ArrayList<>());
        return planRepository.save(p);
    }

    public List<Plan> listar() {
        return planRepository.findAll();
    }
}