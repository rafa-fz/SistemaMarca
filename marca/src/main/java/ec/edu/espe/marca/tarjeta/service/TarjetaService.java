package ec.edu.espe.marca.tarjeta.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ec.edu.espe.marca.tarjeta.model.Tarjeta;
import ec.edu.espe.marca.tarjeta.repository.TarjetaRepository;

@Service
public class TarjetaService {

    private TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public Tarjeta obtenerTarjetaPorId(Integer id) {
        Optional<Tarjeta> tarjetaOpt = this.tarjetaRepository.findById(id);
        if (tarjetaOpt.isPresent()) {
            return tarjetaOpt.get();
        } else {
            throw new RuntimeException("No existe la tarjeta con el ID: " + id);
        }
    }

    public Tarjeta crearTarjeta(Tarjeta tarjeta) {
        return this.tarjetaRepository.save(tarjeta);
    }

    public Tarjeta actualizarTarjeta(Integer id, Tarjeta tarjeta) {
        if (this.tarjetaRepository.existsById(id)) {
            return this.tarjetaRepository.save(tarjeta);
        } else {
            throw new RuntimeException("No se puede actualizar. No existe la tarjeta con el ID: " + id);
        }
    }
}
