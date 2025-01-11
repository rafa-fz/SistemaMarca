package ec.edu.espe.marca.transaccion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.espe.marca.transaccion.model.Transaccion;
import ec.edu.espe.marca.transaccion.repository.TransaccionRepository;

@Service
public class TransaccionService {

    private TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<Transaccion> obtenerTodasTransacciones() {
        return this.transaccionRepository.findAll();
    }

    public Transaccion ObtenerTransaccionporCodigo(Integer codigo) {

        return this.transaccionRepository.findById(codigo)
        .orElseThrow(() -> new RuntimeException("No existe la transacción con el código: " + codigo));
        /*
         Optional<Transaccion> transaccionOpt = this.transaccionRepository.findById(codigo);
        if (!transaccionOpt.isPresent()) {
            return transaccionOpt.get();
        } else {
            throw new RuntimeException("No existe la transaccion con el codigo: " + codigo);
        }
         
         */

    }

    public Transaccion crearTransaccion(Transaccion transaccion) {
        // REGISTRAR TRANSACCIÓN INICIAL > EN LA MARCA
        int siguienteId = transaccionRepository.obtenerSiguienteId();
        transaccion.setCodTransaccion(siguienteId);
        this.transaccionRepository.save(transaccion); 

        // VALIDAR DATOS DE LA TARJETA > EN LA MARCA

        // VALIDAR DATOS DEL CLIENTE > EN LA MARCA

        // SOLICITAR APROBACIÓN > LA MARCA AL BANCO EMISOR 

        // REGISTRAR RESPUESTA > EN LA MARCA

        // REGISTRAR COMISION > EN LA MARCA

        // DEVOLVER ESTADO DE LA TRANSACCIÓN > DE LA MARCA HACIA EL GATEWAY O PROCESADOR DE PAGOS

        int siguienteId = transaccionRepository.obtenerSiguienteId();
        transaccion.setCodTransaccion(siguienteId);
        return this.transaccionRepository.save(transaccion);
    }

    public Transaccion actualizarTransaccion(Integer codigo, Transaccion transaccion) {
        if (this.transaccionRepository.existsById(codigo)) {
            return this.transaccionRepository.save(transaccion);
        } else {
            throw new RuntimeException("No se puede actualizar. No existe la transacción con el código: " + codigo);
        }
    }

}
