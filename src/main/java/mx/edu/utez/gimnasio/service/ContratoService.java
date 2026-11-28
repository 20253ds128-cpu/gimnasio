package mx.edu.utez.gimnasio.service;

import mx.edu.utez.gimnasio.controller.dto.ContratoDTO;
import mx.edu.utez.gimnasio.exeption.CustomException;
import mx.edu.utez.gimnasio.model.*;
import mx.edu.utez.gimnasio.model.cliente.Cliente;
import mx.edu.utez.gimnasio.model.cliente.Estado;
import mx.edu.utez.gimnasio.model.contrato.Contrato;
import mx.edu.utez.gimnasio.model.contrato.EstadoContrato;
import mx.edu.utez.gimnasio.model.plan.Plan;
import mx.edu.utez.gimnasio.model.promocion.Promocion;
import mx.edu.utez.gimnasio.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final ClienteRepository clienteRepository;
    private final PlanRepository planRepository;
    private final PromocionRepository promocionRepository;

    public ContratoService(ContratoRepository contratoRepository, ClienteRepository clienteRepository,
                           PlanRepository planRepository, PromocionRepository promocionRepository) {
        this.contratoRepository = contratoRepository;
        this.clienteRepository = clienteRepository;
        this.planRepository = planRepository;
        this.promocionRepository = promocionRepository;
    }

    public Contrato contratar(ContratoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new CustomException("Cliente no encontrado"));
        if (cliente.getEstado() == Estado.BLOQUEADO) {
            throw new CustomException("El cliente está bloqueado");
        }
        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new CustomException("Plan no encontrado"));

        double precioFinal = plan.getPrecioMensual();
        Promocion promo = null;

        if (dto.getPromocionId() != null) {
            promo = promocionRepository.findById(dto.getPromocionId())
                    .orElseThrow(() -> new CustomException("Promoción no encontrada"));
            LocalDate hoy = LocalDate.now();
            boolean vigente = !hoy.isBefore(promo.getFechaInicio()) && !hoy.isAfter(promo.getFechaFin());
            boolean aplicaAlPlan = plan.getPromociones() != null && plan.getPromociones().contains(promo);
            if (!promo.isActiva() || !vigente || !aplicaAlPlan) {
                throw new CustomException("La promoción no aplica para este plan o no está vigente");
            }
            double desc = promo.getPorcentajeDescuento().doubleValue();
            double factor = (desc > 1.0) ? (desc / 100.0) : desc;
            precioFinal -= (precioFinal * factor);
        }

        Contrato c = new Contrato();
        c.setCliente(cliente);
        c.setPlan(plan);
        c.setPromocion(promo);
        c.setFechaInicio(LocalDate.now());
        c.setPrecioAcordado(precioFinal);
        c.setMesesFidelidad(plan.getMesesFidelidad());
        c.setPorcentajePenalizacion((double) plan.getPorcentajePenalizacion());
        c.setVisitasGratisMensuales(plan.getVisitasGratisMensuales() != null ? plan.getVisitasGratisMensuales() : 0);
        c.setPenalizacionCobrada(0.0);
        c.setEstado(EstadoContrato.ACTIVO);

        return contratoRepository.save(c);
    }

    public Contrato cancelar(Long contratoId) {
        Contrato c = contratoRepository.findById(contratoId)
                .orElseThrow(() -> new CustomException("Contrato no encontrado"));
        if (c.getEstado() == EstadoContrato.CANCELADO) {
            throw new CustomException("El contrato ya está cancelado");
        }

        long mesesCumplidos = ChronoUnit.MONTHS.between(c.getFechaInicio(), LocalDate.now());
        long mesesFaltantes = c.getMesesFidelidad() - mesesCumplidos;

        double penalizacion = 0.0;
        if (mesesFaltantes > 0) {
            double pct = c.getPorcentajePenalizacion();
            double factor = (pct > 1.0) ? (pct / 100.0) : pct;
            penalizacion = mesesFaltantes * c.getPrecioAcordado() * factor;
        }

        c.setFechaCancelacion(LocalDate.now());
        c.setPenalizacionCobrada(penalizacion);
        c.setEstado(EstadoContrato.CANCELADO);
        return contratoRepository.save(c);
    }

    public List<Contrato> listar() {
        return contratoRepository.findAll();
    }
}