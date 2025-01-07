package ec.edu.espe.marca.liquidacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.edu.espe.marca.liquidacion.model.Liquidacion;

public interface LiquidacionRepository extends JpaRepository<Liquidacion, Integer> {

}
