package ec.edu.espe.marca.cliente.controller;

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

import ec.edu.espe.marca.cliente.model.Cliente;
import ec.edu.espe.marca.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = this.clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.clienteService.obtenerClientePorId(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = this.clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = this.clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

}
