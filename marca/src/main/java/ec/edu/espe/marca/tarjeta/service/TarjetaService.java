package ec.edu.espe.marca.tarjeta.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import ec.edu.espe.marca.tarjeta.model.Tarjeta;
import ec.edu.espe.marca.tarjeta.repository.TarjetaRepository;

@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private static final Set<String> TIPOS_TARJETA_SET = new HashSet<>(Arrays.asList(
        "CREDITO",
        "CRE",
        "DEBITO",
        "DEB"
    ));

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public Tarjeta obtenerTarjetaPorNumeroTarjeta(String numeroTarjeta) { // 1234567890123456
        Optional<Tarjeta> tarjetaOpt = this.tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);

        if (tarjetaOpt.isPresent()) {
            return tarjetaOpt.get();
        } else {
            throw new RuntimeException("No existe la tarjeta con el numeroTarjeta: " + numeroTarjeta);
        }
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

    public void validacionPreviaTarjeta(Tarjeta tarjetaAValidar) {
        // VALIDACIÓN PREVIDA DE LA TARJETA > EN LA MARCA
        if (tarjetaAValidar.getNumeroTarjeta().length() != 16) {
            throw new RuntimeException("El número de tarjeta debe tener 16 dígitos");
        }
        if (tarjetaAValidar.getCvvTarjeta().length() != 3) {
            throw new RuntimeException("El cvv debe tener 3 dígitos");
        }
        if (tarjetaAValidar.getNombreEnTarjeta().length() < 5) {
            throw new RuntimeException("El nombre en la tarjeta debe tener al menos 5 caracteres");
        }
        if (!TIPOS_TARJETA_SET.contains(tarjetaAValidar.getTipoTarjeta().toUpperCase())) {
            throw new RuntimeException("El tipo de tarjeta no es válido");
        }
        YearMonth mesAnioExpiracion = YearMonth.parse(tarjetaAValidar.getFechaDeExpiracion(), DateTimeFormatter.ofPattern("MM/yy"));
        if (mesAnioExpiracion.isBefore(YearMonth.now())) {
            throw new RuntimeException("La fecha ingresada es incorrecta");
        }
    }

    public void validarTarjeta(Tarjeta tarjetaAValidar, Tarjeta tarjetaConsultadaBD) {
        // VALIDACIÓN DE LOS DATOS DE LA TARJETA REGISTRADA
        if (!tarjetaAValidar.getNumeroTarjeta().equals(tarjetaConsultadaBD.getNumeroTarjeta())) {
            throw new RuntimeException("El número de tarjeta no coincide");
        }
        if (!tarjetaAValidar.getCvvTarjeta().equals(tarjetaConsultadaBD.getCvvTarjeta())) {
            throw new RuntimeException("El cvv no coincide");
        }
        if (!tarjetaAValidar.getNombreEnTarjeta().equals(tarjetaConsultadaBD.getNombreEnTarjeta())) {
            throw new RuntimeException("El nombre de la tarjeta no coincide");
        }
        YearMonth mesAnioExpiracion = YearMonth.parse(tarjetaAValidar.getFechaDeExpiracion(), DateTimeFormatter.ofPattern("MM/yy"));
        tarjetaAValidar.setFechaExpiracion(mesAnioExpiracion.atDay(1));
        if (!tarjetaAValidar.getFechaExpiracion().equals(tarjetaConsultadaBD.getFechaExpiracion().withDayOfMonth(1))) {
            throw new RuntimeException("La fecha de expiración de la tarjeta no coincide");
        }
    }
}
