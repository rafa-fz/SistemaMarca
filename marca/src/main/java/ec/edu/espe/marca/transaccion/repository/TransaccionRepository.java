package ec.edu.espe.marca.transaccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.edu.espe.marca.transaccion.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

}
