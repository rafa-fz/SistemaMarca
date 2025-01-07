package ec.edu.espe.marca.liquidacion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.edu.espe.marca.liquidacion.model.Liquidacion;
import ec.edu.espe.marca.liquidacion.service.LiquidacionService;

@RestController
@RequestMapping("/liquidacion")
public class LiquidacionController {

    private final LiquidacionService liquidacionService;

    public LiquidacionController(LiquidacionService liquidacionService) {
        this.liquidacionService = liquidacionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Liquidacion> obtenerLiquidacion(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.liquidacionService.obtenerLiquidacionPorId(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Liquidacion> crearLiquidacion(@RequestBody Liquidacion liquidacion) {
        Liquidacion nuevaLiquidacion = this.liquidacionService.crearLiquidacion(liquidacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaLiquidacion);
    }
}
