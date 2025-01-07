package ec.edu.espe.marca.cliente.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import ec.edu.espe.marca.cliente.model.Cliente;
import ec.edu.espe.marca.cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Integer id) {
        Optional<Cliente> clienteOpt = this.clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            return clienteOpt.get();
        } else {
            throw new RuntimeException("No existe el cliente con el ID: " + id);
        }
    }

    public Cliente crearCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        if (this.clienteRepository.existsById(id)) {
            return this.clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("No se puede actualizar. No existe el cliente con el ID: " + id);
        }
    }

}
