package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.PromocionDTO;
import mx.edu.utez.gimnasio.model.plan.Plan;
import mx.edu.utez.gimnasio.model.promocion.Promocion;
import mx.edu.utez.gimnasio.repository.PlanRepository;
import mx.edu.utez.gimnasio.repository.PromocionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromocionService {
    private final PromocionRepository promocionRepository;
    private final PlanRepository planRepository;

    public PromocionService(PromocionRepository promocionRepository, PlanRepository planRepository) {
        this.promocionRepository = promocionRepository;
        this.planRepository = planRepository;
    }

    public Promocion crear(PromocionDTO dto) {
        Promocion promo = new Promocion();
        promo.setNombre(dto.getNombre());
        promo.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        promo.setFechaInicio(dto.getFechaInicio());
        promo.setFechaFin(dto.getFechaFin());
        promo.setActiva(true);
        Promocion guardada = promocionRepository.save(promo);

        // Plan es el dueño de @ManyToMany en dev, por eso guardamos desde Plan
        if (dto.getPlanesIds() != null && !dto.getPlanesIds().isEmpty()) {
            List<Plan> planes = planRepository.findAllById(dto.getPlanesIds());
            for (Plan plan : planes) {
                if (plan.getPromociones() == null) {
                    plan.setPromociones(new ArrayList<>());
                }
                plan.getPromociones().add(guardada);
            }
            planRepository.saveAll(planes);
        }
        return guardada;
    }

    public List<Promocion> listar() {
        return promocionRepository.findAll();
    }
}