package ec.edu.espe.marca.liquidacion.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import ec.edu.espe.marca.liquidacion.model.Liquidacion;
import ec.edu.espe.marca.liquidacion.repository.LiquidacionRepository;

@Service
public class LiquidacionService {

    private LiquidacionRepository liquidacionRepository;

    public LiquidacionService(LiquidacionRepository liquidacionRepository) {
        this.liquidacionRepository = liquidacionRepository;
    }

    public Liquidacion obtenerLiquidacionPorId(Integer id) {
        Optional<Liquidacion> liquidacionOpt = this.liquidacionRepository.findById(id);
        if (liquidacionOpt.isPresent()) {
            return liquidacionOpt.get();
        } else {
            throw new RuntimeException("No existe la liquidación con el ID: " + id);
        }
    }

    public Liquidacion crearLiquidacion(Liquidacion liquidacion) {
        return this.liquidacionRepository.save(liquidacion);
    }

}