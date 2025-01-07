package ec.edu.espe.marca.tarjeta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.edu.espe.marca.tarjeta.model.Tarjeta;
import ec.edu.espe.marca.tarjeta.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> obtenerTarjeta(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.tarjetaService.obtenerTarjetaPorId(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody Tarjeta tarjeta) {
        Tarjeta nuevaTarjeta = this.tarjetaService.crearTarjeta(tarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarjeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarjeta> actualizarTarjeta(@PathVariable Integer id, @RequestBody Tarjeta tarjeta) {
        try {
            Tarjeta tarjetaActualizada = this.tarjetaService.actualizarTarjeta(id, tarjeta);
            return ResponseEntity.ok(tarjetaActualizada);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
