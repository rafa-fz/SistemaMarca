package ec.edu.espe.marca.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.edu.espe.marca.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
