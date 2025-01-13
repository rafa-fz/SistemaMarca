package ec.edu.espe.marca.transaccion.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import ec.edu.espe.marca.cliente.service.ClienteService;
import ec.edu.espe.marca.comision.service.ComisionService;
import ec.edu.espe.marca.tarjeta.model.Tarjeta;
import ec.edu.espe.marca.tarjeta.service.TarjetaService;
import ec.edu.espe.marca.transaccion.model.Transaccion;
import ec.edu.espe.marca.transaccion.repository.TransaccionRepository;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TarjetaService tarjetaService;
    private final ComisionService comisionService;

    // Lista de valores posibles solo para simulación de CODIGO_RESPUESTA del banco emisor
    private static final String[] STATUS = {
        "OK", "OK", "OK", "OK", "OK","OK", "OK", "OK", "OK", "OK","OK", "OK", "OK", "OK", "OK",
        "SOBREGIRADA",
        "ANULADA",
        "FONDOS INSUFICIENTES",
        "BLOQUEADA",
        "EXPIRADA",
        "INHABILITADA",
        "INACTIVA",
        "OTRO"
    };

    private static final Set<String> TIPOS_TTRANSACCION = new HashSet<>(Arrays.asList(
        "CREDITO",
        "CRE",
        "DEBITO",
        "DEB",
        "RETIRO",
        "RET",
        "COMPRA"
    ));

    private static final String CODIGO_RESPUESTA_OK = "OK";
    private static final String ESTADO_APR = "APR";
    private static final String ESTADO_REC = "REC";
    private static final String AUTORIZACION = "AUTH";


    public TransaccionService(TransaccionRepository transaccionRepository, TarjetaService tarjetaService, ClienteService clienteService, ComisionService comisionService) {
        this.transaccionRepository = transaccionRepository;
        this.tarjetaService = tarjetaService;
        this.comisionService = comisionService;
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
        // VALIDAR EL TIPO DE TRANSACCIÓN A REALIZAR > EN LA MARCA
        if (!TIPOS_TTRANSACCION.contains(transaccion.getTipoTransaccion().toUpperCase())) {
            throw new RuntimeException("El tipo de transacción no es válido");
        }

        int siguienteId = transaccionRepository.obtenerSiguienteId();
        transaccion.setCodTransaccion(siguienteId);

        // REGISTRAR TRANSACCIÓN INICIAL > EN LA MARCA
        // this.transaccionRepository.save(transaccion); 

        // VALIDAR DATOS DE LA TARJETA > EN LA MARCA
        this.tarjetaService.validacionPreviaTarjeta(transaccion.getTarjeta());
        Tarjeta tarjetaConsultadaBD = this.tarjetaService.obtenerTarjetaPorNumeroTarjeta(transaccion.getTarjeta().getNumeroTarjeta());
        transaccion.setCodTarjeta(tarjetaConsultadaBD.getCodTarjeta());
        this.tarjetaService.validarTarjeta(transaccion.getTarjeta(), tarjetaConsultadaBD);

        // REGISTRAR COMISION > EN LA MARCA
        // logica para el cálculo de la comision de la transacción
        transaccion = this.comisionService.calcularComisionTransaccionTarjeta(transaccion);

        // SOLICITAR APROBACIÓN DE LA TRANSACCIÓN > DE LA MARCA AL BANCO EMISOR 
        // PENDIENTE - Simulación de la petición de aprobación al banco emisor 
        Transaccion transaccionDevueltaPorBancoEmisor = new Transaccion(); 
        // INICIO - SE simula la respuesta del banco emisor con un código de respuesta aleatorio - INICIO
        transaccionDevueltaPorBancoEmisor.setCodigoAutorizacion(AUTORIZACION + (new Random()).nextInt(999) );
        transaccionDevueltaPorBancoEmisor.setCodigoRespuesta(generarCodigosDeRespuestaALeatorio());
        transaccionDevueltaPorBancoEmisor.setEstado(ESTADO_APR);

        // REGISTRAR RESPUESTA > EN LA MARCA
        transaccion.setCodigoRespuesta(transaccionDevueltaPorBancoEmisor.getCodigoRespuesta());
        transaccion.setCodigoAutorizacion(transaccionDevueltaPorBancoEmisor.getCodigoAutorizacion());
        transaccion.setEstado(transaccionDevueltaPorBancoEmisor.getCodigoRespuesta().equals(CODIGO_RESPUESTA_OK) ? ESTADO_APR : ESTADO_REC);
        // FIN - SE simula la respuesta del banco emisor con un código de respuesta aleatorio - FIN

        // ASIGNAR DATOS POR DEFECTO > EN LA MARCA
        transaccion.setFechaTransaccion(LocalDateTime.now());
        siguienteId = transaccionRepository.obtenerSiguienteId();
        transaccion.setCodTransaccion(siguienteId);
        transaccion.setTarjeta(tarjetaConsultadaBD);

        // DEVOLVER ESTADO DE LA TRANSACCIÓN > DE LA MARCA HACIA EL GATEWAY O PROCESADOR DE PAGOS
        return this.transaccionRepository.save(transaccion);
    }

    public Transaccion actualizarTransaccion(Integer codigo, Transaccion transaccion) {
        if (this.transaccionRepository.existsById(codigo)) {
            return this.transaccionRepository.save(transaccion);
        } else {
            throw new RuntimeException("No se puede actualizar. No existe la transacción con el código: " + codigo);
        }
    }

    public static String generarCodigosDeRespuestaALeatorio() {
        Random random = new Random();
        int index = random.nextInt(STATUS.length); // Genera un índice aleatorio
        return STATUS[index];
    }

}
