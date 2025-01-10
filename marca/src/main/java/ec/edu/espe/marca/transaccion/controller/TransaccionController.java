package ec.edu.espe.marca.transaccion.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.marca.transaccion.model.Transaccion;
import ec.edu.espe.marca.transaccion.service.TransaccionService;

// DTO para retornar solo los datos necesarios al cliente
class TransaccionDTO {
    private Integer codTransaccion;
    private String fechaTransaccion;
    private BigDecimal monto;
    private String estado;
    private String codigoAutorizacion;
    private String paisOrigen;
    private BigDecimal comisionMarca;

    public TransaccionDTO(Integer codTransaccion, String fechaTransaccion, BigDecimal monto, String estado,
                          String codigoAutorizacion, String paisOrigen, BigDecimal comisionMarca) {
        this.codTransaccion = codTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.estado = estado;
        this.codigoAutorizacion = codigoAutorizacion;
        this.paisOrigen = paisOrigen;
        this.comisionMarca = comisionMarca;
    }

    // Getters para serialización
    public Integer getCodTransaccion() {
        return codTransaccion;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public BigDecimal getComisionMarca() {
        return comisionMarca;
    }
}

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping
    public ResponseEntity<List<TransaccionDTO>> obtenerTodasLasTransacciones() {
        List<Transaccion> transacciones = transaccionService.obtenerTodasTransacciones();

        // Convertir entidades a DTOs para filtrar los datos
        List<TransaccionDTO> transaccionesDTO = transacciones.stream()
                .map(transaccion -> new TransaccionDTO(
                        transaccion.getCodTransaccion(),
                        transaccion.getFechaTransaccion().toString(),
                        transaccion.getMonto(),
                        transaccion.getEstado(),
                        transaccion.getCodigoAutorizacion(),
                        transaccion.getPaisOrigen(),
                        transaccion.getComisionMarca()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(transaccionesDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccion(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.transaccionService.ObtenerTransaccionporCodigo(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
