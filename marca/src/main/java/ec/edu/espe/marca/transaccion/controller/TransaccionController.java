package ec.edu.espe.marca.transaccion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.marca.transaccion.model.Transaccion;
import ec.edu.espe.marca.transaccion.service.TransaccionService;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    private final TransaccionService transaccion;

    public TransaccionController(TransaccionService transaccion) {
        this.transaccion = transaccion;
    }

    @GetMapping
    public ResponseEntity<List<Transaccion>> obtenerTodasLasTransacciones() {
        try {
            return ResponseEntity.ok(this.transaccion.obtenerTodasTransacciones());
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Transaccion> obtenerTransaccion(Integer codigo) {
        try {
            return ResponseEntity.ok(this.transaccion.ObtenerTransaccionporCodigo(codigo));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody Transaccion transaccion) {
        Transaccion nuevaTransaccion = this.transaccion.crearTransaccion(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTransaccion);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable Integer codigo,
            @RequestBody Transaccion transaccion) {
        try {
            Transaccion transaccionActualizada = this.transaccion.actualizarTransaccion(codigo, transaccion);
            return ResponseEntity.ok(transaccionActualizada);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

}
